package proiect.filmesgbd.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VersiuneFilm {

    private Long idVersiune;
    private Long idFilm;
    private String format;
    private String limba;
    private String rezolutie;

    private String titluFilm;
}
