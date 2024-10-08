package com.venda.app.services;


import com.venda.app.entities.Produto;
import com.venda.app.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public String save(Produto produto){
        this.produtoRepository.save(produto);
        return "Produto salvo";
    }

    public String update(Produto produtoUpdate, long id) {
        Optional<Produto> produtoOptional =
                this.produtoRepository.findById(id);

        if(produtoOptional.isPresent()) {
            Produto produto = produtoOptional.get();
            produto.setNome(
                    produtoUpdate.getNome() != null ?
                            produtoUpdate.getNome() :
                            produto.getNome()
            );
            produto.setDescricao(
                    produtoUpdate.getDescricao() != null ?
                            produtoUpdate.getDescricao() :
                            produto.getDescricao()
            );
            produto.setPreco(
                    produtoUpdate.getPreco() != 0 ?
                            produtoUpdate.getPreco() :
                            produto.getPreco()
            );
            this.produtoRepository.save(produto);
        }
        return "Produto atualizado";
    }
    public List<Produto> findAll() {
        return this.produtoRepository.findAll();
    }

    public Optional<Produto> findById(long id) {
        if(this.produtoRepository.findById(id).isEmpty()){
            throw new RuntimeException("Produto nao encontrado: " + id);
        }
        return this.produtoRepository.findById(id);
    }
    public String deleteById(long id) {
        if(this.produtoRepository.findById(id).isEmpty()){
            throw new RuntimeException("Produto nao encontrado: " + id);
        }
        this.produtoRepository.deleteById(id);
        return "Produto removido";
    }
}
