package proiect.filmesgbd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proiect.filmesgbd.dto.VizualizareDTO;
import proiect.filmesgbd.entities.Vizualizare;
import proiect.filmesgbd.repositories.VizualizareRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VizualizareService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final VizualizareRepository vizualizareRepository;

    public VizualizareService(VizualizareRepository vizualizareRepository) {
        this.vizualizareRepository = vizualizareRepository;
    }

    public List<Vizualizare> getIstoricClient(int idClient) {
        return vizualizareRepository.findByClientId(idClient);
    }

    public List<Vizualizare> getFilmeAbandonate() {
        return vizualizareRepository.findByStareFinalizata(false);
    }

    @Transactional
    public void salveazaVizualizareSmart(VizualizareDTO dto, String email) {

        List<Long> ids = jdbcTemplate.queryForList(
                "SELECT id_client FROM clienti WHERE email = ?",
                Long.class,
                email
        );

        if (ids.isEmpty()) {
            throw new RuntimeException("Nu s-a găsit niciun client cu emailul: " + email);
        }

        Long idClient = ids.get(0);

        String sql = "INSERT INTO vizualizari (id_client, id_versiune, data_vizualizarii, durata_vizionata_minute, stare_finalizata) " +
                "VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                idClient,
                dto.getIdVersiune(),
                java.sql.Timestamp.valueOf(LocalDateTime.now()),
                dto.getDurataVizionataMinute(),
                dto.getStareFinalizata()
        );
    }
}

