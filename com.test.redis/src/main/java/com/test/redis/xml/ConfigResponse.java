package com.test.redis.xml;

import java.util.List;
import java.util.Map;

public class ConfigResponse {
	// List of subdomains
	private List<String> subdomains;
	// key would be cookie:<name>:<host>
	private Map<String, String> cookies;

	public List<String> getSubdomains() {
		return subdomains;
	}

	public void setSubdomains(List<String> subdomains) {
		this.subdomains = subdomains;
	}

	public Map<String, String> getCookies() {
		return cookies;
	}

	public void setCookies(Map<String, String> cookies) {
		this.cookies = cookies;
	}

	@Override
	public String toString() {
		return "ConfigResponse [subdomains=" + subdomains + ", cookies=" + cookies + "]";
	}
	
}
