create or replace PROCEDURE PROC_OBTER_ESTADO_MAIOR_MENOR_CIDADES (
    p_estado_maior OUT VARCHAR2,
    p_qtd_maior OUT NUMBER,
    p_estado_menor OUT VARCHAR2,
    p_qtd_menor OUT NUMBER
) AS
BEGIN
    -- Obtem o estado com maior quantidade
    SELECT uf, total_cidades INTO p_estado_maior, p_qtd_maior
    FROM (
        SELECT uf, COUNT(*) AS total_cidades
        FROM cidade
        GROUP BY uf
        ORDER BY total_cidades DESC
    ) WHERE ROWNUM = 1;

    -- Obtém estado menor quantidade
    SELECT uf, total_cidades INTO p_estado_menor, p_qtd_menor
    FROM (
        SELECT uf, COUNT(*) AS total_cidades
        FROM cidade
        GROUP BY uf
        ORDER BY total_cidades ASC
    ) WHERE ROWNUM = 1;
END PROC_OBTER_ESTADO_MAIOR_MENOR_CIDADES;
/
