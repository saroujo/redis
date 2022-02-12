package com.task.redis;

import java.util.List;

import com.test.redis.xml.ConfigResponse;

import redis.clients.jedis.Jedis;

public class RedisService {
	
	public RedisService() {
		jedis = new Jedis();
	}
	
	Jedis jedis = null;
	
	
	public void writeData(ConfigResponse response) {
		List<String> subdomains = response.getSubdomains();
		String[] subDomainArray = new String[subdomains.size()];
		subDomainArray = subdomains.toArray(subDomainArray);
		jedis.lpush("subdomains", subDomainArray);
		for(String key : response.getCookies().keySet()) {
			jedis.set(key, response.getCookies().get(key));
		}	
	}
}
