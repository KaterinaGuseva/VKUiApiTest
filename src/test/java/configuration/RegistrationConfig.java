package configuration;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RegistrationConfig {

    private static ISettingsFile environmentRegistration = new JsonSettingsFile("registration_data.json");

    public static String getLogin() {
        return environmentRegistration.getValue("/login").toString();
    }

    public static String getPassword() {
        return environmentRegistration.getValue("/password").toString();
    }

    public static String getToken() {
        return environmentRegistration.getValue("/access_token").toString();
    }
}
