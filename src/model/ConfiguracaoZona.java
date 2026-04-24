package model;

public class ConfiguracaoZona {
    private float limiarUmidadeMin;
    private float limiarUmidadeMax;
    private float limiarTempMax;
    private int intervaloColetaSeg;

    public ConfiguracaoZona() {}

    public ConfiguracaoZona(float limiarUmidadeMin, float limiarUmidadeMax, float limiarTempMax, int intervaloColetaSeg) {
        this.limiarUmidadeMin = limiarUmidadeMin;
        this.limiarUmidadeMax = limiarUmidadeMax;
        this.limiarTempMax = limiarTempMax;
        this.intervaloColetaSeg = intervaloColetaSeg;
    }

    public boolean isUmidadeDeficitaria(float valor) {
        return valor < limiarUmidadeMin;
    }

    public float getLimiarUmidadeMin() { return limiarUmidadeMin; }
    public void setLimiarUmidadeMin(float limiarUmidadeMin) { this.limiarUmidadeMin = limiarUmidadeMin; }

    public float getLimiarUmidadeMax() { return limiarUmidadeMax; }
    public void setLimiarUmidadeMax(float limiarUmidadeMax) { this.limiarUmidadeMax = limiarUmidadeMax; }

    public float getLimiarTempMax() { return limiarTempMax; }
    public void setLimiarTempMax(float limiarTempMax) { this.limiarTempMax = limiarTempMax; }

    public int getIntervaloColetaSeg() { return intervaloColetaSeg; }
    public void setIntervaloColetaSeg(int intervaloColetaSeg) { this.intervaloColetaSeg = intervaloColetaSeg; }
}
