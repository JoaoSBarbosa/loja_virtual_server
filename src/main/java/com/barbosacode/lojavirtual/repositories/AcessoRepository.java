package com.barbosacode.lojavirtual.repositories;

import com.barbosacode.lojavirtual.models.Acesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AcessoRepository extends JpaRepository<Acesso, Long> {
    List<Acesso> findByDescricao(String descricao);
}
