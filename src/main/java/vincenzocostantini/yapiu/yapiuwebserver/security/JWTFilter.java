package vincenzocostantini.yapiu.yapiuwebserver.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import vincenzocostantini.yapiu.yapiuwebserver.entities.users.Utente;
import vincenzocostantini.yapiu.yapiuwebserver.exceptions.InvalidTokenException;
import vincenzocostantini.yapiu.yapiuwebserver.exceptions.UnauthorizedException;
import vincenzocostantini.yapiu.yapiuwebserver.repository.UtenteRepository;

import java.io.IOException;
import java.util.Optional;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    UtenteRepository utenteRepository;

    @Autowired
    private JWTTools jwtTools;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        AntPathMatcher pathMatcher = new AntPathMatcher();
        return pathMatcher.match("/admin/**", request.getServletPath()) ||
                pathMatcher.match("/login/**", request.getServletPath()) ||
                pathMatcher.match("/customer/**", request.getServletPath());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Per favore inserisci il token nell'Authorization Header");
        }
        String accessToken = authHeader.substring(7);
        try {
            jwtTools.verifyToken(accessToken);
        } catch (InvalidTokenException e) {
            throw new RuntimeException(e);
        }

        String id = jwtTools.extractIdFromToken(accessToken);

        Optional<Utente> currentUser = this.utenteRepository.findById(id);

        if (!currentUser.isPresent()) {
            throw new UnauthorizedException("Utente non trovato");
        } else {
        Authentication authentication = new UsernamePasswordAuthenticationToken(currentUser, null, currentUser.get().getAuthorities());;
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
        }
    }
}
