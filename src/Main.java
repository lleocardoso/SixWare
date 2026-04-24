import enums.*;
import model.*;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("SISTEMA DE MONITORAMENTO AGRÍCOLA\n");

        Usuario usuario = new Usuario(1, "Carlos Silva", "carlos@fazenda.com", "senha123");
        System.out.println("Usuário criado: " + usuario.getNome());
        System.out.println("Autenticação válida: " + (usuario.autenticar("senha123") ? "Sim" : "Não"));
        System.out.println();

        ConfiguracaoZona config = new ConfiguracaoZona(30.0f, 80.0f, 38.0f, 60);
        Zona zona = new Zona(1, "Zona A - Soja", "Setor Norte", config);
        System.out.println("Zona criada: " + zona.getNome());

        Dispositivo dispositivo = new Dispositivo(1L, "35-30-A8-3D-D5-AC", "AgriSensor-X200", StatusDispositivo.Ativo);

        SensorUmidade sensorUmidade = new SensorUmidade(1L, 30.0f, 80.0f, dispositivo);
        SensorTemperatura sensorTemp = new SensorTemperatura(2L, 38.0f, dispositivo);

        dispositivo.getSensores().add(sensorUmidade);
        dispositivo.getSensores().add(sensorTemp);
        zona.getDispositivos().add(dispositivo);

        dispositivo.sincronizar();
        System.out.println("Status dispositivo: " + dispositivo.getStatus());
        System.out.println();

        Valvula valvula = new Valvula(1L, "Válvula Principal", StatusValvula.Fechada, zona);
        zona.getValvulas().add(valvula);
        System.out.println("Válvula adicionada: " + valvula.getNome() + " | Status: " + valvula.getStatus());
        System.out.println();

        Solo solo = new Solo(1, "Fazenda Boa Vista", "Ribeirão Preto - SP", usuario);
        solo.adicionarZona(zona);
        System.out.println("Solo criado: " + solo.getNome());
        System.out.println("Localização: " + solo.getLocalizacao());
        System.out.println();

        System.out.println("COLETA DE LEITURAS");
        LeituraSensor leituraUmidade = new LeituraSensor(1L, 25.0f, LocalDateTime.now(), sensorUmidade);
        LeituraSensor leituraTemp    = new LeituraSensor(2L, 36.0f, LocalDateTime.now(), sensorTemp);

        System.out.println("  Umidade coletada : " + leituraUmidade.getValor() + "%");
        System.out.println("  Temperatura colet.: " + leituraTemp.getValor() + "°C");
        System.out.println("  Umidade em déficit: " + (sensorUmidade.verificarDeficit() ? "Sim" : "Não"));
        System.out.println("  Superaquecimento  : " + (sensorTemp.verificarSuperaquecimento() ? "Sim" : "Não"));
        System.out.println("  Leitura anômala   : " + (leituraUmidade.isAnomalia() ? "Sim" : "Não"));
        System.out.println();

        System.out.println("CONTROLADOR DE IRRIGAÇÃO");
        ControladorIrrigacao controlador = new ControladorIrrigacao(zona);
        controlador.processarLeitura(leituraUmidade);
        controlador.processarCondicoes();
        System.out.println("  Válvula após controle: " + valvula.getStatus());
        System.out.println();

        System.out.println("ALERTAS");
        Alerta alerta = new Alerta(1L, TipoAlerta.Umidade, "Umidade abaixo do limite mínimo na Zona A");
        alerta.notificarUsuario();
        System.out.println("  Resolvido antes: " + (alerta.isResolvido() ? "Sim" : "Não"));
        alerta.resolver();
        System.out.println("  Resolvido depois: " + (alerta.isResolvido() ? "Sim" : "Não"));
        System.out.println();

        System.out.println("ACIONAMENTO");
        Acionamento acionamento = new Acionamento(1L, OrigemAcionamento.Automatico, LocalDateTime.now().minusMinutes(5), usuario);
        controlador.encerrarIrrigacao();
        acionamento.encerrar();
        System.out.println("  Duração do acionamento: " + acionamento.getDuracao().toMinutes() + " minuto(s)");
        System.out.println();

        System.out.println("INSPEÇÃO DE CAMPO");
        Funcionario funcionario = new Funcionario(1L, "  Ana Souza", Cargo.Tecnico);
        Inspecao inspecao = new Inspecao(1L, "Verificação de rotina - sinais de fungo detectados", funcionario);
        funcionario.registrarInspecao(inspecao);

        PragaDetectada praga = new PragaDetectada(1L, List.of(TipoPraga.FUNGO), 8, "Mancha foliar identificada em 30% das plantas", zona);
        inspecao.registrarPraga(praga);
        System.out.println("  Praga crítica: " + (praga.isCritical() ? "Sim" : "Não") + " (gravidade: " + praga.getNivelGravidade() + "/10)");
        System.out.println();

        System.out.println("STATUS GERAL DO SOLO");
        solo.getStatusGeral().forEach((nomeZona, dadosObj) -> {
            @SuppressWarnings("unchecked")
            java.util.Map<String, Object> dados = (java.util.Map<String, Object>) dadosObj;
            String precisaIrrigar = (boolean) dados.get("precisaIrrigar") ? "Sim" : "Não";
            String critico = (boolean) dados.get("critico") ? "Sim" : "Não";

            System.out.println("  Zona: " + nomeZona);
            System.out.println("  Dados -> Umidade: " + dados.get("umidade") + "% | " + "Temperatura: " + dados.get("temperatura") + "°C | " + "Precisa Irrigar: " + precisaIrrigar + " | " + "Crítico: " + critico);
        });

        System.out.println("\nFIM DA EXECUÇÃO");
    }
}