package proiect.filmesgbd.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import proiect.filmesgbd.entities.VersiuneFilm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class VersiuneFilmRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<VersiuneFilm> versiuneRowMapper = (rs, rowNum) -> {
        VersiuneFilm vf = new VersiuneFilm();
        vf.setIdVersiune(rs.getLong("id_versiune"));
        vf.setIdFilm(rs.getLong("id_film"));
        vf.setFormat(rs.getString("format"));
        vf.setLimba(rs.getString("limba"));
        vf.setRezolutie(rs.getString("rezolutie"));

        try {
            vf.setTitluFilm(rs.getString("titlu"));
        } catch (Exception e) { }

        return vf;
    };

    public List<VersiuneFilm> findAll() {
        String sql = "SELECT v.*, f.titlu  FROM versiuni_filme v JOIN filme f ON v.id_film = f.id_film ORDER BY f.titlu";
        return jdbcTemplate.query(sql, versiuneRowMapper);
    }

    public List<VersiuneFilm> findByIdFilm(int idFilm) {
        String sql = "SELECT * FROM versiuni_filme WHERE id_film = ?";
        return jdbcTemplate.query(sql, versiuneRowMapper, idFilm);
    }

    public List<VersiuneFilm> findByFormat(String format) {
        String sql = "SELECT v.*, f.titlu FROM versiuni_filme v JOIN filme f ON v.id_film = f.id_film WHERE v.format = ?";
        return jdbcTemplate.query(sql, versiuneRowMapper, format);
    }

    public List<VersiuneFilm> findByLimba(String limba) {
        String sql = "SELECT v.*, f.titlu FROM versiuni_filme v JOIN filme f ON v.id_film = f.id_film WHERE v.limba = ?";
        return jdbcTemplate.query(sql, versiuneRowMapper, limba);
    }

    public List<VersiuneFilm> findByRezolutie(String rezolutie) {
        String sql = "SELECT v.*, f.titlu FROM versiuni_filme v JOIN filme f ON v.id_film = f.id_film WHERE v.rezolutie = ?";
        return jdbcTemplate.query(sql, versiuneRowMapper, rezolutie);
    }
}
