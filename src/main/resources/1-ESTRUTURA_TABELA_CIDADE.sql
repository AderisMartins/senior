CREATE TABLE cidade (
    ibge_id NUMBER(19) NOT NULL,
    uf VARCHAR2(2) NOT NULL,
    name VARCHAR2(255) NOT NULL,
    capital VARCHAR2(50),
    lon VARCHAR2(50),
    lat VARCHAR2(50),
    no_accents VARCHAR2(255),
    alternative_names VARCHAR2(1000),
    microregion VARCHAR2(255),
    mesoregion VARCHAR2(255)
);