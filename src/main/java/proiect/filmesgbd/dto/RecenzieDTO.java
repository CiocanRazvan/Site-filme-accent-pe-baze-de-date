package proiect.filmesgbd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecenzieDTO {
    private String titluFilm;
    private Integer vot;
    private String comentariu;
    private String optiunePredefinita;
}