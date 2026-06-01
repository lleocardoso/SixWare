import enums.*;
import model.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // "BD" em memória para os cadastros.
        List<Usuario> usuarios = new ArrayList<>();
        List<Funcionario> funcionarios = new ArrayList<>();
        List<Solo> solos = new ArrayList<>();
        List<Zona> zonas = new ArrayList<>();

        // Geradores de ID Sequencial
        int contadorUsuario = 1;
        long contadorFuncionario = 1;
        int contadorSolo = 1;
        int contadorZona = 1;

        boolean executando = true;

        //Menu
        while (executando) {
            System.out.println("\n----------------------------------------------");
            System.out.println("   SISTEMA DE MONITORAMENTO AGRÍCOLA - SIXWARE   ");
            System.out.println("----------------------------------------------");
            System.out.println("1. Gerenciar Usuários (Administradores)");
            System.out.println("2. Gerenciar Funcionários");
            System.out.println("3. Gerenciar Empresa");
            System.out.println("4. Executar Verificação");
            System.out.print("Escolha uma opção: ");

            int opcao = -1;
            try {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("\n[ERRO] Entrada inválida! Por favor, digite apenas números.");
                scanner.nextLine();
                continue;
            }

            switch (opcao) {
                case 1:
                    boolean menuUsuario = true;
                    while (menuUsuario) {
                        System.out.println("\n--- GERENCIAR USUÁRIOS (ADMINS) ---");
                        System.out.println("1. Adicionar Usuário");
                        System.out.println("2. Remover Usuário");
                        System.out.println("3. Listar Usuários");
                        System.out.println("0. Voltar");
                        System.out.print("Escolha: ");

                        int opUsu = -1;
                        try {
                            opUsu = scanner.nextInt();
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("\n[ERRO] Digite apenas o número da opção!");
                            scanner.nextLine();
                            continue;
                        }

                        switch (opUsu) {
                            case 1:
                                System.out.println("\n-- ADICIONAR USUÁRIO --");
                                int idUsuario = contadorUsuario++;
                                System.out.println("ID: " + idUsuario);
                                System.out.print("Nome: ");
                                String nomeUsuario = scanner.nextLine();
                                System.out.print("Email: ");
                                String emailUsuario = scanner.nextLine();
                                System.out.print("Senha: ");
                                String senhaUsuario = scanner.nextLine();

                                Usuario novoUsuario = new Usuario(idUsuario, nomeUsuario, emailUsuario, senhaUsuario);
                                usuarios.add(novoUsuario);
                                System.out.println("-> Sucesso: Usuário '" + nomeUsuario + "' cadastrado!");
                                break;

                            case 2:
                                System.out.println("\n-- REMOVER USUÁRIO --");
                                if (usuarios.isEmpty()) {
                                    System.out.println("Atenção: Não há usuários cadastrados.");
                                    break;
                                }
                                System.out.print("Digite o ID do usuário para remover (ou 0 para cancelar): ");
                                try {
                                    int idRemoverU = scanner.nextInt();
                                    scanner.nextLine();

                                    if (idRemoverU == 0) {
                                        System.out.println("Operação cancelada.");
                                        break;
                                    }

                                    boolean removidoU = usuarios.removeIf(u -> u.getId() == idRemoverU);
                                    if (removidoU) {
                                        System.out.println("-> Sucesso: Usuário removido do sistema!");
                                    } else {
                                        System.out.println("Erro: Usuário com ID " + idRemoverU + " não encontrado.");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("[ERRO] O ID deve ser um número.");
                                    scanner.nextLine();
                                }
                                break;

                            case 3:
                                System.out.println("\n-- LISTA DE USUÁRIOS --");
                                System.out.println("Total de Admins cadastrados: " + usuarios.size());
                                if (usuarios.isEmpty()) {
                                    System.out.println("Nenhum usuário cadastrado no momento.");
                                } else {
                                    System.out.println("------------------------------------------------------------");
                                    System.out.printf("%-5s | %-20s | %-25s\n", "ID", "NOME", "EMAIL");
                                    System.out.println("------------------------------------------------------------");
                                    for (Usuario u : usuarios) {
                                        System.out.printf("%-5d | %-20s | %-25s\n", u.getId(), u.getNome(), u.getEmail());
                                    }
                                    System.out.println("------------------------------------------------------------");
                                }
                                break;

                            case 0:
                                menuUsuario = false;
                                break;

                            default:
                                System.out.println("\nOpção inválida no menu de usuários.");
                                break;
                        }
                    }
                    break;

                case 2:
                    boolean menuFuncionario = true;
                    while (menuFuncionario) {
                        System.out.println("\n--- SETOR DE FUNCIONARIOS ---");
                        System.out.println("1. Adicionar Funcionário");
                        System.out.println("2. Remover Funcionário (Desligamento)");
                        System.out.println("3. Listar Equipe Completa");
                        System.out.println("0. Voltar");
                        System.out.print("Escolha: ");

                        int opFunc = -1;
                        try {
                            opFunc = scanner.nextInt();
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("\n[ERRO] Digite apenas o número da opção!");
                            scanner.nextLine();
                            continue;
                        }

                        if (opFunc == 1) {
                            long idFunc = contadorFuncionario++;
                            System.out.println("\nID: " + idFunc);
                            System.out.print("Nome: ");
                            String n = scanner.nextLine();
                            Cargo c = null;
                            boolean cV = false;
                            while (!cV) {
                                System.out.print("Cargo (Tecnico, Gerente, Agronomo): ");
                                try {
                                    c = Cargo.valueOf(scanner.nextLine());
                                    cV = true;
                                } catch (Exception e) { System.out.println("Cargo inválido!"); }
                            }
                            funcionarios.add(new Funcionario(idFunc, n, c));
                            System.out.println("-> Funcionário adicionado!");
                        } else if (opFunc == 2) {
                            System.out.print("ID para remover: ");
                            try {
                                long idR = scanner.nextLong();
                                if(funcionarios.removeIf(f -> f.getId() == idR)) System.out.println("-> Removido!");
                                else System.out.println("-> ID não encontrado.");
                            } catch (InputMismatchException e) {
                                System.out.println("[ERRO] O ID deve ser um número.");
                                scanner.nextLine();
                            }
                        } else if (opFunc == 3) {
                            System.out.println("\n-- EQUIPE SIXWARE --");
                            System.out.println("Total de Funcionários cadastrados: " + funcionarios.size());
                            if (funcionarios.isEmpty()) {
                                System.out.println("Nenhum funcionário cadastrado no momento.");
                            } else {
                                System.out.println("---------------------------------------------------");
                                System.out.printf("%-5s | %-20s | %-15s\n", "ID", "NOME", "CARGO");
                                System.out.println("---------------------------------------------------");
                                for (Funcionario f : funcionarios) {
                                    System.out.printf("%-5d | %-20s | %-15s\n", f.getId(), f.getNome(), f.getCargo());
                                }
                                System.out.println("---------------------------------------------------");
                            }
                        } else if (opFunc == 0) {
                            menuFuncionario = false;
                        } else {
                            System.out.println("\nOpção inválida.");
                        }
                    }
                    break;

                case 3:
                    boolean menuEmpresa = true;
                    while (menuEmpresa) {
                        System.out.println("\n--- GERENCIAR PATRIMÔNIO (EMPRESA) ---");
                        System.out.println("1. Cadastrar Novo Solo (Propriedade)");
                        System.out.println("2. Cadastrar Nova Zona");
                        System.out.println("3. Resumo de Ativos (Listar Solos e Zonas)");
                        System.out.println("0. Voltar");
                        System.out.print("Escolha: ");

                        int opEmp = -1;
                        try {
                            opEmp = scanner.nextInt();
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("\n[ERRO] Digite apenas o número da opção!");
                            scanner.nextLine();
                            continue;
                        }

                        switch (opEmp) {
                            case 1:
                                if (usuarios.isEmpty()) {
                                    System.out.println("Erro: Cadastre um Usuário (Admin) primeiro!");
                                    break;
                                }
                                int idS = contadorSolo++;
                                System.out.print("Nome da propriedade: ");
                                String nS = scanner.nextLine();
                                System.out.print("Localização: ");
                                String lS = scanner.nextLine();
                                System.out.println("Selecione o ID do Admin responsável:");
                                for (Usuario u : usuarios) System.out.println(u.getId() + " - " + u.getNome());
                                System.out.print("ID: ");
                                try {
                                    int idU = scanner.nextInt();
                                    scanner.nextLine();
                                    Usuario responsavel = usuarios.stream().filter(u -> u.getId() == idU).findFirst().orElse(usuarios.get(0));
                                    solos.add(new Solo(idS, nS, lS, responsavel));
                                    System.out.println("-> Solo cadastrado!");
                                } catch (InputMismatchException e) {
                                    System.out.println("[ERRO] O ID deve ser um número. Cadastro cancelado.");
                                    scanner.nextLine();
                                }
                                break;

                            case 2:
                                if (solos.isEmpty()) {
                                    System.out.println("Erro: Cadastre um Solo primeiro!");
                                    break;
                                }
                                int idZ = contadorZona++;
                                System.out.print("Nome da Zona: ");
                                String nZ = scanner.nextLine();
                                System.out.print("Setor: ");
                                String sZ = scanner.nextLine();
                                System.out.println("Vincular ao ID do Solo:");
                                for (Solo s : solos) System.out.println(s.getId() + " - " + s.getNome());
                                System.out.print("ID: ");
                                try {
                                    int idSel = scanner.nextInt();
                                    scanner.nextLine();
                                    Solo sVinculo = solos.stream().filter(s -> s.getId() == idSel).findFirst().orElse(null);
                                    if (sVinculo != null) {
                                        Zona nz = new Zona(idZ, nZ, sZ, new ConfiguracaoZona(30, 80, 38, 60));
                                        sVinculo.adicionarZona(nz);
                                        zonas.add(nz);
                                        System.out.println("-> Zona vinculada com sucesso!");
                                    } else {
                                        System.out.println("Erro: ID de Solo não encontrado!");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("[ERRO] O ID deve ser um número. Cadastro cancelado.");
                                    scanner.nextLine();
                                }
                                break;

                            case 3:
                                System.out.println("\n-- RESUMO PATRIMONIAL --");
                                System.out.println("SOLOS (" + solos.size() + "):");
                                for (Solo s : solos) System.out.println(" - " + s.getNome() + " [" + s.getLocalizacao() + "] | Admin: " + s.getUsuario().getNome());
                                System.out.println("\nZONAS (" + zonas.size() + "):");
                                for (Zona z : zonas) System.out.println(" - " + z.getNome() + " (Setor: " + z.getSolo() + ")");
                                System.out.println("------------------------");
                                break;

                            case 0:
                                menuEmpresa = false;
                                break;

                            default:
                                System.out.println("\nOpção inválida.");
                                break;
                        }
                    }
                    break;

                case 4:
                    System.out.println("\n--- INICIANDO VERIFICAÇÃO ---");

                    //Validações Iniciais de Dependência.
                    if (solos.isEmpty() || zonas.isEmpty()) {
                        System.out.println("Erro de Validação: Para iniciar a verificação, é necessário cadastrar pelo menos 1 Solo e 1 Zona no menu Empresa (Opção 3).");
                        break;
                    }
                    // Verifica se existe profissional capacitado para inspeções manuais de pragas.
                    Funcionario funcAtivo = funcionarios.stream().filter(f -> f.getCargo() == Cargo.Agronomo || f.getCargo() == Cargo.Tecnico) .findFirst() .orElse(null);

                    if (funcAtivo == null) {
                        System.out.println("Erro de Validação: A inspeção de campo requer um funcionário com cargo de Agrônomo ou Técnico. Vá no setor de Funcionarios (Opção 2) e cadastre um.");
                        break;
                    }
                    //Mocking do Setup do Hardware.
                    Solo soloAtivo = solos.get(0);
                    Zona zonaAtiva = soloAtivo.getZonas().isEmpty() ? zonas.get(0) : soloAtivo.getZonas().get(0);
                    Usuario usuarioAtivo = soloAtivo.getUsuario();

                    System.out.println("-> Iniciando verificação na " + zonaAtiva.getNome() + " (Propriedade: " + soloAtivo.getNome() + ")\n");

                    // Instancia Placa IoT e vincula sensores
                    Dispositivo dispositivo = new Dispositivo(1L, "35-30-A8-3D-D5-AC", "AgriSensor-X200", StatusDispositivo.Ativo);
                    SensorUmidade sensorUmidade = new SensorUmidade(1L, 30.0f, 80.0f, dispositivo);
                    SensorTemperatura sensorTemp = new SensorTemperatura(2L, 38.0f, dispositivo);

                    dispositivo.getSensores().add(sensorUmidade);
                    dispositivo.getSensores().add(sensorTemp);

                    if (zonaAtiva.getDispositivos().isEmpty()) {
                        zonaAtiva.getDispositivos().add(dispositivo);
                    }

                    dispositivo.sincronizar();
                    System.out.println("Status dispositivo: " + dispositivo.getStatus());
                    System.out.println();

                    // Instancia Atuador (Válvula de fluxo de água).
                    Valvula valvula = new Valvula(1L, "Válvula Principal", StatusValvula.Fechada, zonaAtiva);
                    if (zonaAtiva.getValvulas().isEmpty()) {
                        zonaAtiva.getValvulas().add(valvula);
                    }
                    System.out.println("Válvula adicionada: " + valvula.getNome() + " | Status: " + valvula.getStatus());
                    System.out.println();

                    //Simulação de Coleta de Dados em Tempo Real.
                    System.out.println("COLETA DE LEITURAS");
                    LeituraSensor leituraUmidade = new LeituraSensor(1L, 25.0f, LocalDateTime.now(), sensorUmidade);
                    LeituraSensor leituraTemp    = new LeituraSensor(2L, 36.0f, LocalDateTime.now(), sensorTemp);

                    sensorUmidade.setUltimaLeitura(leituraUmidade);
                    sensorTemp.setUltimaLeitura(leituraTemp);

                    // Imprime as métricas
                    System.out.println("  Umidade coletada : " + leituraUmidade.getValor() + "%");
                    System.out.println("  Temperatura colet.: " + leituraTemp.getValor() + "°C");
                    System.out.println("  Umidade em déficit: " + (sensorUmidade.verificarDeficit() ? "Sim" : "Não"));
                    System.out.println("  Superaquecimento  : " + (sensorTemp.verificarSuperaquecimento() ? "Sim" : "Não"));
                    System.out.println("  Leitura anômala   : " + (leituraUmidade.isAnomalia() ? "Sim" : "Não"));
                    System.out.println();

                    //Execução do Algoritmo de Controle Booleano.
                    System.out.println("CONTROLADOR DE IRRIGAÇÃO");
                    ControladorIrrigacao controlador = new ControladorIrrigacao(zonaAtiva);
                    controlador.processarLeitura(leituraUmidade);
                    controlador.processarCondicoes();
                    System.out.println("  Válvula após controle: " + valvula.getStatus());
                    System.out.println();

                    //Teste de Sistema de Notificação.
                    System.out.println("ALERTAS");
                    Alerta alerta = new Alerta(1L, TipoAlerta.Umidade, "Umidade abaixo do limite mínimo na Zona: " + zonaAtiva.getNome());
                    alerta.notificarUsuario();
                    System.out.println("  Resolvido antes: " + (alerta.isResolvido() ? "Sim" : "Não"));
                    alerta.resolver();
                    System.out.println("  Resolvido depois: " + (alerta.isResolvido() ? "Sim" : "Não"));
                    System.out.println();

                    //Teste de Acionamento e Registro Histórico.
                    System.out.println("ACIONAMENTO");
                    Acionamento acionamento = new Acionamento(1L, OrigemAcionamento.Automatico, LocalDateTime.now().minusMinutes(5), usuarioAtivo);
                    controlador.encerrarIrrigacao();
                    acionamento.encerrar();
                    System.out.println("  Duração do acionamento: " + acionamento.getDuracao().toMinutes() + " minuto(s)");
                    System.out.println();

                    //Teste do Módulo de Inpeção Manual (Identificação de Pragas).
                    System.out.println("INSPEÇÃO DE CAMPO");
                    Inspecao inspecao = new Inspecao(1L, "Verificação de rotina - sinais de fungo detectados ", funcAtivo);
                    funcAtivo.registrarInspecao(inspecao);

                    PragaDetectada praga = new PragaDetectada(1L, List.of(TipoPraga.FUNGO), 8, "Mancha foliar identificada em 30% das plantas", zonaAtiva);
                    inspecao.registrarPraga(praga);
                    System.out.println("  Praga crítica: " + (praga.isCritical() ? "Sim" : "Não") + " (gravidade: " + praga.getNivelGravidade() + "/10)");
                    System.out.println();

                    //Relatório Final de Fechamento de Ciclo
                    System.out.println("STATUS GERAL DO SOLO (" + soloAtivo.getNome() + ")");
                    soloAtivo.getStatusGeral().forEach((nomeZ, dadosObj) -> {
                        @SuppressWarnings("unchecked")
                        java.util.Map<String, Object> dados = (java.util.Map<String, Object>) dadosObj;
                        String precisaIrrigar = (boolean) dados.get("precisaIrrigar") ? "Sim" : "Não";
                        String critico = (boolean) dados.get("critico") ? "Sim" : "Não";

                        System.out.println("  Zona: " + nomeZ);
                        System.out.println("  Dados -> Umidade: " + dados.get("umidade") + "% | " + "Temperatura: " + dados.get("temperatura") + "°C | " + "Precisa Irrigar: " + precisaIrrigar + " | " + "Crítico: " + critico);
                    });
                    System.out.println("--------------------------------------------------\n");
                    break;

                case 0:
                    executando = false;
                    break;

                default:
                    System.out.println("\nOpção inválida no menu principal!");
                    break;
            }
        }
        scanner.close();
    }
}