package br.com.desafio.totalshake.application.repository;

import br.com.desafio.totalshake.application.model.ingredientes.IngredienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteRepository extends JpaRepository<IngredienteModel, Long> {
}
