package com.ogz.bfs.BFSCountry;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(UnsupportedOperationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public SecurityResponse handleSecurityException(UnsupportedOperationException unsupportedOperationException) {
        SecurityResponse response = new SecurityResponse(unsupportedOperationException.getMessage());
        return response;
    }

    private class SecurityResponse {

        private String error;

        public SecurityResponse() {

        }

        public SecurityResponse(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

    }
}