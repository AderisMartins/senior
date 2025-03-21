-- PARTE 2 - SQL Prático

-- a)	Uma consulta onde mostre os 10 primeiros produtos.
SELECT * 
FROM PRODUTO
WHERE ROWNUM <= 10;

-- b)	Uma consulta onde mostre os produtos apenas com embalagens ativas.
SELECT p.*
FROM PRODUTO p
JOIN EMBALAGEM e ON p.IDPRODUTO = e.IDPRODUTO
WHERE e.ATIVO = 'S';

-- c)	Uma consulta que traga quantidade de embalagens de cada produto.
SELECT p.IDPRODUTO, p.DESCR, COUNT(e.BARRA) AS QTD_EMBALAGENS
FROM PRODUTO p
LEFT JOIN EMBALAGEM e ON p.IDPRODUTO = e.IDPRODUTO
GROUP BY p.IDPRODUTO, p.DESCR;

-- d)	Insira um novo produto e uma nova embalagem para esse produto de acordo com a estrutura dados.
INSERT INTO PRODUTO (IDPRODUTO, CODIGOINTERNO, DESCR, ATIVO)
VALUES(24, '000006', 'ACHOCOLATADO CHOCO CLARA', 'S');

INSERT INTO EMBALAGEM (IDPRODUTO, BARRA, DESCR, FATORCONVERSAO, ALTURA, LARGURA, COMPRIMENTO, ATIVO)
VALUES (24, 7891000745624, 'ACHOCOLATADO CHOCO CLARA CX COM 6', 6, 100, 100, 100, 'S');

-- e)	Altere a altura para 250, largura para 120 e comprimento para 150 das embalagens dos produtos cujo FATORCONVERSAO seja igual a 1.
UPDATE EMBALAGEM
SET ALTURA = 250, LARGURA = 120, COMPRIMENTO = 150
WHERE FATORCONVERSAO = 1;

SELECT BARRA, IDPRODUTO, DESCR, FATORCONVERSAO, ALTURA, LARGURA, COMPRIMENTO
FROM EMBALAGEM
WHERE FATORCONVERSAO = 1;