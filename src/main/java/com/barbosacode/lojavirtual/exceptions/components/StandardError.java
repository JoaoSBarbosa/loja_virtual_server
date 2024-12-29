package com.barbosacode.lojavirtual.exceptions.components;

import java.time.Instant;

public class StandardError {

    private Instant timestamp;
    private String error;
    private String message;
    private Integer status;
    private String path;

    public StandardError() {}
    public StandardError(Instant timestamp, String error, String message, Integer status, String path) {
        this.timestamp = timestamp;
        this.error = error;
        this.message = message;
        this.status = status;
        this.path = path;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "StandardError{" +
                "timestamp=" + timestamp +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", status=" + status +
                ", path='" + path + '\'' +
                '}';
    }
}

