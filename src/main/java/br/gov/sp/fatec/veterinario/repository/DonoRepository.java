package br.gov.sp.fatec.veterinario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.veterinario.entity.Dono;

public interface DonoRepository extends JpaRepository<Dono, Long>{
    public List<Dono> findByAnimaisNome(String nome);
}
