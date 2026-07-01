package proiect.filmesgbd.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Film {
    private Long idFilm;
    private String titlu;
    private String descriere;
    private String categorie;
    private LocalDate dataLansarii;
    private Double ratingMediu;
}
