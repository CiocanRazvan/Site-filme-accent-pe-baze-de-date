package proiect.filmesgbd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proiect.filmesgbd.dto.RecenzieActorDTO;
import proiect.filmesgbd.dto.RecenzieDTO;
import proiect.filmesgbd.entities.Recenzie;
import proiect.filmesgbd.entities.RecenzieActor;
import proiect.filmesgbd.repositories.RecenzieRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RecenzieService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private proiect.filmesgbd.repositories.RecenzieActorRepository recenzieActorRepository;

    private final RecenzieRepository recenzieRepository;

    public RecenzieService(RecenzieRepository recenzieRepository) {
        this.recenzieRepository = recenzieRepository;
    }

    public List<Object> getRecenziiDupaSentiment(String sentiment) {

        List<Recenzie> filme =
                recenzieRepository.findBySentiment(sentiment);

        List<RecenzieActor> actori =
                recenzieActorRepository.findBySentiment(sentiment);

        List<Object> toate = new ArrayList<>();

        toate.addAll(filme);
        toate.addAll(actori);

        return toate;
    }

    public String analizeazaTextRecenzie(String text) {
        return recenzieRepository.getSentiment(text);
    }


    public void adaugaRecenzie(Recenzie recenzie) {
        try {
            recenzieRepository.adaugaRecenzie(recenzie);
        } catch (DataAccessException e) {
            throw new RuntimeException("Nu s-a putut adăuga recenzia: " + e.getMostSpecificCause().getMessage());
        }
    }

    @Transactional
    public void salveazaRecenzieSmart(RecenzieDTO dto, String emailClient) {
        List<Long> filmIds = jdbcTemplate.queryForList(
                "SELECT id_film FROM filme WHERE titlu ILIKE ? LIMIT 1",
                Long.class,
                "%" + dto.getTitluFilm().trim() + "%"
        );

        if (filmIds.isEmpty()) {
            throw new RuntimeException(
                    "Filmul '" + dto.getTitluFilm() + "' nu a fost găsit."
            );
        }

        Long idFilm = filmIds.get(0);

        List<Long> clientIds = jdbcTemplate.queryForList(
                "SELECT id_client FROM clienti WHERE email = ?",
                Long.class, emailClient);
        if (clientIds.isEmpty()) {
            throw new RuntimeException("Sesiune invalidă. Nu s-a găsit clientul cu emailul: " + emailClient);
        }
        Long idClient = clientIds.get(0);

        Recenzie recenzieNoua = new Recenzie();

        recenzieNoua.setIdFilm(idFilm);
        recenzieNoua.setIdClient(idClient);
        recenzieNoua.setVot(dto.getVot());
        recenzieNoua.setComentariu(dto.getComentariu());
        recenzieNoua.setOptiunePredefinita(dto.getOptiunePredefinita());

        this.adaugaRecenzie(recenzieNoua);
    }

    public List<Map<String, Object>> getRecenziiDupaTitlu(String titlu) {
        return recenzieRepository.findByTitluFilm(titlu);
    }

    public List<Recenzie> getRecenziiClient(int idClient) {
        return recenzieRepository.findByClientId(idClient);
    }

    @Transactional
    public void salveazaRecenzieActorSmart(RecenzieActorDTO dto, String emailClient) {
        List<Long> actorIds = jdbcTemplate.queryForList(
                "SELECT id_actor FROM actori WHERE nume_familie ILIKE ? AND prenume ILIKE ? LIMIT 1",
                Long.class,
                 "%" + dto.getNume().trim() + "%",
                "%" + dto.getPrenume().trim() + "%"
        );
        if (actorIds.isEmpty()) {
            throw new RuntimeException(
                    "Actorul '" + dto.getPrenume() + " " + dto.getNume() + "' nu a fost găsit în catalog."
            );
        }
        Long idActor = actorIds.get(0);

        List<Long> clientIds = jdbcTemplate.queryForList(
                "SELECT id_client FROM clienti WHERE email = ?",
                Long.class, emailClient);

        if (clientIds.isEmpty()) {
            throw new RuntimeException("Sesiune invalidă. Nu s-a găsit niciun client cu emailul: " + emailClient);
        }
        Long idClient = clientIds.get(0);

        String sentiment = recenzieRepository.getSentiment(dto.getComentariu());

        RecenzieActor ra = new RecenzieActor();
        ra.setIdClient(idClient);
        ra.setIdActor(idActor);
        ra.setComentariu(dto.getComentariu());
        ra.setOptiunePredefinita(dto.getOptiunePredefinita());
        ra.setSentimentDetectat(sentiment);

        recenzieActorRepository.salveaza(ra);
    }

    public List<RecenzieActor> getRecenziiActor(Long idActor) {
        return recenzieActorRepository.findByActorId(idActor);
    }

    public List<RecenzieActor> getRecenziiActorClient(Long idClient) {
        return recenzieActorRepository.findByClientId(idClient);
    }

    public String getSentimentMediuFilm(String titlu) {
        return recenzieRepository.findSentimentMediuFilm(titlu);
    }

    public String getSentimentMediuActor(String prenume, String numeFamilie) {
        return recenzieActorRepository.findSentimentMediuActor(prenume, numeFamilie);
    }

    public String getSentimentCategorie(String categorie) {
        return recenzieRepository.getSentimentCategorie(categorie);
    }

}
