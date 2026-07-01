package proiect.filmesgbd.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import proiect.filmesgbd.dto.*;

import java.util.List;

@Repository
public class StatisticiRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ProfilCinematograficDTO getProfilCinematografic(Long idClient) {
        String sql = "SELECT * FROM profil_cinematografic(?)";
        return jdbcTemplate.queryForObject(sql, (rs, rn) -> {
            ProfilCinematograficDTO dto = new ProfilCinematograficDTO();
            dto.setCategoriePreferata(rs.getString("categorie_preferata"));
            dto.setActorFavorit(rs.getString("actor_favorit"));
            dto.setSentimentDominant(rs.getString("sentiment_dominant"));
            dto.setTotalVizualizari(rs.getLong("total_vizualizari"));
            dto.setTotalRecenzii(rs.getLong("total_recenzii"));
            dto.setVizualizariAbandonate(rs.getLong("vizualizari_abandonate"));
            dto.setMinuteTotale(rs.getLong("minute_totale"));
            return dto;
        }, idClient);
    }

    public List<RecomandareDTO> getRecomandari(Long idClient) {
        String sql = "SELECT * FROM recomandari_client(?)";
        return jdbcTemplate.query(sql, (rs, rn) -> {
            RecomandareDTO dto = new RecomandareDTO();
            dto.setIdFilm(rs.getLong("id_film"));
            dto.setTitlu(rs.getString("titlu"));
            dto.setCategorie(rs.getString("categorie"));
            dto.setRatingMediu(rs.getDouble("rating_mediu"));
            dto.setMotiv(rs.getString("motiv"));
            return dto;
        }, idClient);
    }

    public List<PredictieSezoniereDTO> getPredictiiSezoniere() {
        String sql = "SELECT * FROM predictii_sezoniere()";
        return jdbcTemplate.query(sql, (rs, rn) -> {
            PredictieSezoniereDTO dto = new PredictieSezoniereDTO();
            dto.setLuna(rs.getInt("luna"));
            dto.setLunaNume(rs.getString("luna_nume").trim());
            dto.setCategorie(rs.getString("categorie"));
            dto.setNumarVizualizari(rs.getLong("numar_vizualizari"));
            dto.setFilmPopular(rs.getString("film_popular"));
            dto.setTipRezultat(rs.getString("tip_rezultat"));
            return dto;
        });
    }

    public List<GrupClientDTO> getGrupareClienti() {
        String sql = "SELECT * FROM grupare_clienti()";
        return jdbcTemplate.query(sql, (rs, rn) -> {
            GrupClientDTO dto = new GrupClientDTO();
            dto.setIdClient(rs.getLong("id_client"));
            dto.setNumeClient(rs.getString("nume_client"));
            dto.setEmail(rs.getString("email"));
            dto.setCategorieDominanta(rs.getString("categorie_dominanta"));
            dto.setSentimentDominant(rs.getString("sentiment_dominant"));
            dto.setNrVizualizari(rs.getLong("nr_vizualizari"));
            dto.setVotMediu(rs.getDouble("vot_mediu"));           // ← nou
            dto.setFrecventa(rs.getString("frecventa"));           // ← nou
            dto.setPreferintaActor(rs.getString("preferinta_actor")); // ← nou
            dto.setGrup(rs.getString("grup"));
            return dto;
        });
    }
}