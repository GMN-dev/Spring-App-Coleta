package com.stefanini.app.dto;

import com.stefanini.app.utils.AssetType;

public record AssetCreateDTO(
        String heritage,
        String email,
        String name,
        AssetType type
){}
