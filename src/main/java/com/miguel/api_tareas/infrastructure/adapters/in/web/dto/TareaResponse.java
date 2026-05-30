package com.miguel.api_tareas.infrastructure.adapters.in.web.dto;

public record TareaResponse(
        Long id,
        String title,
        Boolean completada
) {}