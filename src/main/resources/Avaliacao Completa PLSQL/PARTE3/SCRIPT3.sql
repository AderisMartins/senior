CREATE OR REPLACE PROCEDURE PROC_DEFINIR_VALORES
IS
    v_total_geral EXAME_NF.TOTALGERAL%TYPE;
BEGIN
    -- Atualiza valores da tabela EXAME_ITEMNF aletaoriamente
    FOR r IN (SELECT IDNF, IDITEMNF FROM EXAME_ITEMNF) LOOP
        -- Atualiza QTDE e VALOR com valores aleatórios entre 1 e 100
        UPDATE EXAME_ITEMNF
        SET QTDE = FLOOR(DBMS_RANDOM.VALUE(1, 100)),
            VALOR = FLOOR(DBMS_RANDOM.VALUE(1, 100))
        WHERE IDITEMNF = r.IDITEMNF;
        
    END LOOP;
    
    -- para cada EXAME_NF, calcula a soma do VALOR da tabela EXAME_ITEMNF
    FOR r IN (SELECT IDNF FROM EXAME_NF) LOOP
        -- Soma o valor dos itens associados a cada EXAME_NF
        SELECT SUM(EXITEM.VALOR) 
        INTO v_total_geral
        FROM EXAME_ITEMNF EXITEM
        WHERE EXITEM.IDNF = r.IDNF;
        
        -- Atualiza TOTALGERAL da tabela EXAME_NF
        UPDATE EXAME_NF
        SET TOTALGERAL = v_total_geral
        WHERE IDNF = r.IDNF;
    END LOOP;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Valores definidos e totais atualizados com sucesso!');
END PROC_DEFINIR_VALORES;
/
