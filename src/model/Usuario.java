package model;

import java.util.List;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;

    public Usuario() {}

    public Usuario(int id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public boolean autenticar(String senha) {
        return this.senha != null && this.senha.equals(senha);
    }

    public List<Solo> getSolo() {
        return List.of();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}
