package proiect.filmesgbd.controllers;

import org.springframework.web.bind.annotation.*;
import proiect.filmesgbd.entities.VersiuneFilm;
import proiect.filmesgbd.services.VersiuneFilmService;

import java.util.List;

@RestController
@RequestMapping("/api/versiuni")
@CrossOrigin(origins = "*")
public class VersiuneFilmController {
    private final VersiuneFilmService versiuneService;

    public VersiuneFilmController(VersiuneFilmService versiuneService) {
        this.versiuneService = versiuneService;
    }

    @GetMapping("/film/{idFilm}")
    public List<VersiuneFilm> getFormats(@PathVariable int idFilm) {
        return versiuneService.getFormateDisponibile(idFilm);
    }
}
