package proiect.filmesgbd.entities;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vizualizare {

    private Long idVizualizare;
    private Long idClient;
    private Long idVersiune;
    private LocalDateTime dataVizualizarii;
    private Integer durataVizionataMinute;
    private Boolean stareFinalizata;

    private String numeClient;
    private String titluFilm;
    private String formatVersiune;
}
