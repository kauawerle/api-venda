package com.venda.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
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

    private String name;

    private String email;

    private String cellphone;

    private int age;

    private String address;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnoreProperties
    private List<Venda> venda;

}
