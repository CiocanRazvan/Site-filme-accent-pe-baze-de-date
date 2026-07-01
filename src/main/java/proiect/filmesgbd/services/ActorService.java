package proiect.filmesgbd.services;

import org.springframework.stereotype.Service;
import proiect.filmesgbd.entities.Actor;
import proiect.filmesgbd.repositories.ActorRepository;

import java.util.List;
import java.util.Map;

@Service
public class ActorService {
    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public List<Actor> getActoriFilm(int idFilm) {
        return actorRepository.findByFilmId(idFilm);
    }

    public List<Actor> getActoriFilmTitlu(String text){
        return actorRepository.findByTitluFilm(text);
    }

    public List<Actor> cautaActori(String nume) {
        List<Actor> rezultate = actorRepository.findByNumeFamilie(nume);

        if(rezultate.isEmpty()){
            rezultate = actorRepository.findByPrenume(nume);
        }
        if(rezultate.isEmpty()){
            rezultate = actorRepository.findByNumeScena(nume);
        }
        if(rezultate.isEmpty()){
            rezultate = actorRepository.findByNumeComplet(nume);
        }
        if(rezultate.isEmpty()){
            rezultate = actorRepository.findByRolInDistributie(nume);
        }
        return rezultate;
    }

    public List<Map<String, Object>> getFilmeDupaCautareActor(String criteria) {
        return actorRepository.findFilmeByActorSearch(criteria);
    }


}
