package com.miguel.api_tareas.infrastructure.adapters.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TareaRequest(
        @NotBlank(message = "El título no puede estar vacío")
        String title,

        @NotNull(message = "El estado 'completada' es obligatorio")
        Boolean completada
) {}