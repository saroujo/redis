package com.test.redis.xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

// read the config.xml file
public class ConfigReader {

	public static final String SUBDOMAIN = "subdomain";
	public static final String COOKIE = "cookie";

	public static final String COOKIE_NAME = "name";
	public static final String COOKIE_HOST = "host";

	public static ConfigResponse read(String fileName) throws XMLException {
		// Instantiate the Factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			// optional, but recommended
			// process XML securely, avoid attacks like XML External Entities (XXE)
			dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			// parse XML file
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new File(fileName));
			// optional, but recommended
			// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();
			// TODO: Add check on document size & if nodes exist
			ConfigResponse response = new ConfigResponse();
			List<String> subdomainsInResponse = new ArrayList<String>();
			Map<String, String> cookiesInResponse = new HashMap<String, String>();
			NodeList subdomainList = doc.getElementsByTagName(SUBDOMAIN);
			NodeList cookiesList = doc.getElementsByTagName(COOKIE);
			for (int i = 0; i < subdomainList.getLength(); i++) {
				Node subdomain = (Node) subdomainList.item(i);
				String subdomainValue = subdomain.getTextContent();
				// System.out.println("subdomainValue:::" + subdomainValue);
				subdomainsInResponse.add(subdomainValue);
			}
			for (int i = 0; i < cookiesList.getLength(); i++) {
				Element cookie = (Element) cookiesList.item(i);
				String name = cookie.getAttribute(COOKIE_NAME);
				String host = cookie.getAttribute(COOKIE_HOST);
				String key = "cookie:" + name + ":" + host;
				String cookieValue = cookie.getTextContent();
				// System.out.println("cookieValue:::" + cookieValue);
				cookiesInResponse.put(key, cookieValue);
			}
			response.setSubdomains(subdomainsInResponse);
			response.setCookies(cookiesInResponse);
			return response;

		} catch (ParserConfigurationException | SAXException | IOException e) {
			throw new XMLException(e);
		}

	}
}
