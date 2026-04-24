package model;

import java.time.LocalDateTime;

public class LeituraSensor {
    private long id;
    private float valor;
    private LocalDateTime timestamp;
    private Sensor sensor;

    public LeituraSensor() {}

    public LeituraSensor(long id, float valor, LocalDateTime timestamp, Sensor sensor) {
        this.id = id;
        this.valor = valor;
        this.timestamp = timestamp;
        this.sensor = sensor;
    }

    public boolean isAnomalia() {
        if (sensor instanceof SensorUmidade s) {
            return s.verificarDeficit() || valor > s.getLimiarMax();
        }
        if (sensor instanceof SensorTemperatura s) {
            return s.verificarSuperaquecimento();
        }
        return false;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public float getValor() { return valor; }
    public void setValor(float valor) { this.valor = valor; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public Sensor getSensor() { return sensor; }
    public void setSensor(Sensor sensor) { this.sensor = sensor; }
}
