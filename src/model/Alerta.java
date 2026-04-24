package model;

import enums.TipoAlerta;
import java.time.LocalDateTime;

public class Alerta {
    private long id;
    private TipoAlerta tipo;
    private String mensagem;
    private LocalDateTime criadoEm;
    private LocalDateTime resolvidoEm;

    public Alerta() {
        this.criadoEm = LocalDateTime.now();
    }

    public Alerta(long id, TipoAlerta tipo, String mensagem) {
        this.id = id;
        this.tipo = tipo;
        this.mensagem = mensagem;
        this.criadoEm = LocalDateTime.now();
    }

    public void resolver() {
        this.resolvidoEm = LocalDateTime.now();
        System.out.println("  Alerta #" + id + " resolvido em: " + resolvidoEm);
    }

    public boolean isResolvido() {
        return resolvidoEm != null;
    }

    public void notificarUsuario() {
        System.out.println("  Notificação enviada ao usuário: [" + tipo + "] " + mensagem);
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public TipoAlerta getTipo() { return tipo; }
    public void setTipo(TipoAlerta tipo) { this.tipo = tipo; }

    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }

    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }

    public LocalDateTime getResolvidoEm() { return resolvidoEm; }
    public void setResolvidoEm(LocalDateTime resolvidoEm) { this.resolvidoEm = resolvidoEm; }
}
