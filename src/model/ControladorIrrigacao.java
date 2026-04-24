package model;

import java.util.ArrayList;
import java.util.List;

public class ControladorIrrigacao {
    private Zona zona;
    private List<Alerta> alertas;

    public ControladorIrrigacao() {
        this.alertas = new ArrayList<>();
    }

    public ControladorIrrigacao(Zona zona) {
        this.zona = zona;
        this.alertas = new ArrayList<>();
    }

    public void processarLeitura(LeituraSensor leituraSensor) {
        if (leituraSensor == null) return;

        if (leituraSensor.isAnomalia()) {
            Alerta alerta = new Alerta();
            alerta.setMensagem("  Anomalia detectada na leitura do sensor: valor=" + leituraSensor.getValor());
            alertas.add(alerta);
            System.out.println("  Anomalia detectada! Alerta gerado.");
        }
    }

    public void processarCondicoes() {
        if (zona == null) return;

        StatusSolo status = zona.getStatusSolo();
        if (status.isPrecisaIrrigar()) {
            System.out.println("  Condição de irrigação identificada na zona: " + zona.getNome());
            acionarIrrigacao();
        }
    }

    public void acionarIrrigacao() {
        if (zona == null) return;
        for (Valvula v : zona.getValvulas()) {
            v.abrir();
        }
        System.out.println("  Irrigação acionada na zona: " + zona.getNome());
    }

    public void encerrarIrrigacao() {
        if (zona == null) return;
        for (Valvula v : zona.getValvulas()) {
            v.fechar();
        }
        System.out.println("  Irrigação encerrada na zona: " + zona.getNome());
    }

    public Zona getZona() { return zona; }
    public void setZona(Zona zona) { this.zona = zona; }

    public List<Alerta> getAlertas() { return alertas; }
    public void setAlertas(List<Alerta> alertas) { this.alertas = alertas; }
}
