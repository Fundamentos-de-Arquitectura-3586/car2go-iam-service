package com.car2go.car2go_iam_service.iam.application.internal.outboundservices.acl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ExternalVehicleService {

    private final RestTemplate restTemplate;
    private final String vehicleServiceUrl;

    public ExternalVehicleService(RestTemplate restTemplate, @Value("${vehicle.service.url}") String vehicleServiceUrl) {
        this.restTemplate = restTemplate;
        this.vehicleServiceUrl = vehicleServiceUrl;
    }

    public List<Long> fetchVehicleIdByUserId(long userId) {
        String url = "http://" + vehicleServiceUrl + "/api/vehicles/profile/" + userId + "/ids";

        ResponseEntity<List<Long>> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Long>>() {}
        );

        return response.getBody();
    }
}
