CREATE OR REPLACE PROCEDURE PROC_OBTER_CIDADES_MAIS_DISTANTES (
    p_cidade1       OUT cidade.ibge_id%TYPE,
    p_nome_cidade1  OUT cidade.name%TYPE,
    p_uf_cidade1    OUT cidade.uf%TYPE,
    p_cidade2       OUT cidade.ibge_id%TYPE,
    p_nome_cidade2  OUT cidade.name%TYPE,
    p_uf_cidade2    OUT cidade.uf%TYPE,
    p_distancia_max OUT NUMBER
) IS
BEGIN
    SELECT c1.ibge_id, c1.name, c1.uf,
           c2.ibge_id, c2.name, c2.uf,
           (SDO_GEOM.SDO_DISTANCE(c1.geom, c2.geom, 0.005) / 1000) AS distancia_km
    INTO p_cidade1, p_nome_cidade1, p_uf_cidade1,
         p_cidade2, p_nome_cidade2, p_uf_cidade2,
         p_distancia_max
    FROM cidade c1, cidade c2
    WHERE c1.ibge_id < c2.ibge_id
    ORDER BY distancia_km DESC
    FETCH FIRST 1 ROW ONLY;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20002, 'Nenhuma cidade encontrada.');
END;
/
