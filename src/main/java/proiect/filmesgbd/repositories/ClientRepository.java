package proiect.filmesgbd.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import proiect.filmesgbd.entities.Client;

import java.time.LocalDate;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;


@Repository
public class ClientRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Client> clientRowMapper = (rs, rowNum) -> new Client(
            rs.getLong("id_client"),
            rs.getString("nume"),
            rs.getString("prenume"),
            rs.getString("email"),
            rs.getString("telefon_mobil"),
            rs.getString("adresa"),
            rs.getString("oras"),
            rs.getDate("data_inregistrarii").toLocalDate(),
            rs.getString("parola")
    );

    public List<Client> findAll() {
        String sql = "SELECT * FROM clienti ORDER BY nume, prenume";
        return jdbcTemplate.query(sql, clientRowMapper);
    }

    public List<Client> findByNume(String nume) {
        String sql = "SELECT * FROM clienti WHERE nume ILIKE ?";
        return jdbcTemplate.query(sql, clientRowMapper, "%" + nume + "%");
    }

    public List<Client> findByPrenume(String prenume) {
        String sql = "SELECT * FROM clienti WHERE prenume ILIKE ?";
        return jdbcTemplate.query(sql, clientRowMapper, "%" + prenume + "%");
    }

    public Client findByEmail(String email) {
        String sql = "SELECT * FROM clienti WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, clientRowMapper, email);
    }

    public List<Client> findByTelefon(String telefon) {
        String sql = "SELECT * FROM clienti WHERE telefon_mobil LIKE ?";
        return jdbcTemplate.query(sql, clientRowMapper, "%" + telefon + "%");
    }

    public List<Client> findByDataInregistrarii(LocalDate data) {
        String sql = "SELECT * FROM clienti WHERE data_inregistrarii::date = ?";
        return jdbcTemplate.query(sql, clientRowMapper, data);
    }

    public void save(Client client) {
        String sql = "INSERT INTO clienti (nume, prenume, email, telefon_mobil, adresa, oras, data_inregistrarii, parola)  VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                client.getNume(),
                client.getPrenume(),
                client.getEmail(),
                client.getNumarTelefon(),
                client.getAdresa(),
                client.getOras(),
                java.sql.Date.valueOf(LocalDate.now()),
                client.getParola()
        );
    }
}
