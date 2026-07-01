package proiect.filmesgbd.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import proiect.filmesgbd.dto.RecenzieDTO;
import proiect.filmesgbd.entities.Client;
import proiect.filmesgbd.entities.Recenzie;
import proiect.filmesgbd.entities.RecenzieActor;
import proiect.filmesgbd.services.RecenzieService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recenzii")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RecenzieController {

    private final RecenzieService recenzieService;

    public RecenzieController(RecenzieService recenzieService) {
        this.recenzieService = recenzieService;
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody RecenzieDTO recenzieDto) {
        String emailLogat = SecurityContextHolder.getContext().getAuthentication().getName();

        try {
            recenzieService.salveazaRecenzieSmart(recenzieDto, emailLogat);
            return ResponseEntity.ok("Recenzie adăugată cu succes!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Eroare: " + e.getMessage());
        }
    }

    @GetMapping("/sentiment")
    public List<Object> getBySentiment(@RequestParam String tip) {
        return recenzieService.getRecenziiDupaSentiment(tip);
    }

    @PostMapping("/analiza-rapida")
    public String analyzeText(@RequestBody String text) {
        return recenzieService.analizeazaTextRecenzie(text);
    }

    @GetMapping("/titlu")
    public List<Map<String, Object>> getByTitlu(@RequestParam String titlu) {
        return recenzieService.getRecenziiDupaTitlu(titlu);
    }

    @GetMapping("/client/{idClient}")
    public List<Recenzie> getByClient(@PathVariable int idClient) {
        return recenzieService.getRecenziiClient(idClient);
    }

    @PostMapping("/actor")
    public ResponseEntity<String> addActorReview(@RequestBody proiect.filmesgbd.dto.RecenzieActorDTO dto) {
        String emailLogat = SecurityContextHolder.getContext().getAuthentication().getName();
        try {
            recenzieService.salveazaRecenzieActorSmart(dto, emailLogat);
            return ResponseEntity.ok("Recenzia pentru actor a fost adăugată cu succes!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Eroare: " + e.getMessage());
        }
    }

    @GetMapping("/actor")
    public List<RecenzieActor> getByActor(@RequestParam Long idActor) {
        return recenzieService.getRecenziiActor(idActor);
    }

    @GetMapping("/actor/client/{id}")
    public List<RecenzieActor> getRecenziiActorClient(@PathVariable Long id) {
        return recenzieService.getRecenziiActorClient(id);
    }

    @GetMapping("/sentiment-film")
    public ResponseEntity<String> getSentimentFilm(@RequestParam String titlu) {
        try {
            String sentiment = recenzieService.getSentimentMediuFilm(titlu);
            return ResponseEntity.ok(sentiment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Eroare la calcularea sentimentului pentru film: " + e.getMessage());
        }
    }

    @GetMapping("/sentiment-actor")
    public ResponseEntity<String> getSentimentActor(
            @RequestParam String prenume,
            @RequestParam String numeFamilie) {
        try {
            String sentiment = recenzieService.getSentimentMediuActor(prenume, numeFamilie);
            return ResponseEntity.ok(sentiment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Eroare la calcularea sentimentului pentru actor: " + e.getMessage());
        }
    }

    @GetMapping("/sentiment-categorie")
    public String getSentimentCategorie(@RequestParam String categorie) {
        return recenzieService.getSentimentCategorie(categorie);
    }
}
