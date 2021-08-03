package com.privyid.pretest.privyidpretestbackendenginer.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class SharedControllerAdvice {

    @ExceptionHandler({ServiceException.class})
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public @ResponseBody
    ResponseAPIErrorDTO handleErrorResponse(
            final ServiceException exception, final HttpServletRequest request
    ) {
        return buildErrorResponse(exception, request, HttpStatus.UNPROCESSABLE_ENTITY.value(), HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase());
    }

    private ResponseAPIErrorDTO buildErrorResponse(Exception exception, HttpServletRequest request, int responseCode, String responseMsg) {
        log.error(exception.getMessage(), exception);
        return ResponseAPIErrorDTO
                .builder()
                .error(exception.getMessage())
                .exception(exception.getClass().getCanonicalName())
                .path(request.getServletPath())
                .timestamp(new Date())
                .build();
    }

}
