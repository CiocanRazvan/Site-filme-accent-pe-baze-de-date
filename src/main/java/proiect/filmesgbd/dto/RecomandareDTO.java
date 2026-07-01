package proiect.filmesgbd.dto;

import lombok.Data;

@Data
public class RecomandareDTO {
    private Long idFilm;
    private String titlu;
    private String categorie;
    private Double ratingMediu;
    private String motiv;
}
