package com.venda.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "tb_venda")

public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JsonIgnoreProperties
    private Funcionario funcionario;

    @ManyToOne
    @JsonIgnoreProperties
    private Client cliente;

    @ManyToMany
    @JsonIgnoreProperties
    private List<Produto> produto;
}
