package configuration;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SettingsVkConfig {
    
    private static ISettingsFile environmentSettingsVk = new JsonSettingsFile("settings_vk.json");
    
    public static String getUrl() {
        return environmentSettingsVk.getValue("/default_url").toString();
    }

    public static String getBaseMethodApi() {
        return environmentSettingsVk.getValue("/base_method_api").toString();
    }

    public static int getOwnerId() {
        return (int) environmentSettingsVk.getValue("/owner_id");
    }

    public static String getVersionApi() {
        return environmentSettingsVk.getValue("/version_api").toString();
    }
}
