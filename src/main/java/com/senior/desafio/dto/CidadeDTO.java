package com.senior.desafio.dto;

public record CidadeDTO(
        long ibgeId,
        String uf,
        String name,
        String capital,
        String lon,
        String lat,
        String noAccents,
        String alternativeNames,
        String microregion,
        String mesoregion
) {}

