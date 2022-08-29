package br.com.desafio.totalshake.application.model.ingredientes;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("fruta")
public class Fruta  extends IngredienteModel {

}
