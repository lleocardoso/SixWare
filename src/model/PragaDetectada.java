package model;

import enums.TipoPraga;
import java.util.List;
import java.util.ArrayList;

public class PragaDetectada {
    private long id;
    private List<TipoPraga> tipo;
    private int nivelGravidade;
    private String descricao;
    private Zona zona;

    public PragaDetectada() {
        this.tipo = new ArrayList<>();
    }

    public PragaDetectada(long id, List<TipoPraga> tipo, int nivelGravidade, String descricao, Zona zona) {
        this.id = id;
        this.tipo = tipo != null ? tipo : new ArrayList<>();
        this.nivelGravidade = nivelGravidade;
        this.descricao = descricao;
        this.zona = zona;
    }

    public boolean isCritical() {
        return nivelGravidade >= 7;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public List<TipoPraga> getTipo() { return tipo; }
    public void setTipo(List<TipoPraga> tipo) { this.tipo = tipo; }

    public int getNivelGravidade() { return nivelGravidade; }
    public void setNivelGravidade(int nivelGravidade) { this.nivelGravidade = nivelGravidade; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Zona getZona() { return zona; }
    public void setZona(Zona zona) { this.zona = zona; }
}
