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
    private String nome;

    @Column(name = "preco", nullable = true)
    private Double preco;

    @Override
    public String toString() {
        return this.getNome()+" - R$"+ this.getPreco();
    }
}
