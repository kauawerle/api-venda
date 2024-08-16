package com.venda.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "tb_funcionario")

public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "O nome é obrigatório.")
    @Pattern(regexp = "^(?=.*\\s)(?=.*\\b\\w{2,}).*$", message =
            "O nome deve conter pelo menos duas palavras e um espaço.")
    private String nome;

    @Email(message = "E-mail inválido!")
    private String email;

    @Pattern(regexp = "^\\(\\d{2}\\) \\d{4,5}-\\d{4}$", message =
            "O telefone deve seguir o padrão (XX) XXXX-XXXX ou (XX) XXXXX-XXXX.")
    private String telefone;

    @Min(value = 0, message = "A idade não pode ser menor ou igual a 0")
    private int idade;

    @NotBlank(message = "O endereço é obrigatório.")
    private String endereco;

    private String funcao;

    @OneToMany(mappedBy = "funcionario")
    @JsonIgnoreProperties
    private List<Venda> venda;
}
