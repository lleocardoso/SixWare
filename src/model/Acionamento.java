package model;

import enums.OrigemAcionamento;
import java.time.Duration;
import java.time.LocalDateTime;

//Registra o histórico de quando o sistema de irrigação foi ligado e desligado.
//Fundamental para auditoria de consumo de água e gerenciamento de precisão.

public class Acionamento {
    private long id;
    private OrigemAcionamento origem;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private Usuario usuario;

    public Acionamento() {}

    public Acionamento(long id, OrigemAcionamento origem, LocalDateTime inicio, Usuario usuario) {
        this.id = id;
        this.origem = origem;
        this.inicio = inicio;
        this.usuario = usuario;
    }

    //Finaliza o ciclo de irrigação atual, registrando o momento exato do término.
    public void encerrar() {
        this.fim = LocalDateTime.now();
        System.out.println("  Acionamento #" + id + " encerrado. Duração: " + getDuracao());
    }

    //Calcula o tempo total em que a válvula ficou aberta.
    //@return Duration contendo o tempo de acionamento.
    public Duration getDuracao() {
        if (inicio == null) return Duration.ZERO;
        LocalDateTime encerramento = (fim != null) ? fim : LocalDateTime.now();
        return Duration.between(inicio, encerramento);
    }

    // Getters e Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public OrigemAcionamento getOrigem() { return origem; }
    public void setOrigem(OrigemAcionamento origem) { this.origem = origem; }

    public LocalDateTime getInicio() { return inicio; }
    public void setInicio(LocalDateTime inicio) { this.inicio = inicio; }

    public LocalDateTime getFim() { return fim; }
    public void setFim(LocalDateTime fim) { this.fim = fim; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}
