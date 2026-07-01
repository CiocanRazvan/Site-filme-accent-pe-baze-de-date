package proiect.filmesgbd.services;

import org.springframework.stereotype.Service;
import proiect.filmesgbd.entities.DistributieFilm;
import proiect.filmesgbd.repositories.DistributieFilmRepository;

import java.util.List;

@Service
public class DistributieFilmService {
    private final DistributieFilmRepository distributieFilmRepository;

    public DistributieFilmService(DistributieFilmRepository distributieFilmRepository) {
        this.distributieFilmRepository = distributieFilmRepository;
    }

    public List<DistributieFilm> getAllDistributii() {
        return distributieFilmRepository.findAll();
    }

    public List<DistributieFilm> getDistributieByFilm(int idFilm) {
        List<DistributieFilm> lista = distributieFilmRepository.findByFilmId(idFilm);
        if (lista.isEmpty()) {
            System.out.println("Nu s-au găsit actori pentru filmul cu ID: " + idFilm);
        }
        return lista;
    }

    public List<DistributieFilm> getRoluriActor(int idActor) {
        return distributieFilmRepository.findByActorId(idActor);
    }

    public String getAnalizaSentimentPrestatie(int idFilm, int idActor) {
        try {
            return distributieFilmRepository.getSentimentPrestatie(idFilm, idActor);
        } catch (Exception e) {
            return "NEUTRU (Eroare la analiza sau comentariu lipsa)";
        }
    }

    public List<DistributieFilm> cautaDupaRol(String rol) {
        return distributieFilmRepository.findByRol(rol);
    }
}
