package model;

import enums.StatusDispositivo;
import java.util.ArrayList;
import java.util.List;

public class Dispositivo {
    private long id;
    private String codigoMac;
    private String modelo;
    private StatusDispositivo status;
    private List<Sensor> sensores;

    public Dispositivo() {
        this.sensores = new ArrayList<>();
    }

    public Dispositivo(long id, String codigoMac, String modelo, StatusDispositivo status) {
        this.id = id;
        this.codigoMac = codigoMac;
        this.modelo = modelo;
        this.status = status;
        this.sensores = new ArrayList<>();
    }

    public void sincronizar() {
        System.out.println("Sincronizando dispositivo: " + codigoMac);
    }

    public void enviarComando(String cmd) {
        System.out.println("Enviando comando '" + cmd + "' para: " + codigoMac);
    }

    public String getStatus() {
        return status != null ? status.name() : "DESCONHECIDO";
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getCodigoMac() { return codigoMac; }
    public void setCodigoMac(String codigoMac) { this.codigoMac = codigoMac; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public void setStatus(StatusDispositivo status) { this.status = status; }

    public List<Sensor> getSensores() { return sensores; }
    public void setSensores(List<Sensor> sensores) { this.sensores = sensores; }
}
