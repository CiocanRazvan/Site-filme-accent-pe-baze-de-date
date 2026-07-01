package proiect.filmesgbd.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistributieFilm {

    private Long idFilm;
    private Long idActor;
    private String rol;
    private String observatiiPrestatie;

    private String titluFilm;
    private String numeScena;

    private String prenume;
    private String numeFamilie;
}
