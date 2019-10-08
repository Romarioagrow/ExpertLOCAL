package expertshop.controllers;
import lombok.extern.java.Log;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestContextHolder;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Log
public class ControllerService {
    static Map<String, String> getValidErrors(BindingResult validResult) {
        showValidErrors(validResult);
        Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
                filedError -> filedError.getField() + "Error", FieldError::getDefaultMessage
        );
        return validResult.getFieldErrors().stream().collect(collector);
    }

    static Object getValidErrorsSet(BindingResult validResult) {
        showValidErrors(validResult);
        Set<String> validErrors = new HashSet<>();
        validResult.getFieldErrors().forEach(fieldError -> validErrors.add(fieldError.getDefaultMessage()));
        return validErrors;
    }

    public static void showValidErrors(BindingResult validResult) {
        validResult.getFieldErrors().forEach(fieldError ->
                log.info(fieldError.getField() + ": " + fieldError.getCode() + "; " + fieldError.getDefaultMessage())
        );
    }

    public static String getSessionID() {
        return RequestContextHolder.currentRequestAttributes().getSessionId();
    }
}
