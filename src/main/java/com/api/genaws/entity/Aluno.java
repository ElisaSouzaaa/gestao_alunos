package com.api.genaws.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_alunos")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "This attribute must be filled!")
    @Size(min = 8, max = 20)
    private String nome_aluno;

    @NotBlank(message = "This attribute must be filled!")
    private int idade_aluno;

    @NotBlank(message = "This attribute must be filled!")
    private double nota_primeiro_semestre;

    @NotBlank(message = "This attribute must be filled!")
    private double nota_segundo_semestre;

    @NotBlank(message = "This attribute must be filled!")
    @Size(min = 8, max = 20)
    private String nome_professor;

    @NotBlank(message = "This attribute must be filled!")
    private int numero_sala;
}