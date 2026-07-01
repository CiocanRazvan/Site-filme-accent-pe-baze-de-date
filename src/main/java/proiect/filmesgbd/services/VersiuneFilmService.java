package proiect.filmesgbd.services;

import org.springframework.stereotype.Service;
import proiect.filmesgbd.entities.VersiuneFilm;
import proiect.filmesgbd.repositories.VersiuneFilmRepository;

import java.util.List;

@Service
public class VersiuneFilmService {
    private final VersiuneFilmRepository versiuneFilmRepository;

    public VersiuneFilmService(VersiuneFilmRepository versiuneFilmRepository) {
        this.versiuneFilmRepository = versiuneFilmRepository;
    }

    public List<VersiuneFilm> getFormateDisponibile(int idFilm) {
        return versiuneFilmRepository.findByIdFilm(idFilm);
    }
}
