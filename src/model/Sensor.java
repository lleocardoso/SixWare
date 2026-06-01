package model;

import enums.TipoSensor;

//Classe abstrata base para qualquer sensor do sistema. Garante que todos os sensores saibam como coletar uma leitura.
public abstract class Sensor {
    private long id;
    private TipoSensor tipo;
    private String unidade;
    private Dispositivo dispositivo;
    private LeituraSensor ultimaLeitura;

    public Sensor() {}

    public Sensor(long id, TipoSensor tipo, String unidade, Dispositivo dispositivo) {
        this.id = id;
        this.tipo = tipo;
        this.unidade = unidade;
        this.dispositivo = dispositivo;
    }
    // Metodo obrigatório para as classes filhas implementarem a lógica de captura.
    public abstract LeituraSensor coletarLeitura();

    // Getters e Setters
    public LeituraSensor getUltimaLeitura() { return ultimaLeitura; }
    public void setUltimaLeitura(LeituraSensor ultimaLeitura) { this.ultimaLeitura = ultimaLeitura; }
    public TipoSensor getTipo() { return tipo; }
    public void setTipo(TipoSensor tipo) { this.tipo = tipo; }
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getUnidade() { return unidade; }
    public void setUnidade(String unidade) { this.unidade = unidade; }
    public Dispositivo getDispositivo() { return dispositivo; }
    public void setDispositivo(Dispositivo dispositivo) { this.dispositivo = dispositivo; }
}