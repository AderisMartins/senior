ALTER TABLE cidade ADD geom SDO_GEOMETRY;
/

-- APÓS A TABELA SER POPULADA ESSE UPDATE PRECISA SER EXECUTADO
-- O SDO_GEOMETRY NÃO CONSEGUE CALCULAR DIREITO SEM ELE
UPDATE cidade
SET geom = SDO_GEOMETRY(2001, 8307, SDO_POINT_TYPE(TO_NUMBER(lon), TO_NUMBER(lat), NULL), NULL, NULL);
/

-- Fonte de uso da SDO_GEOMETRY
INSERT INTO USER_SDO_GEOM_METADATA (TABLE_NAME, COLUMN_NAME, DIMINFO, SRID)
VALUES (
    'CIDADE',
    'GEOM',
    SDO_DIM_ARRAY(
        SDO_DIM_ELEMENT('Longitude', -180, 180, 0.00001),
        SDO_DIM_ELEMENT('Latitude', -90, 90, 0.00001)
    ),
    8307
);
/

-- Índice coluna geom
CREATE INDEX idx_cidade_geom ON cidade(geom) INDEXTYPE IS MDSYS.SPATIAL_INDEX;
/

-- indice coluna ibge_id
CREATE INDEX idx_cidade_ibge_id ON cidade(ibge_id);
/

-- Índices colunas de nome e UF
CREATE INDEX idx_cidade_name ON cidade(name);
/

CREATE INDEX idx_cidade_uf ON cidade(uf);
/