package com.townhubresponse.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {
    private int status;
    private String message;
    private T data;
    private List<TownHubError> errors;

    public Result() {
        super();
    }

    public Result(int status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public Result(int status, T data) {
        super();
        this.status = status;
        this.data = data;
    }

    public Result(int status, String message, T data) {
        super();
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Result(int status, List<TownHubError> error) {
        super();
        this.status = status;
        this.errors = error;
    }

    public Result(int status, String message, List<TownHubError> error) {
        super();
        this.status = status;
        this.message = message;
        this.errors = error;
    }

    public Result(int status, TownHubError error) {
        super();
        this.status = status;
        addErrorToList(error);
    }

    public Result(int status, String message, TownHubError error) {
        super();
        this.status = status;
        this.message = message;
        addErrorToList(error);
    }

    public Result(int status, String message, T data, List<TownHubError> error) {
        super();
        this.status = status;
        this.message = message;
        this.data = data;
        this.errors = error;
    }

    public int getstatus() {
        return status;
    }

    public void setstatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<TownHubError> getError() {
        return errors;
    }

    public void setErrorList(List<TownHubError> error) {
        this.errors = error;
    }

    public void addErrorToList(TownHubError error) {
        if (this.errors == null) {
            this.errors = new ArrayList<TownHubError>();
        }
        this.errors.add(error);
    }

    @Override
    public String toString() {
        return "ComplainSystemRespone [message=" + message + ", data=" + data + ", errors=" + errors + "]";
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class TownHubError {
        private int errorId;
        private String errorMessage;
        private String field;

        public TownHubError() {
            super();
        }

        public TownHubError(int errorId, String errorMessage) {
            super();
            this.errorId = errorId;
            this.errorMessage = errorMessage;
        }

        public TownHubError(int errorId, String errorMessage, String field) {
            super();
            this.errorId = errorId;
            this.errorMessage = errorMessage;
            this.field = field;
        }

        public int getErrorId() {
            return errorId;
        }

        public void setErrorId(int errorId) {
            this.errorId = errorId;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        @Override
        public String toString() {
            return "TownHubError [errorId=" + errorId + ", errorMessage=" + errorMessage + ", field=" + field
                    + "]";
        }

    }
}