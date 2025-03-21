package com.senior.desafio.dto;
public class CidadesDistantesDTO {
    private Integer cidade1;
    private String nomeCidade1;
    private String ufCidade1;
    private Integer cidade2;
    private String nomeCidade2;
    private String ufCidade2;
    private Float distanciaMax;

    public CidadesDistantesDTO(Integer cidade1, String nomeCidade1, String ufCidade1,
                               Integer cidade2, String nomeCidade2, String ufCidade2, Float distanciaMax) {
        this.cidade1 = cidade1;
        this.nomeCidade1 = nomeCidade1;
        this.ufCidade1 = ufCidade1;
        this.cidade2 = cidade2;
        this.nomeCidade2 = nomeCidade2;
        this.ufCidade2 = ufCidade2;
        this.distanciaMax = distanciaMax;
    }

    public Integer getCidade1() {
        return cidade1;
    }

    public void setCidade1(Integer cidade1) {
        this.cidade1 = cidade1;
    }

    public String getNomeCidade1() {
        return nomeCidade1;
    }

    public void setNomeCidade1(String nomeCidade1) {
        this.nomeCidade1 = nomeCidade1;
    }

    public String getUfCidade1() {
        return ufCidade1;
    }

    public void setUfCidade1(String ufCidade1) {
        this.ufCidade1 = ufCidade1;
    }

    public Integer getCidade2() {
        return cidade2;
    }

    public void setCidade2(Integer cidade2) {
        this.cidade2 = cidade2;
    }

    public String getNomeCidade2() {
        return nomeCidade2;
    }

    public void setNomeCidade2(String nomeCidade2) {
        this.nomeCidade2 = nomeCidade2;
    }

    public String getUfCidade2() {
        return ufCidade2;
    }

    public void setUfCidade2(String ufCidade2) {
        this.ufCidade2 = ufCidade2;
    }

    public Float getDistanciaMax() {
        return distanciaMax;
    }

    public void setDistanciaMax(Float distanciaMax) {
        this.distanciaMax = distanciaMax;
    }
}
