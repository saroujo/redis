package com.task.redis;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.test.redis.xml.ConfigReader;
import com.test.redis.xml.ConfigResponse;
import com.test.redis.xml.XMLException;

public class ConfigReaderTest {

	@Test
	public void testStaticFile() {
		try {
			RedisService service = new RedisService();
			ConfigResponse resp = ConfigReader.read("config.xml");
			assertEquals(resp.getSubdomains().size(), 38);
			assertEquals(resp.getCookies().keySet().size(), 46);
			service.writeData(resp);
		} catch (XMLException e) {
			// TODO Auto-generated catch block
			System.out.print("Exception::" + e);
		}
	}

	@Test
	public void testWriteToRedis() {
		try {
			RedisService service = new RedisService();
			ConfigResponse resp = ConfigReader.read("config.xml");
			service.writeData(resp);
		} catch (XMLException e) {
			// TODO Auto-generated catch block
			System.out.print("Exception::" + e);
		}
	}
}
