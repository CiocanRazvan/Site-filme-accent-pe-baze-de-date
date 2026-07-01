package proiect.filmesgbd.dto;

import lombok.Data;

@Data
public class GrupClientDTO {
    private Long idClient;
    private String numeClient;
    private String email;
    private String categorieDominanta;
    private String sentimentDominant;
    private Long nrVizualizari;
    private Double votMediu;
    private String frecventa;
    private String preferintaActor;
    private String grup;
}