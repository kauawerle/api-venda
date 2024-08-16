package com.venda.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tb_client")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "O nome é obrigatório.")
    @Pattern(regexp = "^(?=.*\\s)(?=.*\\b\\w{2,}).*$", message =
            "O nome deve conter pelo menos duas palavras e um espaço.")
    private String name;

    @Email(message = "E-mail inválido!")
    private String email;

    @Pattern(regexp = "^\\(\\d{2}\\) \\d{4,5}-\\d{4}$", message =
            "O telefone deve seguir o padrão (XX) XXXX-XXXX ou (XX) XXXXX-XXXX.")
    private String cellphone;

    @Min(value = 0, message = "A idade não pode ser menor ou igual a 0")
    private int age;

    @NotBlank(message = "O endereço é obrigatório.")
    private String address;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnoreProperties
    private List<Venda> venda;

}
