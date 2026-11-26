package br.com.clinica.repository;

import br.com.clinica.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
    List<Servico> findByAnimalIdOrderByDataServicoDesc(Long animalId);
}