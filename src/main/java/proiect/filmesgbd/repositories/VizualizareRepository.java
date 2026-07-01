package proiect.filmesgbd.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import proiect.filmesgbd.entities.Vizualizare;

import java.time.LocalDate;
import java.util.List;

@Repository
public class VizualizareRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Vizualizare> vizualizareRowMapper = (rs, rowNum) -> {

        Vizualizare v = new Vizualizare();

        v.setIdVizualizare(rs.getLong("id_vizualizare"));
        v.setIdClient(rs.getLong("id_client"));
        v.setIdVersiune(rs.getLong("id_versiune"));

        v.setDataVizualizarii(
                rs.getTimestamp("data_vizualizarii").toLocalDateTime()
        );

        v.setDurataVizionataMinute(
                rs.getInt("durata_vizionata_minute")
        );

        v.setStareFinalizata(
                rs.getBoolean("stare_finalizata")
        );

        v.setTitluFilm(rs.getString("titlu_film"));
        v.setFormatVersiune(rs.getString("versiune_format"));

        return v;
    };

    public List<Vizualizare> findAll() {
        String sql = "SELECT v.*, c.nume || ' ' || c.prenume as nume_client, f.titlu, vf.format FROM vizualizari v JOIN clienti c ON v.id_client = c.id_client JOIN versiuni_filme vf ON v.id_versiune = vf.id_versiune JOIN filme f ON vf.id_film = f.id_film ORDER BY v.data_vizualizarii DESC";
        return jdbcTemplate.query(sql, vizualizareRowMapper);
    }

    public Vizualizare findById(int idVizualizare) {
        String sql = "SELECT * FROM vizualizari WHERE id_vizualizare = ?";
        return jdbcTemplate.queryForObject(sql, vizualizareRowMapper, idVizualizare);
    }

    public List<Vizualizare> findByClientId(int idClient) {

        String sql =
                "SELECT " +
                        "v.*, " +
                        "f.titlu AS titlu_film, " +
                        "vf.format AS versiune_format " +
                        "FROM vizualizari v " +
                        "JOIN versiuni_filme vf ON v.id_versiune = vf.id_versiune " +
                        "JOIN filme f ON vf.id_film = f.id_film " +
                        "WHERE v.id_client = ? " +
                        "ORDER BY v.data_vizualizarii DESC";

        return jdbcTemplate.query(sql, vizualizareRowMapper, idClient);
    }

    public List<Vizualizare> findByIdVersiune(int idVersiune) {
        String sql = "SELECT * FROM vizualizari WHERE id_versiune = ?";
        return jdbcTemplate.query(sql, vizualizareRowMapper, idVersiune);
    }

    public List<Vizualizare> findByData(LocalDate data) {
        String sql = "SELECT * FROM vizualizari WHERE data_vizualizarii::date = ?";
        return jdbcTemplate.query(sql, vizualizareRowMapper, data);
    }

    public List<Vizualizare> findByStareFinalizata(boolean stare) {
        String sql = "SELECT v.*, f.titlu  FROM vizualizari v JOIN versiuni_filme vf ON v.id_versiune = vf.id_versiune JOIN filme f ON vf.id_film = f.id_film WHERE v.stare_finalizata = ?";
        return jdbcTemplate.query(sql, vizualizareRowMapper, stare);
    }

    public void salveazaVizualizare(Vizualizare v) {
        String sql = "INSERT INTO vizualizari (id_client, id_versiune, data_vizualizarii, durata_vizionata_minute) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, v.getIdClient(), v.getIdVersiune(), v.getDataVizualizarii(), v.getDurataVizionataMinute());
    }
}
