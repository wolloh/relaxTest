package com.example.relaxtest.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

@Schema(description = "Сущность,которая хранит в себе путь к файлу,передаваемый пользователем")
public record FileDTO(String pathFile) {
    public FileDTO {
        Objects.requireNonNull(pathFile);
    }
}
