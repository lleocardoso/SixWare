package model;

import enums.TipoSensor;
import java.time.LocalDateTime;

public class SensorTemperatura extends Sensor {
    private float limiarMax;

    public SensorTemperatura() {
        super();
        setTipo(TipoSensor.Temperatura);
        setUnidade("°C");
    }

    public SensorTemperatura(long id, float limiarMax, Dispositivo dispositivo) {
        super(id, TipoSensor.Temperatura, "°C", dispositivo);
        this.limiarMax = limiarMax;
    }

    public boolean verificarSuperaquecimento() {
        LeituraSensor ultima = coletarLeitura();
        return ultima != null && ultima.getValor() > limiarMax;
    }

    @Override
    public LeituraSensor coletarLeitura() {
        return new LeituraSensor(0L, 0f, LocalDateTime.now(), this);
    }

    public float getLimiarMax() { return limiarMax; }
    public void setLimiarMax(float limiarMax) { this.limiarMax = limiarMax; }
}
