package proiect.filmesgbd.controllers;

import org.springframework.web.bind.annotation.*;
import proiect.filmesgbd.entities.DistributieFilm;
import proiect.filmesgbd.services.DistributieFilmService;

import java.util.List;

@RestController
@RequestMapping("/api/distributie")
@CrossOrigin(origins = "*")
public class DistributieFilmController {
    private final DistributieFilmService distributieService;

    public DistributieFilmController(DistributieFilmService distributieService) {
        this.distributieService = distributieService;
    }

    @GetMapping("/film/{idFilm}")
    public List<DistributieFilm> getByFilm(@PathVariable int idFilm) {
        return distributieService.getDistributieByFilm(idFilm);
    }

    @GetMapping("/sentiment-actor")
    public String getActorSentiment(@RequestParam int idFilm, @RequestParam int idActor) {
        return distributieService.getAnalizaSentimentPrestatie(idFilm, idActor);
    }
}
