package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solo {
    private int id;
    private String nome;
    private String localizacao;
    private Usuario usuario;
    private List<Zona> zonas;

    public Solo() {
        this.zonas = new ArrayList<>();
    }

    public Solo(int id, String nome, String localizacao, Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
        this.usuario = usuario;
        this.zonas = new ArrayList<>();
    }

    public void adicionarZona(Zona zona) {
        if (zona != null) {
            this.zonas.add(zona);
        }
    }

    public Map<String, Object> getStatusGeral() {
        Map<String, Object> status = new HashMap<>();
        for (Zona zona : zonas) {
            StatusSolo s = zona.getStatusSolo();
            Map<String, Object> dadosZona = new HashMap<>();
            dadosZona.put("umidade", s.getUmidade());
            dadosZona.put("temperatura", s.getTemperatura());
            dadosZona.put("precisaIrrigar", s.isPrecisaIrrigar());
            dadosZona.put("critico", s.isCritical());
            status.put(zona.getNome(), dadosZona);
        }
        return status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public List<Zona> getZonas() { return zonas; }
    public void setZonas(List<Zona> zonas) { this.zonas = zonas; }
}
