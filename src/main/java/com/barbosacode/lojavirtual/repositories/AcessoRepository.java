package com.barbosacode.lojavirtual.repositories;

import com.barbosacode.lojavirtual.models.Acesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AcessoRepository extends JpaRepository<Acesso, Long> {
    List<Acesso> findByDescricao(String descricao);


    @Query("SELECT a FROM Acesso a WHERE UPPER(trim(a.descricao)) LIKE %?1%")
    List<Acesso> findByDescricaoContaining(String descricao);
}
