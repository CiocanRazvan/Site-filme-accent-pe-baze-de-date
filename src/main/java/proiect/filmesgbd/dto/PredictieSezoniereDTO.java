package proiect.filmesgbd.dto;

import lombok.Data;

@Data
public class PredictieSezoniereDTO {
    private Integer luna;
    private String lunaNume;
    private String categorie;
    private Long numarVizualizari;
    private String filmPopular;
    private String tipRezultat;
}