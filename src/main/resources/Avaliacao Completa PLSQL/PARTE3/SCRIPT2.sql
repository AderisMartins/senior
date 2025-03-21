-- Bloco PL/SQL para gerar 1000 registros para a tabela EXAME_NF e EXAME_ITEMNF

DECLARE

    v_idnf NUMBER := 0;  -- ID EXAME_NF
    v_iditemnf NUMBER := 0;  -- ID EXAME_ITEMNF
    v_data DATE := SYSDATE - 10;  -- Data de cadastro começa com 10 dias atrás
    v_total_registros NUMBER := 1000;  -- Total registros a serem criados
    v_qtd_item NUMBER := 3;  -- Cada EXAME_NF terá 3 itens
BEGIN
    -- Loop gerar 1000 registros para EXAME_NF
    FOR contador IN 1..v_total_registros LOOP
        -- Gera o IDNF sequencial
        v_idnf := v_idnf + 1;
        
        -- Insere registr na tabela EXAME_NF
        INSERT INTO EXAME_NF (IDNF, NUMERO, DATACADASTRO, TOTALGERAL)
        VALUES (v_idnf, v_idnf + 1000, v_data, ROUND(DBMS_RANDOM.VALUE(100, 1000), 2));
        
        -- Loop para inserir 3 itens para cada EXAME_NF
        FOR j IN 1..v_qtd_item LOOP
            -- Gera o IDITEMNF sequencial
            v_iditemnf := v_iditemnf + 1;
            
            -- Insere registro na tabela EXAME_ITEMNF
            INSERT INTO EXAME_ITEMNF (IDITEMNF, IDNF, IDPRODUTO, QTDE, VALOR)
            VALUES (v_iditemnf, v_idnf, MOD(v_iditemnf, 100) + 1, ROUND(DBMS_RANDOM.VALUE(1, 10), 0), ROUND(DBMS_RANDOM.VALUE(10, 100), 2));
        END LOOP;
        
        -- cada 100 registros, incrementa 1 dia
        IF MOD(contador, 100) = 0 THEN
            v_data := v_data + 1;
        END IF;
    END LOOP;
    
    COMMIT;
    
    DBMS_OUTPUT.PUT_LINE('Reg gerados com sucesso.');
END;
/