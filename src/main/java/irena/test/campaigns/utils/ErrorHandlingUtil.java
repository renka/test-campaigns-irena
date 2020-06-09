package irena.test.campaigns.utils;


import org.slf4j.Logger;

public class ErrorHandlingUtil {
    public static BaseResponse getErrorResponse(Exception e, String s, Logger log) {
        log.error(e.getMessage());
        e.printStackTrace();
        return new BaseResponse<>(null, s);
    }
}
