package com.barbosacode.lojavirtual.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomResponse<T> {


    private String timestamp;
    private String path;
    private int status;
    private String message;
    private T data;

    public CustomResponse() {
    }

    public CustomResponse(String timestamp, String path, int status, String message, T data) {
        this.timestamp = timestamp;
        this.path = path;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CustomResponse{" +
                "timestamp=" + timestamp +
                ", path='" + path + '\'' +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
