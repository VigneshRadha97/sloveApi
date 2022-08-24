package org.testrunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.junit.Assert;
import org.pojo.readCommmons;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class readConstants {
	private Properties properties;
	private final String constantPath = "C:\\Users\\Windows\\git\\newapi\\SloveApi\\Properties\\constants.properties";

	public readConstants() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(constantPath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getBaseURL() {
		String baseurl = properties.getProperty("BaseURL");
		if (baseurl != null)
			return baseurl;
		else
			throw new RuntimeException("Base URL path is not found in the constants file");
	}

	public String getUserRegistrationPath() {
		String userregisterationpath = properties.getProperty("UserRegistrationPath");
		if (userregisterationpath != null)
			return userregisterationpath;
		else
			throw new RuntimeException("User Registration Path parameter is not found in constants file");
	}

	public String getUserLoginPath() {
		String userloginpath = properties.getProperty("UserLoginPath");
		if (userloginpath != null)
			return userloginpath;
		else
			throw new RuntimeException("User Login Path parameter is not found in constants file");
	}

	public String getAUserPath() {
		String getauserpath = properties.getProperty("GetUserPath");
		if (getauserpath != null)
			return getauserpath;
		else
			throw new RuntimeException("Get a User Path parameter is not found in constants file");
	}

	public String getAllUsersPath() {
		String getalluserspath = properties.getProperty("GetAllUsers");
		if (getalluserspath != null)
			return getalluserspath;
		else
			throw new RuntimeException("Get All Users Path parameter is not found in constants file");
	}

	public String getReportPath() {
		String getreportpath = properties.getProperty("reportconfigpath");
		if (getreportpath != null)
			return getreportpath;
		else
			throw new RuntimeException("Get Report Config Path is not found in constants file");

	}

	public readCommmons getCommons() {
		readCommmons readValue = null;
		try {
			File file = new File("src\\test\\resources\\commons.yaml");
			ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
			readValue = objectMapper.readValue(file, readCommmons.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return readValue;
	}

}
