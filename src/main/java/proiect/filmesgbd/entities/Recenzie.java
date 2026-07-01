    package proiect.filmesgbd.entities;


    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Recenzie {

        private Long idRecenzie;
        private Long idClient;
        private Long idFilm;
        private Integer vot;
        private String comentariu;
        private String optiunePredefinita;
        private String sentimentDetectat;

        private String numeClient;
        private String titluFilm;
        private Long idActor;
        private String numeActor;
    }
