package proiect.filmesgbd.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import proiect.filmesgbd.entities.RecenzieActor;

import java.util.List;

@Repository
public class RecenzieActorRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<RecenzieActor> rowMapper = (rs, rowNum) -> {
        RecenzieActor ra = new RecenzieActor();
        ra.setIdRecenzieActor(rs.getLong("id_recenzie_actor"));
        ra.setIdClient(rs.getLong("id_client"));
        ra.setIdActor(rs.getLong("id_actor"));
        ra.setComentariu(rs.getString("comentariu"));
        ra.setOptiunePredefinita(rs.getString("optiune_predefinita"));
        ra.setSentimentDetectat(rs.getString("sentiment_detectat"));
        ra.setNumeClient(rs.getString("nume_client"));
        ra.setNumeActor(rs.getString("nume_actor"));
        return ra;
    };

    public void salveaza(RecenzieActor ra) {
        String sql = "INSERT INTO recenzii_actori (id_client, id_actor, comentariu, optiune_predefinita, sentiment_detectat) " +
                "VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                ra.getIdClient(),
                ra.getIdActor(),
                ra.getComentariu(),
                ra.getOptiunePredefinita(),
                ra.getSentimentDetectat()
        );
    }

    public List<RecenzieActor> findBySentiment(String sentiment) {
        String sql = """
        SELECT ra.*,
               a.prenume || ' ' || a.nume_familie AS nume_actor,
               c.nume || ' ' || c.prenume AS nume_client
        FROM recenzii_actori ra
        JOIN actori a ON ra.id_actor = a.id_actor
        JOIN clienti c ON ra.id_client = c.id_client
        WHERE LOWER(ra.sentiment_detectat) = LOWER(?)
        ORDER BY ra.id_recenzie_actor DESC
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            RecenzieActor ra = new RecenzieActor();
            ra.setIdRecenzieActor(rs.getLong("id_recenzie_actor"));
            ra.setComentariu(rs.getString("comentariu"));
            ra.setOptiunePredefinita(rs.getString("optiune_predefinita"));
            ra.setSentimentDetectat(rs.getString("sentiment_detectat"));
            ra.setNumeActor(rs.getString("nume_actor"));
            ra.setNumeClient(rs.getString("nume_client"));
            return ra;
        }, sentiment);
    }

    public List<RecenzieActor> findByActorId(Long idActor) {
        String sql = """
        SELECT ra.*,
               a.prenume || ' ' || a.nume_familie AS nume_actor,
               c.nume || ' ' || c.prenume AS nume_client
        FROM recenzii_actori ra
        JOIN actori a ON ra.id_actor = a.id_actor
        JOIN clienti c ON ra.id_client = c.id_client
        WHERE ra.id_actor = ?
        ORDER BY ra.id_recenzie_actor DESC
        """;
        return jdbcTemplate.query(sql, rowMapper, idActor);
    }

    public List<RecenzieActor> findByClientId(Long idClient) {
        String sql = """
        SELECT ra.*,
               a.prenume || ' ' || a.nume_familie AS nume_actor,
               c.nume || ' ' || c.prenume AS nume_client
        FROM recenzii_actori ra
        JOIN actori a ON ra.id_actor = a.id_actor
        JOIN clienti c ON ra.id_client = c.id_client
        WHERE ra.id_client = ?
        ORDER BY ra.id_recenzie_actor DESC
        """;
        return jdbcTemplate.query(sql, rowMapper, idClient);
    }

    public String findSentimentMediuActor(String prenume, String numeFamilie) {
        String sql = "SELECT calculeaza_sentiment_actor(?, ?)";
        return jdbcTemplate.queryForObject(sql, String.class, prenume, numeFamilie);
    }
}