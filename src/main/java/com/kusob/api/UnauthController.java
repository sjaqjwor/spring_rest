package com.kusob.api;

import com.kusob.domain.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by seungki on 2017-08-07.
 */

@ControllerAdvice
public class UnauthController {

    @ExceptionHandler(value = { Exception.class} )
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResponseDTO handlerException(Exception e){
        return new ResponseDTO("FAIL");
    }

}

