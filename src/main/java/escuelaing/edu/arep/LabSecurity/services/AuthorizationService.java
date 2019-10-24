package escuelaing.edu.arep.LabSecurity.services;

import escuelaing.edu.arep.LabSecurity.repositories.AuthorizationRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    private final AuthorizationRepository authorizationRepository;

    public AuthorizationService(AuthorizationRepository authorizationRepository) {
        this.authorizationRepository = authorizationRepository;
    }

    public boolean isAuthorizeAsAdmin(String apiKey) {
        return authorizationRepository.isAuthorizeAsAdmin(apiKey);
    }

    public boolean isAuthorizeAsUser(String apiKey) {
        return authorizationRepository.isAuthorizeAsUser(apiKey);
    }
}
