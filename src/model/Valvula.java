package model;

import enums.StatusValvula;

public class Valvula {
    private long id;
    private String nome;
    private StatusValvula statusAtual;
    private Zona zona;

    public Valvula() {}

    public Valvula(long id, String nome, StatusValvula statusAtual, Zona zona) {
        this.id = id;
        this.nome = nome;
        this.statusAtual = statusAtual;
        this.zona = zona;
    }

    public void abrir() {
        this.statusAtual = StatusValvula.Aberta;
        System.out.println("  Válvula '" + nome + "' aberta.");
    }

    public void fechar() {
        this.statusAtual = StatusValvula.Fechada;
        System.out.println("  Válvula '" + nome + "' fechada.");
    }

    public String getStatus() {
        return statusAtual != null ? statusAtual.name() : "INDEFINIDO";
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public StatusValvula getStatusAtual() { return statusAtual; }
    public void setStatusAtual(StatusValvula statusAtual) { this.statusAtual = statusAtual; }

    public Zona getZona() { return zona; }
    public void setZona(Zona zona) { this.zona = zona; }
}
