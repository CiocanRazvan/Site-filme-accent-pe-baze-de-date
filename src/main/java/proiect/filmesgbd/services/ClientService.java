package proiect.filmesgbd.services;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import proiect.filmesgbd.entities.Client;
import proiect.filmesgbd.repositories.ClientRepository;
import proiect.filmesgbd.security.JwtUtil;


@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public ClientService(ClientRepository clientRepository,BCryptPasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public void inregistrareClient(Client client) {
        try {
            String hash = passwordEncoder.encode(client.getParola());
            client.setParola(hash);
            clientRepository.save(client);
        } catch (DataAccessException e) {
            throw new RuntimeException("Eroare la salvare client: " + e.getMostSpecificCause().getMessage());
        }
    }

    public Client getClientByEmail(String email) {
        try {
            return clientRepository.findByEmail(email);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public String login(String email, String parolaSimpla) {
        Client client = clientRepository.findByEmail(email);
        if (client != null && passwordEncoder.matches(parolaSimpla, client.getParola())) {
            return jwtUtil.generateToken(email);
        }
        throw new RuntimeException("Email sau parolă incorectă!");
    }
}
