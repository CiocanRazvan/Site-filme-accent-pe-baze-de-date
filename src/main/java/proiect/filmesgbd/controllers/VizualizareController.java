package proiect.filmesgbd.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import proiect.filmesgbd.dto.VizualizareDTO;
import proiect.filmesgbd.entities.Vizualizare;
import proiect.filmesgbd.services.VizualizareService;

import java.util.List;

@RestController
@RequestMapping("/api/vizualizari")
@CrossOrigin(origins = "*")
public class VizualizareController {
    private final VizualizareService vizualizareService;

    public VizualizareController(VizualizareService vizualizareService) {
        this.vizualizareService = vizualizareService;
    }

    @GetMapping("/abandonate")
    public List<Vizualizare> getAbandoned() {
        return vizualizareService.getFilmeAbandonate();
    }

    @PostMapping
    public ResponseEntity<String> adauga(@RequestBody VizualizareDTO dto) {
        String emailLogat = SecurityContextHolder.getContext().getAuthentication().getName();
        try {
            vizualizareService.salveazaVizualizareSmart(dto, emailLogat);
            return ResponseEntity.ok("Vizualizare salvată cu succes!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Eroare: " + e.getMessage());
        }
    }
    @GetMapping("/client/{id}")
    public List<Vizualizare> getIstoric(@PathVariable int id) {
        return vizualizareService.getIstoricClient(id);
    }
}
