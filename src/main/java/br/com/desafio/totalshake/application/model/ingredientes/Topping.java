package br.com.desafio.totalshake.application.model.ingredientes;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("cobertura")
public class Topping extends IngredienteModel {
}
