package proiect.filmesgbd.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import proiect.filmesgbd.entities.Actor;

import java.util.List;
import java.util.Map;

@Repository
public class ActorRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Actor> actorRowMapper = (rs, rowNum) -> new Actor(
            rs.getLong("id_actor"),
            rs.getString("nume_familie"),
            rs.getString("prenume"),
            rs.getString("nume_scena"),
            rs.getDate("data_nasterii") != null ? rs.getDate("data_nasterii").toLocalDate() : null
    );

    public List<Actor> findAll() {
        String sql = "SELECT * FROM actori ORDER BY nume_familie, prenume";
        return jdbcTemplate.query(sql, actorRowMapper);
    }

    public List<Actor> findByNumeFamilie(String numeFamilie) {
        String sql = "SELECT * FROM actori WHERE nume_familie ILIKE ?";
        return jdbcTemplate.query(sql, actorRowMapper, "%" + numeFamilie + "%");
    }

    public List<Actor> findByPrenume(String prenume) {
        String sql = "SELECT * FROM actori WHERE prenume ILIKE ?";
        return jdbcTemplate.query(sql, actorRowMapper, "%" + prenume + "%");
    }

    public List<Actor> findByNumeScena(String numeScena) {
        String sql = "SELECT * FROM actori WHERE nume_scena ILIKE ?";
        return jdbcTemplate.query(sql, actorRowMapper, "%" + numeScena + "%");
    }

    public List<Actor> findByTitluFilm(String titluFilm) {
        String sql = "SELECT a.* FROM actori a JOIN distributie_filme d ON a.id_actor = d.id_actor JOIN filme f ON d.id_film = f.id_film WHERE f.titlu ILIKE ?";
        return jdbcTemplate.query(sql, actorRowMapper, "%" + titluFilm + "%");
    }
    public List<Actor> findByFilmId(int idFilm) {
        String sql = "SELECT a.* FROM actori a JOIN distributie_filme df ON a.id_actor = df.id_actor WHERE df.id_film = ?";
        return jdbcTemplate.query(sql, actorRowMapper, idFilm);
    }

    public List<Actor> findByNumeComplet(String numeComplet){
        String sql = "SELECT * FROM actori WHERE LOWER(prenume || ' ' || nume_familie) LIKE LOWER(?)";
        return jdbcTemplate.query(sql, actorRowMapper, "%" + numeComplet + "%");
    }

    public List<Map<String, Object>> findFilmeByActorSearch(String criteria) {
        String sql = "SELECT f.id_film, f.titlu, f.categorie, f.data_lansarii, f.rating_mediu, d.rol " +
                "FROM filme f " +
                "JOIN distributie_filme d ON f.id_film = d.id_film " +
                "JOIN actori a ON d.id_actor = a.id_actor " +
                "WHERE a.nume_familie ILIKE ? " +
                "   OR a.prenume ILIKE ? " +
                "   OR a.nume_scena ILIKE ? " +
                "   OR (a.prenume || ' ' || a.nume_familie) ILIKE ? " +
                "ORDER BY f.data_lansarii DESC";

        String searchPattern = "%" + criteria + "%";
        return jdbcTemplate.queryForList(sql,
                searchPattern, searchPattern, searchPattern, searchPattern);
    }
    public List<Actor> findByRolInDistributie(String rol) {
        String sql = "SELECT DISTINCT a.* FROM actori a " +
                "JOIN distributie_filme d ON a.id_actor = d.id_actor " +
                "WHERE d.rol ILIKE ?";
        return jdbcTemplate.query(sql, actorRowMapper, "%" + rol + "%");
    }

}
