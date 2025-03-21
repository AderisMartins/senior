package com.senior.desafio.repository;

import com.senior.desafio.entity.Cidade;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Transactional
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    @Query("SELECT c FROM Cidade c WHERE c.capital = 'true' ORDER BY c.name")
    List<Cidade> findCapitais();

    @Query("SELECT c.uf AS estado, COUNT(c) AS quantidade FROM Cidade c GROUP BY c.uf ORDER BY c.uf")
    List<Map<String, Object>> obterQuantidadeCidadesPorEstado();

    Optional<Cidade> findByIbgeId(Long ibgeId);

    @Query("SELECT c.name FROM Cidade c WHERE c.uf = :uf ORDER BY c.name")
    List<String> findCidadesByEstado(String uf);


    @Query("SELECT COUNT(c) FROM Cidade c")
    long contarTotalCidades();

}
