package com.venda.app.services;

import com.venda.app.entities.Client;
import com.venda.app.entities.Produto;
import com.venda.app.entities.Venda;
import com.venda.app.repositories.ProdutoRepository;
import com.venda.app.repositories.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {
    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private VendaRepository vendaRepositoy;

    //
    @Autowired
    private ProdutoRepository produtoRepository;

    public String save(Venda venda){
        double valorTotal = this.calcularTotalVenda(venda.getProduto());

        venda.setValorTotal(valorTotal);

        this.verificarIdadeCliente(venda.getCliente(), valorTotal);

        this.vendaRepository.save(venda);
        return "Venda salvo";
    }

    public String saveMultiple(List<Venda> vendas) {
        this.vendaRepositoy.saveAll(vendas);
        return "Vendas salvos com sucesso!";
    }

    private double calcularTotalVenda(List<Produto>produtos) {
        double valorTotal = 0;
        for(Produto produto : produtos) {

            Produto produtoAUX = produtoRepository.findById(produto.getId()).get();

            valorTotal+=produtoAUX.getPreco();
        }
        return valorTotal;
    }

    private void verificarIdadeCliente(Client cliente, double valorTotal) {
        if (cliente.getAge() < 18 && valorTotal > 500) {
            throw new IllegalArgumentException(
                    "O valor total da venda não pode exceder R$500,00 pila para clientes menores de 18 anos. :("
            );
        }
    }


    public String update(Venda vendaUpdate, long id) {
        Optional<Venda> vendaOptional =
                this.vendaRepository.findById(id);

        if(vendaOptional.isPresent()) {
            Venda venda = vendaOptional.get();
            venda.setCliente(
                    vendaUpdate.getCliente() != null ?
                            vendaUpdate.getCliente() :
                            venda.getCliente()
            );
            venda.setFuncionario(
                    vendaUpdate.getFuncionario() != null ?
                            vendaUpdate.getFuncionario() :
                            venda.getFuncionario()
            );
            venda.setProduto(
                    vendaUpdate.getProduto() != null ?
                            vendaUpdate.getProduto() :
                            venda.getProduto()
            );
            this.vendaRepository.save(venda);
        }
        return "Venda atualizado";
    }
    public List<Venda> findAll() {
        return this.vendaRepository.findAll();
    }

    public Optional<Venda> findById(long id) {
        if(this.vendaRepository.findById(id).isEmpty()){
            throw new RuntimeException("Venda nao encontrado: " + id);
        }
        return this.vendaRepository.findById(id);
    }
    public String deleteById(long id) {
        if(this.vendaRepository.findById(id).isEmpty()){
            throw new RuntimeException("Venda nao encontrado: " + id);
        }
        this.vendaRepository.deleteById(id);
        return "Venda removido";
    }
}
