package model;

import enums.TipoSensor;
import java.time.LocalDateTime;

public class SensorUmidade extends Sensor {
    private float limiarMin;
    private float limiarMax;

    public SensorUmidade() {
        super();
        setTipo(TipoSensor.Umidade);
        setUnidade("%");
    }

    public SensorUmidade(long id, float limiarMin, float limiarMax, Dispositivo dispositivo) {
        super(id, TipoSensor.Umidade, "%", dispositivo);
        this.limiarMin = limiarMin;
        this.limiarMax = limiarMax;
    }

    public boolean verificarDeficit() {
        LeituraSensor ultima = coletarLeitura();
        return ultima != null && ultima.getValor() < limiarMin;
    }

    public float getPercentualCapacidade() {
        LeituraSensor ultima = coletarLeitura();
        if (ultima == null || limiarMax == 0) return 0;
        return (ultima.getValor() / limiarMax) * 100;
    }

    @Override
    public LeituraSensor coletarLeitura() {
        return new LeituraSensor(0L, 0f, LocalDateTime.now(), this);
    }

    public float getLimiarMin() { return limiarMin; }
    public void setLimiarMin(float limiarMin) { this.limiarMin = limiarMin; }

    public float getLimiarMax() { return limiarMax; }
    public void setLimiarMax(float limiarMax) { this.limiarMax = limiarMax; }
}
