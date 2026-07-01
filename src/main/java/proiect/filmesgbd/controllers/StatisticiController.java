// StatisticiController.java
package proiect.filmesgbd.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import proiect.filmesgbd.dto.*;
import proiect.filmesgbd.services.StatisticiService;

import java.util.List;

@RestController
@RequestMapping("/api/statistici")
@CrossOrigin(origins = "*")
public class StatisticiController {

    @Autowired
    private StatisticiService statisticiService;

    @GetMapping("/profil/{idClient}")
    public ProfilCinematograficDTO getProfilCinematografic(@PathVariable Long idClient) {
        return statisticiService.getProfilCinematografic(idClient);
    }

    @GetMapping("/recomandari/{idClient}")
    public List<RecomandareDTO> getRecomandari(@PathVariable Long idClient) {
        return statisticiService.getRecomandari(idClient);
    }

    @GetMapping("/predictii")
    public List<PredictieSezoniereDTO> getPredictiiSezoniere() {
        return statisticiService.getPredictiiSezoniere();
    }

    @GetMapping("/grupuri")
    public List<GrupClientDTO> getGrupareClienti() {
        return statisticiService.getGrupareClienti();
    }
}