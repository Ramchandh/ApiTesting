package org.com.restbase.RestAuto;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class TestBase {

	public Properties prop;

	public int RESPONSE_CODE_200 = 200;
	public int RESPONSE_CODE_500 = 500;
	public int RESPONSE_CODE_400 = 400;
	public int RESPONSE_CODE_401 = 401;
	public int RESPONSE_CODE_201 = 201;

	public TestBase() {

		prop = new Properties();

		try {
			FileInputStream file = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\org\\com\\qa\\config\\config.properties");
			prop.load(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println(e);

		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
