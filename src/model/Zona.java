package model;

import java.util.ArrayList;
import java.util.List;

public class Zona {
    private int id;
    private String nome;
    private String solo;
    private List<Dispositivo> dispositivos;
    private List<Valvula> valvulas;
    private ConfiguracaoZona configuracoes;

    public Zona() {
        this.dispositivos = new ArrayList<>();
        this.valvulas = new ArrayList<>();
    }

    public Zona(int id, String nome, String solo, ConfiguracaoZona configuracoes) {
        this.id = id;
        this.nome = nome;
        this.solo = solo;
        this.configuracoes = configuracoes;
        this.dispositivos = new ArrayList<>();
        this.valvulas = new ArrayList<>();
    }

    public StatusSolo getStatusSolo() {
        float umidadeMedia = 0;
        float tempMedia = 0;
        int totalLeituras = 0;

        for (Dispositivo d : dispositivos) {
            for (Sensor s : d.getSensores()) {
                LeituraSensor l = s.coletarLeitura();
                if (s instanceof SensorUmidade) {
                    umidadeMedia += l.getValor();
                } else if (s instanceof SensorTemperatura) {
                    tempMedia += l.getValor();
                }
                totalLeituras++;
            }
        }

        if (totalLeituras > 0) {
            umidadeMedia /= totalLeituras;
            tempMedia /= totalLeituras;
        }

        boolean precisaIrrigar = configuracoes != null && configuracoes.isUmidadeDeficitaria(umidadeMedia);
        return new StatusSolo(umidadeMedia, tempMedia, precisaIrrigar);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSolo() { return solo; }
    public void setSolo(String solo) { this.solo = solo; }

    public List<Dispositivo> getDispositivos() { return dispositivos; }
    public void setDispositivos(List<Dispositivo> dispositivos) { this.dispositivos = dispositivos; }

    public List<Valvula> getValvulas() { return valvulas; }
    public void setValvulas(List<Valvula> valvulas) { this.valvulas = valvulas; }

    public ConfiguracaoZona getConfiguracoes() { return configuracoes; }
    public void setConfiguracoes(ConfiguracaoZona configuracoes) { this.configuracoes = configuracoes; }
}
