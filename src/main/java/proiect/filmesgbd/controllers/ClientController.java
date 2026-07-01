package proiect.filmesgbd.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proiect.filmesgbd.dto.LoginRequest;
import proiect.filmesgbd.entities.Client;
import proiect.filmesgbd.services.ClientService;

@RestController
@RequestMapping("/api/clienti")
@CrossOrigin(origins = "*")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/inregistrare")
    public ResponseEntity<String> register(@RequestBody Client client) {
        try {
            clientService.inregistrareClient(client);
            return ResponseEntity.ok("Client inregistrat cu succes!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            String token = clientService.login(loginRequest.email, loginRequest.parola);

            return ResponseEntity.ok(java.util.Map.of("token", token));
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @GetMapping("/email")
    public Client getByEmail(@RequestParam String email) {
        return clientService.getClientByEmail(email);
    }

}
