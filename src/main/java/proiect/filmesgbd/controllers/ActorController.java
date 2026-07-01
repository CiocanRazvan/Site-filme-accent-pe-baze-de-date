package proiect.filmesgbd.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proiect.filmesgbd.entities.Actor;
import proiect.filmesgbd.services.ActorService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/actori")
@CrossOrigin(origins = "*")
public class ActorController {
    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/cautare")
    public List<Actor> search(@RequestParam String nume) {
        return actorService.cautaActori(nume);
    }

    @GetMapping("/film")
    public List<Actor> getByFilmTitlu(@RequestParam String text) {
        return actorService.getActoriFilmTitlu(text);
    }

        @GetMapping("/filme/cautare")
        public ResponseEntity<List<Map<String, Object>>> searchFilmeByActor(@RequestParam String actor) {
            List<Map<String, Object>> filme = actorService.getFilmeDupaCautareActor(actor);
            return ResponseEntity.ok(filme);
        }
}
