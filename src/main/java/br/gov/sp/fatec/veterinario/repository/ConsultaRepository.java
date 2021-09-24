package br.gov.sp.fatec.veterinario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.veterinario.entity.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long>{
    
    public List<Consulta> findByAnimalNome(String nome);

}
