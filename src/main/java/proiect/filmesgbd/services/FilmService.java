package proiect.filmesgbd.services;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import proiect.filmesgbd.entities.Film;
import proiect.filmesgbd.repositories.FilmRepository;

import java.util.List;

@Service
public class FilmService {
    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Film> getAllFilme() {
        return filmRepository.findAll();
    }

    public Film getFilmById(int id) {
        try {
            return filmRepository.findById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("Filmul cu ID-ul " + id + " nu a fost găsit.");
        }
    }

    public List<Film> cautaInteligent(String text){
        return filmRepository.findByTitlu(text);
    }

    public List<Film> cautaDupaCategorie(String text){
        return filmRepository.findByCategorie(text);
    }

    public List<Film> cautaSmart(String titlu, String categorie) {
        boolean areTitlu = titlu != null && !titlu.trim().isEmpty();
        boolean areCategorie = categorie != null && !categorie.trim().isEmpty();

        if (!areTitlu && !areCategorie) {
            return filmRepository.findAll();
        }
        if (areTitlu && !areCategorie) {
            return filmRepository.findByTitlu(titlu);
        }
        if (!areTitlu && areCategorie) {
            return filmRepository.findByCategorie(categorie);
        }
        return filmRepository.cautaDupaTitluSiCategorie(titlu, categorie);
    }

}
