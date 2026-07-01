package proiect.filmesgbd.dto;

import lombok.Data;

@Data
public class ProfilCinematograficDTO {
    private String categoriePreferata;
    private String actorFavorit;
    private String sentimentDominant;
    private Long totalVizualizari;
    private Long totalRecenzii;
    private Long vizualizariAbandonate;
    private Long minuteTotale;
}