package com.psawesome.zuulgateway.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * package: com.psawesome.zuulgateway.config
 * author: PS
 * DATE: 2020-02-02 일요일 22:51
 */
@Configuration
public class HystrixFallbackConfig {

    @Bean
    public FallbackProvider fallbackProvider() {
        return new FallbackProvider() {
            @Override
            public String getRoute() {
                // serviceId
                return "multiplication";
            }

            @Override
            public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
                return new ClientHttpResponse() {
                    @Override
                    public HttpStatus getStatusCode() throws IOException {
                        return HttpStatus.OK;
                    }

                    @Override
                    public int getRawStatusCode() throws IOException {
                        return HttpStatus.OK.value();
                    }

                    @Override
                    public String getStatusText() throws IOException {
                        return HttpStatus.OK.toString();
                    }

                    @Override
                    public void close() {

                    }

                    @Override
                    public InputStream getBody() throws IOException {
                        Map<String, String> jsonMap = new HashMap<>();
                        jsonMap.put("factorA", "죄송합니다. 서비스가 중단되었습니다.");
                        jsonMap.put("factorB", "");
                        jsonMap.put("id", "null");
                        return new ByteArrayInputStream(new ObjectMapper().writeValueAsString(jsonMap).getBytes());
                    }

                    @Override
                    public HttpHeaders getHeaders() {
                        HttpHeaders headers = new HttpHeaders();
                        headers.setContentType(MediaType.APPLICATION_JSON);
                        headers.setAccessControlAllowCredentials(true);
                        headers.setAccessControlAllowOrigin("*");
                        return headers;
                    }
                };
            }
        };
    }
}
