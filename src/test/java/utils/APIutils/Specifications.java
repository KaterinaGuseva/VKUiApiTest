package utils.APIutils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import lombok.experimental.UtilityClass;
import configuration.RegistrationConfig;
import configuration.SettingsVkConfig;
import constants.ApiParamConstants;
import logger.ApiLogger;


@UtilityClass
public class Specifications {

    public static RequestSpecification requestSpecification(String url) {
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setBasePath(ApiParamConstants.BASE_METHOD)
                .addQueryParam(ApiParamConstants.ACCESS_TOKEN, RegistrationConfig.getToken())
                .addQueryParam(ApiParamConstants.VERSION, SettingsVkConfig.getVersionApi())
                .addFilter(new ApiLogger())
                .build();
    }
}
