package br.com.desafio.totalshake.application.model.produto;

import br.com.desafio.totalshake.application.model.AbstractModel;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "produto")
public class ProdutoModel extends AbstractModel<Long> {

    @Column(nullable = false, length = 50)
    @NotEmpty( message = "O campo nome deve ter no minimo 5 e no maximo 50 caracteres")
    @Size(min = 5, max = 50, message = "O campo nome deve ter no minimo 5 e no maximo 50 caracteres")
    private String nome;

    @Column(name = "preco", nullable = true)
    private Double preco;

    @Override
    public String toString() {
        return this.getNome()+" - R$"+ this.getPreco();
    }
}
