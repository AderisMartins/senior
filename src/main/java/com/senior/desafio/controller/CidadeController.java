package com.senior.desafio.controller;

import com.senior.desafio.dto.*;
import com.senior.desafio.entity.Cidade;
import com.senior.desafio.service.CidadeService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/cidade")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;


    @GetMapping("/capitais")
    public ResponseEntity<List<Cidade>> listarCapitais() {
        return ResponseEntity.status(HttpStatus.OK).body(cidadeService.getCapitais());
    }

    @GetMapping("/estado-maior-menor")
    public ResponseEntity<?> obterEstadoMaiorMenorCidades() {
        try {
            Map<String, Object> resultado = cidadeService.obterEstadoMaiorMenorCidades();

            if (!resultado.isEmpty()) {
                return ResponseEntity.ok(resultado);
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body(new ErroResponse("Nenhum dado encontrado", "Não há estados cadastrados"));
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ErroResponse("Erro ao buscar estados", e.getMessage()));
        }
    }

    @GetMapping("/quantidade-por-estado")
    public ResponseEntity<?> obterQuantidadeCidadesPorEstado() {
        List<Map<String, Object>> resultado = cidadeService.obterQuantidadeCidadesPorEstado();

        if (resultado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ErroResponse("Nenhum dado encontrado", "Não há cidades cadastradas para contar."));
        }

        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/{ibgeId}")
    public ResponseEntity<Object> obterCidadePorIbgeId(@PathVariable Long ibgeId) {
        Optional<Cidade> cidade = cidadeService.obterCidadePorIbgeId(ibgeId);

        if (cidade.isPresent()) {
            return ResponseEntity.ok(cidade.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("erro", "Cidade não encontrada", "detalhes", "IBGE ID: " + ibgeId));
        }
    }


    @GetMapping("/estado/{uf}")
    public ResponseEntity<?> obterCidadesPorEstado(@PathVariable String uf) {
        String estadoFormatado = uf.toUpperCase();
        List<String> cidades = cidadeService.obterCidadesPorEstado(estadoFormatado);

        if (cidades.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErroResponse("Estado sem cidades", "Não foram encontradas cidades para " + estadoFormatado));
        }

        return ResponseEntity.ok(Map.of("estado", estadoFormatado, "cidades", cidades));
    }

    @PostMapping("/adicionar")
    public ResponseEntity<?> adicionarCidade(@RequestBody CidadeDTO cidadeDTO) {
        try {
            Cidade cidade = cidadeService.adicionarCidade(cidadeDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new MensagemResponse("Cidade " + cidade.getName() + " adicionada com sucesso."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErroResponse("Erro ao adicionar cidade", e.getMessage()));
        }
    }

    @DeleteMapping("/deletar/{ibgeId}")
    public ResponseEntity<?> deletarCidade(@PathVariable Long ibgeId) {
        try {
            cidadeService.deletarCidade(ibgeId);
            return ResponseEntity.ok(new MensagemResponse("Cidade com ID " + ibgeId + " deletada com sucesso."));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErroResponse("Erro ao deletar cidade", e.getMessage()));
        }
    }

    @GetMapping("/contar-distintos")
    public ResponseEntity<?> contarValoresDistintos(
            @Parameter(
                    name = "coluna",
                    description = "Selecione a coluna para contar valores distintos",
                    in = ParameterIn.QUERY,
                    schema = @Schema(type = "string", allowableValues = {"uf", "capital", "microregion", "mesoregion"})
            )
            @RequestParam String coluna) {
        try {
            Long count = cidadeService.contarValoresDistintos(coluna);
            return ResponseEntity.ok(new ContagemResponse(coluna, count));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErroResponse("Coluna inválida", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ErroResponse("Erro inesperado", e.getMessage()));
        }
    }

    @GetMapping("/total")
    public ResponseEntity<?> obterQuantidadeTotalCidades() {
        long totalCidades = cidadeService.obterQuantidadeTotalCidades();
        return ResponseEntity.ok(Map.of("totalCidades", totalCidades));
    }

    @GetMapping("/mais-distantes")
    public ResponseEntity<CidadesDistantesDTO> getCidadesMaisDistantes() {
        try {
            CidadesDistantesDTO cidadesDistantes = cidadeService.obterCidadesMaisDistantes();
            return new ResponseEntity<>(cidadesDistantes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/upload", consumes = {"multipart/form-data"})
    public ResponseEntity<String> uploadCidades(
            @RequestPart("file") MultipartFile file) throws IOException {
        String mensagem = cidadeService.processarCSV(file);

        return ResponseEntity.ok(mensagem);
    }
}
