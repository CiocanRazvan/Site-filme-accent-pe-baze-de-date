package proiect.filmesgbd.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proiect.filmesgbd.entities.Film;
import proiect.filmesgbd.services.FilmService;

import java.util.List;

@RestController
@RequestMapping("/api/filme")
@CrossOrigin(origins = "*")
public class FilmController {
    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public List<Film> getAll() {
        return filmService.getAllFilme();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Film> getById(@PathVariable int id) {
        return ResponseEntity.ok(filmService.getFilmById(id));
    }

    @GetMapping("/cautare")
    public List<Film> search(
            @RequestParam(value = "text", required = false) String text,
            @RequestParam(value = "categorie", required = false) String categorie) {

        return filmService.cautaSmart(text, categorie);
    }
}
