package com.stefanini.app.dto;

import com.stefanini.app.utils.Status;

public record AssetUpdateDTO(
        Status status,
        String scheduledDate
){}