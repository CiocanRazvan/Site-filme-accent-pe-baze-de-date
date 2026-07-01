package proiect.filmesgbd.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import proiect.filmesgbd.entities.DistributieFilm;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

@Repository
public class DistributieFilmRepository  {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<DistributieFilm> distributieRowMapper = (rs, rowNum) -> {
        DistributieFilm df = new DistributieFilm();
        df.setIdFilm(rs.getLong("id_film"));
        df.setIdActor(rs.getLong("id_actor"));
        df.setRol(rs.getString("rol"));
        df.setObservatiiPrestatie(rs.getString("observatii_prestatie"));
        try { df.setTitluFilm(rs.getString("titlu")); } catch (Exception ignored) {}
        try { df.setNumeScena(rs.getString("nume_scena")); } catch (Exception ignored) {}
        try { df.setPrenume(rs.getString("prenume")); } catch (Exception ignored) {}
        try { df.setNumeFamilie(rs.getString("nume_familie")); } catch (Exception ignored) {}
        return df;
    };
    public List<DistributieFilm> findAll() {
        String sql = "SELECT d.*, f.titlu, a.nume_scena FROM distributie_filme d JOIN filme f ON d.id_film = f.id_film JOIN actori a ON d.id_actor = a.id_actor";
        return jdbcTemplate.query(sql, distributieRowMapper);
    }

    public List<DistributieFilm> findByFilmId(int idFilm) {
        String sql = "SELECT d.id_film, d.id_actor, d.rol, d.observatii_prestatie, " +
                "a.nume_scena, a.prenume, a.nume_familie " +
                "FROM distributie_filme d " +
                "LEFT JOIN actori a ON d.id_actor = a.id_actor " +
                "WHERE d.id_film = ?";
        return jdbcTemplate.query(sql, distributieRowMapper, idFilm);
    }

    public List<DistributieFilm> findByActorId(int idActor) {
        String sql = "SELECT d.*, f.titlu  FROM distributie_filme d JOIN filme f ON d.id_film = f.id_film WHERE d.id_actor = ?";
        return jdbcTemplate.query(sql, distributieRowMapper, idActor);
    }

    public List<DistributieFilm> findByRol(String rol) {
        String sql = "SELECT d.*, f.titlu, a.nume_scena  FROM distributie_filme d JOIN filme f ON d.id_film = f.id_film JOIN actori a ON d.id_actor = a.id_actor WHERE d.rol ILIKE ?";
        return jdbcTemplate.query(sql, distributieRowMapper, "%" + rol + "%");
    }

    public String getSentimentPrestatie(int idFilm, int idActor) {
        String sql = "SELECT analizeaza_sentiment_complex(observatii_prestatie) FROM distributie_filme WHERE id_film = ? AND id_actor = ?";
        return jdbcTemplate.queryForObject(sql, String.class, idFilm, idActor);
    }

}
