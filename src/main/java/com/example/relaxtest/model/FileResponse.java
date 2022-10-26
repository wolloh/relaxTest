package com.example.relaxtest.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Модель возвращающаяся при успешном выполнении запроса загрузки файла ")
public class FileResponse {
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;
}
