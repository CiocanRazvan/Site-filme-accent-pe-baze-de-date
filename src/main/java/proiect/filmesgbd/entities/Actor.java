package proiect.filmesgbd.entities;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Actor {

    private Long idActor;
    private String numeFamilie;
    private String prenume;
    private String numeScena;
    private LocalDate dataNasterii;
}
