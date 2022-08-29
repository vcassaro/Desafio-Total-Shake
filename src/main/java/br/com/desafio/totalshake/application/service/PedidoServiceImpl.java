package br.com.desafio.totalshake.application.service;

import br.com.desafio.totalshake.application.model.enums.Status;
import br.com.desafio.totalshake.application.model.mapper.IngredienteMapper;
import br.com.desafio.totalshake.application.model.pedido.ItemPedidoModel;
import br.com.desafio.totalshake.application.model.pedido.PedidoModel;
import br.com.desafio.totalshake.application.repository.PedidoRepository;
import br.com.desafio.totalshake.application.model.dto.PedidoDto;
import br.com.desafio.totalshake.application.repository.IngredienteRepository;
import org.modelmapper.ModelMapper;
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

    private final ModelMapper modelMapper;

    private final IngredienteMapper ingredienteMapper;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, IngredienteRepository ingredienteRepository, ModelMapper modelMapper, IngredienteMapper ingredienteMapper) {
        this.pedidoRepository = pedidoRepository;
        this.ingredienteRepository = ingredienteRepository;
        this.modelMapper = modelMapper;
        this.ingredienteMapper = ingredienteMapper;
    }

    @Override
    public Page<PedidoDto> findAllPedidos(Pageable pageable) {

        return pedidoRepository.findAll(pageable).map(this::convertModelDto);
    }

    @Override
    public PedidoDto findPedidoById(Long id) {

        var pedido = pedidoRepository.findById(id).orElseThrow(RuntimeException::new);
        return convertModelDto(pedido);
    }

    @Override
    @Transactional
    public PedidoDto savePedido(PedidoModel pedido) {

        pedido.setItensPedidoList(removeDuplicatas(pedido.getItensPedidoList()));

        var precoTotal = pedido.getItensPedidoList().stream().map(item -> {

            item.getShake().setBase(ingredienteMapper.convertIngredienteBase(
                    ingredienteRepository.findById(item.getShake().getBase().getId()).orElseThrow(RuntimeException::new)));
            item.getShake().setFruta(ingredienteMapper.convertIngredienteFruta(
                    ingredienteRepository.findById(item.getShake().getFruta().getId()).orElseThrow(RuntimeException::new)));
            item.getShake().setTopping(ingredienteMapper.convertIngredienteCobertura(
                    ingredienteRepository.findById(item.getShake().getTopping().getId()).orElseThrow(RuntimeException::new)));
            item.setPrecoItemPedido(calcularPrecoItemPedido(item));
            item.setShakeString(item.getShake().toString());
            item.setPedido(pedido);
            return item.getPrecoItemPedido();
        }).reduce(0.0, Double::sum);

        pedido.setDataHora(LocalDateTime.now());
        pedido.setId(null);
        pedido.setStatus(Status.REALIZADO);
        pedido.setPrecoTotal(precoTotal);

        return convertModelDto(pedidoRepository.save(pedido));
    }

    @Override
    @Transactional
    public PedidoDto updatePedido(Long id, PedidoModel pedido) {

        pedido.setItensPedidoList(removeDuplicatas(pedido.getItensPedidoList()));

        var pedidoSave = pedidoRepository.findById(id).orElseThrow(RuntimeException::new);

        pedidoSave.setCliente(pedido.getCliente());
        pedidoSave.setStatus(pedido.getStatus());
        pedidoSave.setItensPedidoList(pedido.getItensPedidoList());

        var precoTotal = pedidoSave.getItensPedidoList().stream().map(item -> {

            item.getShake().setBase(ingredienteMapper.convertIngredienteBase(
                    ingredienteRepository.findById(item.getShake().getBase().getId()).orElseThrow(RuntimeException::new)));
            item.getShake().setFruta(ingredienteMapper.convertIngredienteFruta(
                    ingredienteRepository.findById(item.getShake().getFruta().getId()).orElseThrow(RuntimeException::new)));
            item.getShake().setTopping(ingredienteMapper.convertIngredienteCobertura(
                    ingredienteRepository.findById(item.getShake().getTopping().getId()).orElseThrow(RuntimeException::new)));
            item.setPrecoItemPedido(calcularPrecoItemPedido(item));
            item.setShakeString(item.getShake().toString());
            item.setPedido(pedido);
            return item.getPrecoItemPedido();
        }).reduce(0.0, Double::sum);

        pedidoSave.setPrecoTotal(precoTotal);

        return convertModelDto(pedidoRepository.save(pedidoSave));
    }

    @Override
    @Transactional
    public void deletePedido(Long id) {

        pedidoRepository.findById(id).orElseThrow(RuntimeException::new);
        pedidoRepository.deleteById(id);
    }

    public Double calcularPrecoItemPedido(ItemPedidoModel item){
        double itemPreco = 0;
        itemPreco+=item.getShake().getBase().getPreco();
        itemPreco+=item.getShake().getFruta().getPreco();
        itemPreco+=item.getShake().getTopping().getPreco();
        itemPreco+=item.getShake().getAdicionais().stream().map(adicional -> {
            adicional= ingredienteRepository.findById(adicional.getId()).orElseThrow(RuntimeException::new);
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
                        .filter(itemNaoRepetido -> itemNaoRepetido.getShake().equals(item.getShake()))
                        .findFirst()
                        .ifPresentOrElse(
                                itemNaoRepetido -> itemNaoRepetido
                                        .setQuantidade(itemNaoRepetido.getQuantidade()+item.getQuantidade()),
                                () -> itensPedidoSemRepeticao.add(item)
                        )
        );
            
        return itensPedidoSemRepeticao;
    }

    private PedidoDto convertModelDto(PedidoModel pedidoModel){

        return modelMapper.map(pedidoModel, PedidoDto.class);
    }
}
