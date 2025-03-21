package com.senior.desafio.service;

import com.senior.desafio.dto.CidadeDTO;
import com.senior.desafio.dto.CidadesDistantesDTO;
import com.senior.desafio.entity.Cidade;
import com.senior.desafio.repository.CidadeRepository;
import com.senior.desafio.repository.ProcCidadeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CidadeService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private ProcCidadeRepository procCidadeRepository;


    public List<Cidade> getCapitais() {
        return cidadeRepository.findCapitais();
    }

    public Map<String, Object> obterEstadoMaiorMenorCidades() {
        return procCidadeRepository.obterEstadoMaiorMenorCidades();
    }

    public List<Map<String, Object>> obterQuantidadeCidadesPorEstado() {
        return cidadeRepository.obterQuantidadeCidadesPorEstado();
    }

    public Optional<Cidade> obterCidadePorIbgeId(Long ibgeId) {
        return cidadeRepository.findByIbgeId(ibgeId);
    }

    public List<String> obterCidadesPorEstado(String uf) {
        return cidadeRepository.findCidadesByEstado(uf);
    }

    public Cidade adicionarCidade(CidadeDTO cidadeDTO) throws Exception {

        if (cidadeDTO.uf().length() > 2) {
            throw new Exception("O campo 'UF' deve ter no máximo 2 caracteres.");
        }

        if (cidadeRepository.existsById(cidadeDTO.ibgeId())) {
            throw new Exception("Cidade com IBGE_ID " + cidadeDTO.ibgeId() + " já existe.");
        }

        Cidade cidade = new Cidade();
        cidade.setIbgeId(cidadeDTO.ibgeId());
        cidade.setUf(cidadeDTO.uf());
        cidade.setName(cidadeDTO.name());
        cidade.setCapital(cidadeDTO.capital());
        cidade.setLon(cidadeDTO.lon());
        cidade.setLat(cidadeDTO.lat());
        cidade.setNoAccents(cidadeDTO.noAccents());
        cidade.setAlternativeNames(cidadeDTO.alternativeNames());
        cidade.setMicroregion(cidadeDTO.microregion());
        cidade.setMesoregion(cidadeDTO.mesoregion());

        return cidadeRepository.save(cidade);
    }

    public void deletarCidade(Long ibgeId) {
        if (!cidadeRepository.existsById(ibgeId)) {
            throw new RuntimeException("Cidade com ID " + ibgeId + " não encontrada.");
        }
        cidadeRepository.deleteById(ibgeId);
    }

    @Transactional
    public Long contarValoresDistintos(String coluna) {
        // Validação para evtar SQL Injection
        if (!List.of("uf", "capital", "microregion", "mesoregion").contains(coluna)) {
            throw new IllegalArgumentException("Coluna inválida: " + coluna);
        }

        // Criar query dinamica
        String query = "SELECT COUNT(DISTINCT c." + coluna + ") FROM Cidade c";
        return (Long) entityManager.createQuery(query).getSingleResult();
    }

    public long obterQuantidadeTotalCidades() {
        return cidadeRepository.contarTotalCidades();
    }

    public CidadesDistantesDTO obterCidadesMaisDistantes() {
        return procCidadeRepository.chamarProcedureCidadesMaisDistantes();
    }

    public String processarCSV(MultipartFile file) throws IOException {
        List<Cidade> cidades = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String linha;
            boolean isHeader = true;

            while ((linha = br.readLine()) != null) {
                // Pula a primeira linha
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] dados = linha.split(",");

                if (dados.length >= 10) {
                    Cidade cidade = new Cidade();
                    cidade.setIbgeId(Long.parseLong(dados[0]));
                    cidade.setUf(dados[1]);
                    cidade.setName(dados[2]);
                    cidade.setCapital(dados[3]);
                    cidade.setLon(dados[4]);
                    cidade.setLat(dados[5]);
                    cidade.setNoAccents(dados[6]);
                    cidade.setAlternativeNames(dados[7]);
                    cidade.setMicroregion(dados[8]);
                    cidade.setMesoregion(dados[9]);

                    cidades.add(cidade);
                }
            }
        }
        cidadeRepository.saveAll(cidades);
         return "CSV processado e dados salvos com sucesso!";
    }
}