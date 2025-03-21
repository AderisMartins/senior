package com.senior.desafio.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Cidade {

    @Id
    @Column(name = "ibge_id")
    private long ibgeId;

    private String uf;
    private String name;
    private String capital;
    private String lon;
    private String lat;

    @Column(name = "no_accents")
    private String noAccents;

    @Column(name = "alternative_names")
    private String alternativeNames;

    private String microregion;
    private String mesoregion;

    public long getIbgeId() {
        return ibgeId;
    }

    public void setIbgeId(long ibgeId) {
        this.ibgeId = ibgeId;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getNoAccents() {
        return noAccents;
    }

    public void setNoAccents(String noAccents) {
        this.noAccents = noAccents;
    }

    public String getAlternativeNames() {
        return alternativeNames;
    }

    public void setAlternativeNames(String alternativeNames) {
        this.alternativeNames = alternativeNames;
    }

    public String getMicroregion() {
        return microregion;
    }

    public void setMicroregion(String microregion) {
        this.microregion = microregion;
    }

    public String getMesoregion() {
        return mesoregion;
    }

    public void setMesoregion(String mesoregion) {
        this.mesoregion = mesoregion;
    }

    @Override
    public String toString() {
        return "Cidade{" +
                "ibgeId=" + ibgeId +
                ", uf='" + uf + '\'' +
                ", name='" + name + '\'' +
                ", capital='" + capital + '\'' +
                ", lon='" + lon + '\'' +
                ", lat='" + lat + '\'' +
                ", noAccents='" + noAccents + '\'' +
                ", alternativeNames='" + alternativeNames + '\'' +
                ", microregion='" + microregion + '\'' +
                ", mesoregion='" + mesoregion + '\'' +
                '}';
    }
}
