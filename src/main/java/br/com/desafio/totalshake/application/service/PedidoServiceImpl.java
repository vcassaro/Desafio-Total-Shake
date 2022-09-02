package br.com.desafio.totalshake.application.service;

import br.com.desafio.totalshake.application.model.enums.Status;
import br.com.desafio.totalshake.application.model.mapper.IngredienteMapper;
import br.com.desafio.totalshake.application.model.pedido.ItemPedidoModel;
import br.com.desafio.totalshake.application.model.pedido.PedidoModel;
import br.com.desafio.totalshake.application.repository.IngredienteRepository;
import br.com.desafio.totalshake.application.repository.PedidoRepository;
import br.com.desafio.totalshake.application.repository.ProdutoRepository;
import br.com.desafio.totalshake.application.service.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService{

    private final PedidoRepository pedidoRepository;

    private final IngredienteRepository ingredienteRepository;

    private final ProdutoRepository produtoRepository;

    private final IngredienteMapper ingredienteMapper;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, IngredienteRepository ingredienteRepository, ProdutoRepository produtoRepository, IngredienteMapper ingredienteMapper) {
        this.pedidoRepository = pedidoRepository;
        this.ingredienteRepository = ingredienteRepository;
        this.produtoRepository = produtoRepository;
        this.ingredienteMapper = ingredienteMapper;
    }

    @Override
    public Page<PedidoModel> findAllPedidos(Pageable pageable) {

        return pedidoRepository.findAll(pageable);
    }

    @Override
    public PedidoModel findPedidoById(Long id) {

        return pedidoRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Pedido não encontrado."));
    }

    @Override
    @Transactional
    public PedidoModel savePedido(PedidoModel pedido) {

        pedido.setItensPedidoList(removeDuplicatas(pedido.getItensPedidoList()));

        var precoTotal = pedido.getItensPedidoList().stream().map(item -> {

            if(item.getShake() != null){
                item.getShake().setBase(ingredienteMapper.convertIngredienteBase(
                        ingredienteRepository.findById(item.getShake().getBase().getId()).orElseThrow(() ->new ResourceNotFoundException("Base para shake com id: "+item.getShake().getBase().getId()+" não encontrada."))));
                item.getShake().setFruta(ingredienteMapper.convertIngredienteFruta(
                        ingredienteRepository.findById(item.getShake().getFruta().getId()).orElseThrow(() ->new ResourceNotFoundException("Fruta para shake com id: "+item.getShake().getFruta().getId()+" não encontrada."))));
                item.getShake().setTopping(ingredienteMapper.convertIngredienteCobertura(
                        ingredienteRepository.findById(item.getShake().getTopping().getId()).orElseThrow(() ->new ResourceNotFoundException("Cobertura para shake com id: "+item.getShake().getTopping().getId()+" não encontrada."))));
                item.setPrecoItemPedido(calcularPrecoShake(item));
                item.setDescricao(item.getShake().toString());
                item.setPedido(pedido);
                return item.getPrecoItemPedido();
            } else {
                item.setProduto(produtoRepository.findById(item.getProduto().getId()).orElseThrow(() ->new ResourceNotFoundException("Produto com id: "+item.getProduto().getId()+" não encontrado.")));
                item.setPrecoItemPedido(item.getProduto().getPreco()* item.getQuantidade());
                item.setDescricao(item.getProduto().toString());
                item.setPedido(pedido);
                return item.getPrecoItemPedido();
            }
        }).reduce(0.0, Double::sum);

        pedido.setDataHora(LocalDateTime.now());
        pedido.setId(null);
        pedido.setStatus(Status.REALIZADO);
        pedido.setPrecoTotal(precoTotal);

        return pedidoRepository.save(pedido);
    }

    @Override
    @Transactional
    public PedidoModel updatePedido(Long id, PedidoModel pedido) {

        pedido.setItensPedidoList(removeDuplicatas(pedido.getItensPedidoList()));

        var pedidoSave = findPedidoById(id);

        pedidoSave.setCliente(pedido.getCliente());
        pedidoSave.setStatus(pedido.getStatus());
        pedidoSave.setItensPedidoList(pedido.getItensPedidoList());

        var precoTotal = pedidoSave.getItensPedidoList().stream().map(item -> {

            if(item.getShake() != null){
                item.getShake().setBase(ingredienteMapper.convertIngredienteBase(
                        ingredienteRepository.findById(item.getShake().getBase().getId()).orElseThrow(() ->new ResourceNotFoundException("Base para shake com id: "+item.getShake().getBase().getId()+" não encontrada."))));
                item.getShake().setFruta(ingredienteMapper.convertIngredienteFruta(
                        ingredienteRepository.findById(item.getShake().getFruta().getId()).orElseThrow(() ->new ResourceNotFoundException("Fruta para shake com id: "+item.getShake().getFruta().getId()+" não encontrada."))));
                item.getShake().setTopping(ingredienteMapper.convertIngredienteCobertura(
                        ingredienteRepository.findById(item.getShake().getTopping().getId()).orElseThrow(() ->new ResourceNotFoundException("Cobertura para shake com id: "+item.getShake().getTopping().getId()+" não encontrada."))));
                item.setPrecoItemPedido(calcularPrecoShake(item));
                item.setDescricao(item.getShake().toString());
                item.setPedido(pedido);
                return item.getPrecoItemPedido();
            } else {
                item.setProduto(produtoRepository.findById(item.getProduto().getId()).orElseThrow(() ->new ResourceNotFoundException("Produto com id: "+item.getProduto().getId()+" não encontrado.")));
                item.setPrecoItemPedido(item.getProduto().getPreco()* item.getQuantidade());
                item.setDescricao(item.getProduto().toString());
                item.setPedido(pedido);
                return item.getPrecoItemPedido();
            }
        }).reduce(0.0, Double::sum);

        pedidoSave.setPrecoTotal(precoTotal);

        return pedidoRepository.save(pedidoSave);
    }

    @Override
    @Transactional
    public void deletePedido(Long id) {

        findPedidoById(id);
        pedidoRepository.deleteById(id);
    }

    public Double calcularPrecoShake(ItemPedidoModel item){
        double itemPreco = 0;
        itemPreco+=item.getShake().getBase().getPreco();
        itemPreco+=item.getShake().getFruta().getPreco();
        itemPreco+=item.getShake().getTopping().getPreco();
        itemPreco+=item.getShake().getAdicionais().stream().map(adicional -> {
            adicional= ingredienteRepository.findById(adicional.getId()).orElseThrow(() ->new ResourceNotFoundException("Adicional do shake não encontrado."));
            return adicional.getPreco();
        }).reduce(0.0, Double::sum);
        itemPreco*=item.getShake().getTipoTamanho().getMultiplicador();
        itemPreco*=item.getQuantidade();
        return itemPreco;
    }

    public List<ItemPedidoModel> removeDuplicatas(List<ItemPedidoModel> itensPedido){
        List<ItemPedidoModel> itensPedidoSemRepeticao = new ArrayList<>();
        itensPedido.forEach(
                        item -> itensPedidoSemRepeticao.stream()
                        .filter(itemNaoRepetido -> itemNaoRepetido.getProduto().equals(item.getProduto()))
                        .findFirst()
                        .ifPresentOrElse(
                                itemNaoRepetido -> {
                                    if(itemNaoRepetido.getShake() != null){
                                        if(itemNaoRepetido.getShake().equals(item.getShake())){
                                            itemNaoRepetido
                                                    .setQuantidade(itemNaoRepetido.getQuantidade()+item.getQuantidade());
                                        }
                                        itensPedidoSemRepeticao.add(item);
                                    }
                                    itemNaoRepetido
                                        .setQuantidade(itemNaoRepetido.getQuantidade()+item.getQuantidade());
                                    },
                                () -> itensPedidoSemRepeticao.add(item)
                        )
        );
            
        return itensPedidoSemRepeticao;
    }
}
