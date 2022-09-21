package logger;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class ApiLogger implements Filter {

    private static final String STRING_LOG_FORMAT = "Method: %s \n Request Body: %s \n Response Status: %s %s \n Response Body: %s";

        @Override
        public Response filter(FilterableRequestSpecification filterableReqSpec, FilterableResponseSpecification filterableRespSpec, FilterContext filterContext) {
            Response response = filterContext.next(filterableReqSpec, filterableRespSpec);
            MyLogger.getMyLogger().info(String.format(STRING_LOG_FORMAT,
                    filterableReqSpec.getMethod(),
                    filterableReqSpec.getURI(),
                    response.getStatusCode(),
                    response.getStatusLine(),
                    response.jsonPath().prettyPrint()
            ));
        return response;
    }
}
