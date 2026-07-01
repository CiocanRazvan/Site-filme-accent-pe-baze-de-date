package proiect.filmesgbd.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import proiect.filmesgbd.entities.Film;

import java.time.LocalDate;
import java.util.List;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Repository
public class FilmRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Film> filmRowMapper = (rs, rowNum) -> new Film(
            rs.getLong("id_film"),
            rs.getString("titlu"),
            rs.getString("descriere"),
            rs.getString("categorie"),
            rs.getDate("data_lansarii").toLocalDate(),
            rs.getDouble("rating_mediu")
    );

    public List<Film> findAll() {
        String sql = "SELECT * FROM filme ORDER BY id_film";
        return jdbcTemplate.query(sql, filmRowMapper);
    }

    public Film findById(int id) {
        String sql = "SELECT * FROM filme WHERE id_film = ?";
        return jdbcTemplate.queryForObject(sql, filmRowMapper, id);
    }

    public List<Film> findByTitlu(String titlu) {
        String sql = "SELECT * FROM filme WHERE titlu ILIKE ?";
        return jdbcTemplate.query(sql, filmRowMapper, "%" + titlu + "%");
    }

    public List<Film> findByCategorie(String categorie) {
        String sql = "SELECT * FROM filme WHERE categorie = ?";
        return jdbcTemplate.query(sql, filmRowMapper, categorie);
    }

    public List<Film> findByDataLansarii(LocalDate data) {
        String sql = "SELECT * FROM filme WHERE data_lansarii = ?";
        return jdbcTemplate.query(sql, filmRowMapper, data);
    }
    public List<Film> cautaDupaTitluSiCategorie(String titlu, String categorie) {
        String sql = "SELECT * FROM filme WHERE LOWER(titlu) LIKE LOWER(?) AND LOWER(categorie) = LOWER(?)";
        return jdbcTemplate.query(sql, filmRowMapper, "%" + titlu + "%", categorie);
    }
}
