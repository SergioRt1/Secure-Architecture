package escuelaing.edu.arep.LabSecurity.repositories;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Repository
public class AuthorizationRepository {
    private final RestTemplate restTemplate;
    private final String authorizationServiceURL;
    private final HttpEntity entityWithHeaders;

    public AuthorizationRepository(RestTemplate restTemplate,
                                   @Value("${authorization-service.url}") String authorizationServiceURL,
                                   @Value("${authorization-service.api-key}") String authorizationServiceApiKey) {
        this.restTemplate = restTemplate;
        this.authorizationServiceURL = authorizationServiceURL;
        HttpHeaders headers = new HttpHeaders();
        headers.set("api-key", authorizationServiceApiKey);

        entityWithHeaders = new HttpEntity(headers);
    }


    public boolean isAuthorizeAsAdmin(String apiKey) {
        String URLRequest = authorizationServiceURL + "/api/authorization/admin/" + apiKey;

        Boolean response = restTemplate.exchange(URLRequest, HttpMethod.GET, entityWithHeaders, Boolean.class, Collections.emptyMap()).getBody();
        if (response != null) return response;

        throw new SecurityException("Can't authorize as admin");
    }

    public boolean isAuthorizeAsUser(String apiKey) {
        String URLRequest = authorizationServiceURL + "/api/authorization/user/" + apiKey;
        Boolean response = restTemplate.exchange(URLRequest, HttpMethod.GET, entityWithHeaders, Boolean.class, Collections.emptyMap()).getBody();
        if (response != null) return response;

        throw new SecurityException("Can't authorize as user");
    }

}
