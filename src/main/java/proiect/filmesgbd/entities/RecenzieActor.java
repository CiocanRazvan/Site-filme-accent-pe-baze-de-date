package proiect.filmesgbd.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecenzieActor {
    private Long idRecenzieActor;
    private Long idClient;
    private Long idActor;
    private String comentariu;
    private String optiunePredefinita;
    private String sentimentDetectat;

    private String numeClient;
    private String numeActor;
}