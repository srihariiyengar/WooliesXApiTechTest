package utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class RetrieveEndpointProperty {
    private Properties properties;
    private final String propertyFilePath = "src/test/resources/properties/endPoint.properties";

    /**
     * We will be checking if the endpoint.properties file is present
     */
    public RetrieveEndpointProperty() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("endPoint.properties file found at " + propertyFilePath);
        }
    }

    /**
     * Method to fetch baseUrl
     * @param key
     * @return
     */
    public String baseURL(String key) {
        String url = properties.getProperty(key);
        if (url != null) return url;
        else throw new RuntimeException("url not specified in the endPoint.properties file ");
    }
}
