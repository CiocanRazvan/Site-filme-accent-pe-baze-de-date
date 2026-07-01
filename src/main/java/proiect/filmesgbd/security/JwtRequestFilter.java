package proiect.filmesgbd.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        // Verificăm dacă header-ul conține "Bearer [token]"
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            try {
                username = jwtUtil.extractEmail(jwt);
            } catch (Exception e) {
                logger.warn("Token invalid sau expirat");
            }
        }

        // Dacă avem user și nu este deja autentificat în sesiune
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (jwtUtil.validateToken(jwt, username)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        username, null, new ArrayList<>());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Îi spunem lui Spring: "Omul ăsta e cine zice că e, lasă-l să treacă!"
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        chain.doFilter(request, response);
    }

    /*@Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI(); // Folosim URI complet pentru siguranță
        return path.contains("/api/recenzii/titlu") ||
                path.contains("/api/filme") ||
                path.contains("/api/recenzii/sentiment");
    }*/

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {

        String path = request.getServletPath();
        String method = request.getMethod();

        // POST-urile la recenzii TREBUIE filtrate
        if (path.startsWith("/api/recenzii") && method.equals("POST")) {
            return false;
        }

        return path.startsWith("/api/clienti/login") ||
                path.startsWith("/api/clienti/inregistrare") ||
                path.startsWith("/api/filme") ||
                path.startsWith("/api/actori") ||
                path.startsWith("/api/distributie") ||
                path.startsWith("/api/versiuni") ||
                path.startsWith("/api/vizualizari/abandonate") ||
                path.matches("/api/vizualizari/istoric/.*");
    }
}
