package edu.bbte.bibliospringdata.api;

import edu.bbte.bibliospringdata.api.exeption.BadRequestException;
import edu.bbte.bibliospringdata.api.exeption.CreationFaildException;
import edu.bbte.bibliospringdata.api.exeption.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApiExceptionHandler{

    private final ApiErrorResponse errorResponse;

    @Autowired
    public ApiExceptionHandler(ApiErrorResponse errorResponse){
        this.errorResponse = errorResponse;
    }

    // abban az esetben ha tobb exceptin szeretnenk hogy kezeljen akkor kapcsos zarojelben adhatjuk meg az osztalyokat!
    @ExceptionHandler({NotFoundException.class})
    /* a ResponseEntity osztaly spring keretrendszerhez tartozik es beallitja automatikusan
     a body -t es a header -t. */
    public ResponseEntity<ApiErrorResponse> handleNotFoundException(NotFoundException e){
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(e.getType().getSimpleName() + " Entity not found with Id: " + e.getId());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // abban az esetben ha csak egy exception -ert felelos akkor nem kotelezo megadni semmit az ExceptionHandler utan!
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiErrorResponse handleBadRequestException(BadRequestException e){
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(" Bad request! The " + e.getType().getSimpleName() + " with the: '" + e.getId() + "' Id does not exist. Please use a number for the Id parameter.");
        return errorResponse;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ApiErrorResponse handleCreationFaildException(CreationFaildException e){
        errorResponse.setStatus(HttpStatus.CONFLICT.value());
        errorResponse.setMessage("Creation failed");
        return errorResponse;
    }

    @Component
    public static class ApiErrorResponse {

        private int status;
        private String message;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
