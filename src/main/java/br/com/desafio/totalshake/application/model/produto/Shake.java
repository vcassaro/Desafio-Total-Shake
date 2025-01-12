package br.com.desafio.totalshake.application.model.produto;

import br.com.desafio.totalshake.application.model.enums.TipoTamanho;
import br.com.desafio.totalshake.application.model.ingredientes.Base;
import br.com.desafio.totalshake.application.model.ingredientes.Fruta;
import br.com.desafio.totalshake.application.model.ingredientes.IngredienteModel;
import br.com.desafio.totalshake.application.model.ingredientes.Topping;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shake{

    private Base base;
    private Fruta fruta;
    private Topping topping;
    private TipoTamanho tipoTamanho;
    private List<IngredienteModel> adicionais = new ArrayList<>();

    @Override
    public String toString() {
        return this.base.getNome()+" - R$"+ this.base.getPreco() + " / " +
                this.fruta.getNome()+" - R$"+ this.fruta.getPreco() + " / " +
                this.topping.getNome()+" - R$"+ this.topping.getPreco() + " / " +
                "Adicionais: "+ this.adicionais + " / " +
                "Tamanho: "+this.tipoTamanho.toString();
    }
}
