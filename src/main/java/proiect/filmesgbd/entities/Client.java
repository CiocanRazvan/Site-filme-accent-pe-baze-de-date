package proiect.filmesgbd.entities;



import lombok.*;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    private Long idClient;
    private String nume;
    private String prenume;
    private String email;
    private String numarTelefon;
    private String adresa;
    private String oras;
    private LocalDate dataInregistrarii;

    private String parola;

    public void setLong(long idClient) {
    }
}
