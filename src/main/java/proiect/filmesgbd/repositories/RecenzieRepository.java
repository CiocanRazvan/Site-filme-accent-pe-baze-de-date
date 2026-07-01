    package proiect.filmesgbd.repositories;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.jdbc.core.JdbcTemplate;
    import org.springframework.jdbc.core.RowMapper;
    import org.springframework.stereotype.Repository;
    import proiect.filmesgbd.entities.Recenzie;

    import java.util.List;
    import java.util.Map;

    @Repository
    public class RecenzieRepository{
        @Autowired
        private JdbcTemplate jdbcTemplate;

        private final RowMapper<Recenzie> recenzieRowMapper = (rs, rowNum) -> {
            Recenzie r = new Recenzie();
            r.setIdRecenzie(rs.getLong("id_recenzie"));
            r.setIdClient(rs.getLong("id_client"));
            r.setIdFilm(rs.getLong("id_film"));
            r.setVot(rs.getInt("vot"));
            r.setComentariu(rs.getString("comentariu"));
            r.setOptiunePredefinita(rs.getString("optiune_predefinita"));
            r.setTitluFilm(rs.getString("titlu_film"));

            try { r.setNumeClient(rs.getString("nume_client")); }
            catch (Exception ignored) {}

            return r;
        };

        public List<Recenzie> findAll() {
            String sql = """
            SELECT r.*,
                   c.nume || ' ' || c.prenume AS nume_client,
                   f.titlu AS titlu_film
            FROM recenzii r
            JOIN clienti c ON r.id_client = c.id_client
            JOIN filme f   ON r.id_film   = f.id_film
            ORDER BY r.id_recenzie DESC
            """;
            return jdbcTemplate.query(sql, recenzieRowMapper);
        }


        public Recenzie findById(int idRecenzie) {
            String sql = "SELECT * FROM recenzii WHERE id_recenzie = ?";
            return jdbcTemplate.queryForObject(sql, recenzieRowMapper, idRecenzie);
        }

        public List<Recenzie> findByClientId(int idClient) {

            String sql =
                    "SELECT r.*, " +
                            "f.titlu AS titlu_film, " +
                            "analizeaza_sentiment_complex(r.comentariu) AS sentiment_calculat " +
                            "FROM recenzii r " +
                            "JOIN filme f ON r.id_film = f.id_film " +
                            "WHERE r.id_client = ?";

            return jdbcTemplate.query(sql, (rs, rowNum) -> {

                Recenzie r = recenzieRowMapper.mapRow(rs, rowNum);

                r.setSentimentDetectat(
                        rs.getString("sentiment_calculat")
                );

                return r;

            }, idClient);
        }

        public List<Recenzie> findByActorId(int idActor) {
            String sql = """
            SELECT r.*, f.titlu AS titlu_film
            FROM recenzii r
            JOIN filme f           ON r.id_film  = f.id_film
            JOIN distributie_filme df ON f.id_film = df.id_film
            WHERE df.id_actor = ?
            """;
            return jdbcTemplate.query(sql, recenzieRowMapper, idActor);
        }

        public List<Recenzie> findBySentiment(String sentimentCauta) {

            String sql = """
            SELECT r.*, 
                   f.titlu AS titlu_film,
                   c.nume || ' ' || c.prenume AS nume_client,
                   analizeaza_sentiment_complex(r.comentariu) AS sentiment_detectat
            FROM recenzii r
            JOIN filme f ON r.id_film = f.id_film
            JOIN clienti c ON r.id_client = c.id_client
            WHERE LOWER(analizeaza_sentiment_complex(r.comentariu)) = LOWER(?)
            ORDER BY r.id_recenzie DESC
            """;

            return jdbcTemplate.query(sql, (rs, rowNum) -> {

                Recenzie r = recenzieRowMapper.mapRow(rs, rowNum);

                r.setSentimentDetectat(rs.getString("sentiment_detectat"));
                r.setNumeClient(rs.getString("nume_client"));
                r.setTitluFilm(rs.getString("titlu_film"));

                return r;
            }, sentimentCauta);
        }

        public String getSentiment(String text) {
            String sql = "SELECT analizeaza_sentiment_complex(?)";
            return jdbcTemplate.queryForObject(sql, String.class, text);
        }

        public void adaugaRecenzie(Recenzie recenzie) {
            String sql = "INSERT INTO recenzii (id_client, id_film, vot, comentariu, optiune_predefinita)  VALUES (?, ?, ?, ?, ?)";

            jdbcTemplate.update(sql,
                    recenzie.getIdClient(),
                    recenzie.getIdFilm(),
                    recenzie.getVot(),
                    recenzie.getComentariu(),
                    recenzie.getOptiunePredefinita()
            );
        }

        public List<Map<String, Object>> findByTitluFilm(String titlu) {
            String sql = "SELECT r.*, " +
                    "c.nume || ' ' || c.prenume AS nume_client, " +  // ← adaugă asta
                    "f.titlu AS titlu_film " +
                    "FROM recenzii r " +
                    "JOIN clienti c ON r.id_client = c.id_client " +
                    "JOIN filme f ON r.id_film = f.id_film " +
                    "WHERE f.titlu ILIKE ?";
            return jdbcTemplate.queryForList(sql, "%" + titlu + "%");
        }

        public String findSentimentMediuFilm(String titlu) {
            String sql = "SELECT calculeaza_sentiment_film(?)";
            return jdbcTemplate.queryForObject(sql, String.class, titlu);
        }

        public String getSentimentCategorie(String categorie) {
            String sql = """
        SELECT analizeaza_sentiment_complex(r.comentariu) AS sentiment
        FROM recenzii r
        JOIN filme f ON r.id_film = f.id_film
        WHERE f.categorie ILIKE ?
        GROUP BY analizeaza_sentiment_complex(r.comentariu)
        ORDER BY COUNT(*) DESC
        LIMIT 1
        """;
            List<String> results = jdbcTemplate.queryForList(sql, String.class, categorie);
            return results.isEmpty() ? "NICIO RECENZIE" : results.get(0);
        }
    }
