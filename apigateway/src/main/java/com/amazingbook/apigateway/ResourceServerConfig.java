package com.amazingbook.apigateway;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class ResourceServerConfig {

	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
		httpSecurity.authorizeExchange().anyExchange().authenticated().and().oauth2ResourceServer().jwt();
		return httpSecurity.build();
	}

	@Bean
	public ReactiveJwtDecoder jwtDecoder() {
		byte[] keyInBytes = "123456AFHN123456AFHN123456AFHN56".getBytes();
		SecretKeySpec secretKeySpec = new SecretKeySpec(keyInBytes, 0, keyInBytes.length, "AES");
		return NimbusReactiveJwtDecoder.withSecretKey(secretKeySpec).build();
	}

}
