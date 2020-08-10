package com.spring.exercises.exercises.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

    @SuppressWarnings("serial")
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    public class Usernotfoundexception extends RuntimeException {

        private final String userName;

        private final String fieldName;

        private final Object fieldValue;

        public Usernotfoundexception(String userName, String fieldName, Object fieldValue) {
            super(String.format("%s not found with %s : '%s'", userName, fieldName, fieldValue));
            this.userName = userName;
            this.fieldName = fieldName;
            this.fieldValue = fieldValue;
        }

        public String getResourceName() {
            return userName;
        }

        public String getFieldName() {
            return fieldName;
        }

        public Object getFieldValue() {
            return fieldValue;
        }
    }
