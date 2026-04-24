package model;

public class StatusSolo {
    private float umidade;
    private float temperatura;
    private boolean precisaIrrigar;

    public StatusSolo() {}

    public StatusSolo(float umidade, float temperatura, boolean precisaIrrigar) {
        this.umidade = umidade;
        this.temperatura = temperatura;
        this.precisaIrrigar = precisaIrrigar;
    }

    public boolean isCritical() {
        return precisaIrrigar || umidade < 20.0f || temperatura > 40.0f;
    }

    public float getUmidade() { return umidade; }
    public void setUmidade(float umidade) { this.umidade = umidade; }

    public float getTemperatura() { return temperatura; }
    public void setTemperatura(float temperatura) { this.temperatura = temperatura; }

    public boolean isPrecisaIrrigar() { return precisaIrrigar; }
    public void setPrecisaIrrigar(boolean precisaIrrigar) { this.precisaIrrigar = precisaIrrigar; }
}
