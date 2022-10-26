package com.example.relaxtest.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Модель с данными  возвращающаяся при успешном выполнении запросов к api")
public class ApiResponse<T> {
    private T data;

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}