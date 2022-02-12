package com.task.redis;

import com.test.redis.xml.ConfigReader;
import com.test.redis.xml.ConfigResponse;
import com.test.redis.xml.XMLException;

public class DataProcessor {

	public static void main(String[] args) {
		String filePath = args[0];
		try {
			ConfigResponse response = ConfigReader.read(filePath);
			RedisService service = new RedisService();
			service.writeData(response);
		} catch (XMLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
