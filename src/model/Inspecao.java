package model;

import java.time.LocalDateTime;

public class Inspecao {
    private long id;
    private LocalDateTime data;
    private String observacao;
    private Funcionario funcionario;

    public Inspecao() {
        this.data = LocalDateTime.now();
    }

    public Inspecao(long id, String observacao, Funcionario funcionario) {
        this.id = id;
        this.data = LocalDateTime.now();
        this.observacao = observacao;
        this.funcionario = funcionario;
    }

    public PragaDetectada registrarPraga(PragaDetectada pragaDetectada) {
        if (pragaDetectada != null) {
            System.out.println("  Praga registrada na inspeção #" + id + ": " + pragaDetectada.getDescricao());
        }
        return pragaDetectada;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }

    public Funcionario getFuncionario() { return funcionario; }
    public void setFuncionario(Funcionario funcionario) { this.funcionario = funcionario; }
}
