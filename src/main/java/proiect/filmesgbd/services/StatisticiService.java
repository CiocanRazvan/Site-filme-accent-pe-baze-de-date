// StatisticiService.java
package proiect.filmesgbd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.filmesgbd.dto.*;
import proiect.filmesgbd.repositories.StatisticiRepository;

import java.util.List;

@Service
public class StatisticiService {

    @Autowired
    private StatisticiRepository statisticiRepository;

    public ProfilCinematograficDTO getProfilCinematografic(Long idClient) {
        try {
            return statisticiRepository.getProfilCinematografic(idClient);
        } catch (Exception e) {
            return new ProfilCinematograficDTO();
        }
    }

    public List<RecomandareDTO> getRecomandari(Long idClient) {
        return statisticiRepository.getRecomandari(idClient);
    }

    public List<PredictieSezoniereDTO> getPredictiiSezoniere() {
        return statisticiRepository.getPredictiiSezoniere();
    }

    public List<GrupClientDTO> getGrupareClienti() {
        return statisticiRepository.getGrupareClienti();
    }
}