package servlet.configs;

import com.cloudinary.Cloudinary;

import java.util.HashMap;
import java.util.Map;

public class CloudinaryConfiguration {
    private final String CLOUD_NAME = "cloud_name";
    private final String API_KEY = "api_key";
    private final String API_SECRET = "api_secret";

    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", CLOUD_NAME);
        config.put("api_key", API_KEY);
        config.put("api_secret", API_SECRET);
        return new Cloudinary(config);
    }
}
