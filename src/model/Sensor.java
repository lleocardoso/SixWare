package model;

import enums.TipoSensor;

public abstract class Sensor {
    private long id;
    private TipoSensor tipo;
    private String unidade;
    private Dispositivo dispositivo;

    public Sensor() {}

    public Sensor(long id, TipoSensor tipo, String unidade, Dispositivo dispositivo) {
        this.id = id;
        this.tipo = tipo;
        this.unidade = unidade;
        this.dispositivo = dispositivo;
    }

    public abstract LeituraSensor coletarLeitura();

    public TipoSensor getTipo() {
        return tipo;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public void setTipo(TipoSensor tipo) { this.tipo = tipo; }

    public String getUnidade() { return unidade; }
    public void setUnidade(String unidade) { this.unidade = unidade; }

    public Dispositivo getDispositivo() { return dispositivo; }
    public void setDispositivo(Dispositivo dispositivo) { this.dispositivo = dispositivo; }
}
