package com.vikram.openidconnect.google;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.http.HttpException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vikram.http.HttpClientUtil;
import com.vikram.openidconnect.IOpenConnectDiscovery;


@JsonIgnoreProperties(ignoreUnknown=true)
public class GoogleOpenConnectDiscovery implements IOpenConnectDiscovery {

	private static String URL = "https://accounts.google.com/.well-known/openid-configuration";
	
	private String issuer;
	private String authorization_endpoint;
	private String token_endpoint;
	private String userinfo_endpoint;
	private String revocation_endpoint;
	private String jwks_uri;
	private String[] response_types_supported;
	private String[] subject_types_supported;
	private String[] id_token_alg_values_supported;
	
	@Autowired
	private HttpClientUtil httpClientUtil;
	
	@PostConstruct
	public void init() throws JsonParseException, JsonMappingException, IOException, HttpException {
		String jsonResponse = httpClientUtil.getJSONResponse(URL);
		ObjectMapper mapper = new ObjectMapper();		
		BeanUtils.copyProperties(mapper.readValue(jsonResponse, GoogleOpenConnectDiscovery.class), this);
	}

	
	public String getIssuer() {
		return issuer;
	}


	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}


	public String getAuthorization_endpoint() {
		return authorization_endpoint;
	}


	public void setAuthorization_endpoint(String authorization_endpoint) {
		this.authorization_endpoint = authorization_endpoint;
	}


	public String getToken_endpoint() {
		return token_endpoint;
	}


	public void setToken_endpoint(String token_endpoint) {
		this.token_endpoint = token_endpoint;
	}


	public String getUserinfo_endpoint() {
		return userinfo_endpoint;
	}


	public void setUserinfo_endpoint(String userinfo_endpoint) {
		this.userinfo_endpoint = userinfo_endpoint;
	}


	public String getRevocation_endpoint() {
		return revocation_endpoint;
	}


	public void setRevocation_endpoint(String revocation_endpoint) {
		this.revocation_endpoint = revocation_endpoint;
	}


	public String getJwks_uri() {
		return jwks_uri;
	}


	public void setJwks_uri(String jwks_uri) {
		this.jwks_uri = jwks_uri;
	}


	public String[] getResponse_types_supported() {
		return response_types_supported;
	}


	public void setResponse_types_supported(String[] response_types_supported) {
		this.response_types_supported = response_types_supported;
	}


	public String[] getSubject_types_supported() {
		return subject_types_supported;
	}


	public void setSubject_types_supported(String[] subject_types_supported) {
		this.subject_types_supported = subject_types_supported;
	}


	public String[] getId_token_alg_values_supported() {
		return id_token_alg_values_supported;
	}


	public void setId_token_alg_values_supported(
			String[] id_token_alg_values_supported) {
		this.id_token_alg_values_supported = id_token_alg_values_supported;
	}
	
	public void setHttpUtil(HttpClientUtil util){
		this.httpClientUtil = util;
	}
}
