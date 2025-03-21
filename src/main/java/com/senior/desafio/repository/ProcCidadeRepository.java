package com.senior.desafio.repository;

import com.senior.desafio.dto.CidadesDistantesDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@Transactional
public class ProcCidadeRepository {

    private final EntityManager entityManager;

    public ProcCidadeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Map<String, Object> obterEstadoMaiorMenorCidades() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("PROC_OBTER_ESTADO_MAIOR_MENOR_CIDADES");

        // Registra param de saida
        query.registerStoredProcedureParameter(1, String.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter(4, Integer.class, ParameterMode.OUT);

        query.execute();

        // Valores retornados
        Map<String, Object> resultado = new HashMap<>();
        resultado.put("estadoMaior", query.getOutputParameterValue(1));
        resultado.put("qtdMaior", query.getOutputParameterValue(2));
        resultado.put("estadoMenor", query.getOutputParameterValue(3));
        resultado.put("qtdMenor", query.getOutputParameterValue(4));

        return resultado;
    }

    public CidadesDistantesDTO chamarProcedureCidadesMaisDistantes() {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("PROC_OBTER_CIDADES_MAIS_DISTANTES");
        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter(4, Integer.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter(5, String.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter(6, String.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter(7, Float.class, ParameterMode.OUT);

        query.execute();

        Integer cidade1 = (Integer) query.getOutputParameterValue(1);
        String nomeCidade1 = (String) query.getOutputParameterValue(2);
        String ufCidade1 = (String) query.getOutputParameterValue(3);

        Integer cidade2 = (Integer) query.getOutputParameterValue(4);
        String nomeCidade2 = (String) query.getOutputParameterValue(5);
        String ufCidade2 = (String) query.getOutputParameterValue(6);

        Float distanciaMax = (Float) query.getOutputParameterValue(7);

        return new CidadesDistantesDTO(cidade1, nomeCidade1, ufCidade1, cidade2, nomeCidade2, ufCidade2, distanciaMax);
    }
}
