package com.optimagrowth.licenseserver.contextholder;

import org.springframework.stereotype.Component;

/**
 * @author abishek on 2022-04-03
 */
@Component
public class UserContext {
    public static final String CORRELATION_ID = "tmx-correlation-id";
    public static final String AUTH_TOKEN = "tmz-auth-token";
    public static final String USER_ID="tmx-user_id";
    public static final String ORGANISATION_ID= "tmx-organisation-id";

    private String correlationId = new String();
    private String authToken = new String();
    private String userId = new String();
    private String organisationId  = new String();

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(String organisationId) {
        this.organisationId = organisationId;
    }
}
