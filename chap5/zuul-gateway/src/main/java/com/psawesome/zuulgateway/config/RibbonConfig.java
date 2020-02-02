package com.psawesome.zuulgateway.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import org.springframework.context.annotation.Bean;

/**
 * package: com.psawesome.zuulgateway.config
 * author: PS
 * DATE: 2020-02-02 ¿œø‰¿œ 20:21
 */
public class RibbonConfig {

    @Bean
    public IPing ribbonPing(final IClientConfig config) {
        return new PingUrl(false, "/actuator/health");
    }

    @Bean
    public IRule ribbonRule(final IClientConfig config) {
        return new AvailabilityFilteringRule();
    }
}
