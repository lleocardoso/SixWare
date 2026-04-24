package model;

import enums.Cargo;

public class Funcionario {
    private long id;
    private String nome;
    private Cargo cargo;

    public Funcionario() {}

    public Funcionario(long id, String nome, Cargo cargo) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
    }

    public Inspecao registrarInspecao(Inspecao inspecao) {
        if (inspecao != null) {
            inspecao.setFuncionario(this);
            System.out.println("  Inspeção registrada pelo funcionário:" + nome);
        }
        return inspecao;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Cargo getCargo() { return cargo; }
    public void setCargo(Cargo cargo) { this.cargo = cargo; }
}
