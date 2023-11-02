package aplicacao;

import dados.Categoria;
import dados.Jogo;
import dados.JogoEletronico;
import dados.JogoTabuleiro;
import dados.Ludoteca;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ACMEGames {

    Ludoteca ludoteca;
    Scanner scanner = new Scanner(System.in);
    private Scanner entrada = null;
    private PrintStream saidaPadrao = System.out;
    private final List<JogoEletronico> jogosEletronicos;

    public ACMEGames() {
        this.ludoteca = new Ludoteca();
        this.jogosEletronicos = new ArrayList<>();
    }

    private void lerArquivos() {
        List<JogoEletronico> jogosEletronicos = new ArrayList<>();
        try {
            BufferedReader streamEntrada = new BufferedReader(new FileReader("src/dadosIn.txt"));
            String line;
            while ((line = streamEntrada.readLine()) != null) {
                String[] parts = line.split(";");

                if (parts.length == 5) {
                    String nome = parts[0];
                    int ano = Integer.parseInt(parts[1]);
                    double precoBase = Double.parseDouble(parts[2]);
                    String plataforma = parts[3];
                    String categoriaNome = parts[4];

                    Categoria categoria = null;
                    for (Categoria c : Categoria.values()) {
                        if (c.getNome().equals(categoriaNome)) {
                            categoria = c;
                            break;
                        }
                    }

                    if (categoria == null) {
                        System.out.println("Invalid categoria: " + categoriaNome);
                        continue;
                    }

                    JogoEletronico jogo = new JogoEletronico(nome, ano, precoBase, plataforma, categoria);
                    jogosEletronicos.add(jogo);
                    System.out.println("Jogo eletrônico cadastrado: " + jogo.getNome());
                    System.out.println("Categoria: " + jogo.getCategoria().getNome());
                    System.out.println("Plataforma: " + jogo.getPlataforma());
                    System.out.println("==================================================");
                } else {
                    System.out.println("Invalid data: " + line);
                }
            }
            streamEntrada.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }



    public void executa() {
        lerArquivos();

        int choice;
        do {
            exibirMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    cadastrarJogosEletronicos();
                    break;
                case 2:
                    cadastrarJogosTabuleiro();
                    break;
                case 3:
                    mostrarDadosJogo();
                    break;
                case 4:
                    mostrarDadosPorAnoJogo();
                    break;
                case 5:
                    mostrarDadosJogoEletronicoPorCategoria();
                    break;
                case 6:
                    mostrarSomatorioPrecoFinalJogos();
                    break;
                case 7:
                    mostrarDadosJogoTabuleiroMaiorPrecoFinal();
                    break;
                case 8:
                    mostrarDadosJogoTabuleiroMaisAntigo();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        } while (choice != 0);
    }

    private void exibirMenu() {
        System.out.println("=== Menu ===");
        System.out.println("[1] Cadastrar jogos eletrônicos");
        System.out.println("[2] Cadastrar jogos de tabuleiro");
        System.out.println("[3] Mostrar Dados de um Jogo");
        System.out.println("[4] Mostrar Dados de um jogo pelo ano");
        System.out.println("[5] Mostrar dados de jogo por categoria");
        System.out.println("[6] Mostrar somatório do preço final dos jogos");
        System.out.println("[7] Jogo de tabuleiro com maior preço final");
        System.out.println("[8] Mostrar dados do jogo de tabuleiro mais antigo");
        System.out.println("[0] Sair do sistema");
    }

    private void cadastrarJogosEletronicos() {
        System.out.println("=== Cadastrar dados de jogo eletrônico ===");

        while (true) {
            System.out.println("Nome (ou -1 para sair): ");
            String nome = scanner.next();

            if ("-1".equals(nome)) {
                break;
            }
            if (ludoteca.consultaPorNome(nome) != null) {
                System.out.println("1:Erro-jogo com nome repetido: " + nome);
                continue;
            }

            System.out.println("Ano: ");
            int ano = scanner.nextInt();

            System.out.print("Preço base: ");
            double precoBase = scanner.nextDouble();

            System.out.print("Plataforma: ");
            String plataforma = scanner.next();

            System.out.print("Categoria (ACAO, SIMULACAO, ESTRATEGIA): ");
            String categorias = scanner.next();
            Categoria categoria = Categoria.valueOf(categorias);

            JogoEletronico jogoEletronico = new JogoEletronico(nome, ano, precoBase, plataforma, categoria);
            if (ludoteca.addJogo(jogoEletronico)) {
                System.out.println("1:" + jogoEletronico.getNome() + "," + jogoEletronico.calculaPrecoFinal() + "," + jogoEletronico.getCategoria());
                jogosEletronicos.add(jogoEletronico);
            }
        }
    }

    private void cadastrarJogosTabuleiro() {
        System.out.println("=== Cadastrar jogo de tabuleiro ===");

        while (true) {
            System.out.println("Nome (ou -1 para sair): ");
            String nome = scanner.next();

            if ("-1".equals(nome)) {
                break;
            }

            if (ludoteca.consultaPorNome(nome) != null) {
                System.out.println("1:Erro-jogo com nome repetido: " + nome);
                continue;
            }

            System.out.print("Ano: ");
            int ano = scanner.nextInt();

            System.out.print("Preço base: ");
            double precoBase = scanner.nextDouble();

            System.out.print("Numero de peças: ");
            int numeroPecas = scanner.nextInt();

            JogoTabuleiro jogoTabuleiro = new JogoTabuleiro(nome, ano, precoBase, numeroPecas);

            if (ludoteca.addJogo(jogoTabuleiro)) {
                System.out.println("1:" + jogoTabuleiro.getNome() + "," + jogoTabuleiro.calculaPrecoFinal());
            }
        }
    }


    private void mostrarDadosJogo() {
        System.out.println("=== Mostrar Dados de um Jogo ===");
        System.out.print("Nome do Jogo: ");
        String nome = scanner.next();

        Jogo jogo = ludoteca.consultaPorNome(nome);

        if (jogo != null) {
            System.out.println("3:" + jogo.getNome() + "," + jogo.getPrecoBase() + "," + jogo.getAno() + jogo.calculaPrecoFinal());
        } else {
            System.out.println("3:Nome inexistente.");
        }
    }

    public void mostrarDadosPorAnoJogo() {
        System.out.println("=== Mostrar Dados de um jogo pelo ano ===");
        System.out.print("Ano do Jogo: ");
        int ano = scanner.nextInt();
        List<Jogo> jogosDoAno = ludoteca.consultaPorAno(ano);

        if (jogosDoAno.isEmpty()) {
            System.out.println("4; Ano inexistente");
        } else {
            for (Jogo jogo : jogosDoAno) {
                System.out.println("4:" + jogo.getNome() + "," + jogo.getPrecoBase() + "," + jogo.getAno() + "," + jogo.calculaPrecoFinal());
            }
        }
    }

    public void mostrarDadosJogoEletronicoPorCategoria() {
        System.out.println("=== Mostrar dados de jogo por categoria ===");
        System.out.print("Categoria do jogo: ");
        String categoria = scanner.next();

        boolean found = false;

        for (JogoEletronico jogo : jogosEletronicos) {
            boolean match = jogo.getCategoria().name().equals(categoria);

            if (match) {
                found = true;
                System.out.println("6:" + jogo.getNome() + "," + jogo.getAno() + "," + jogo.calculaPrecoFinal());
            }
        }

        if (!found) {
            System.out.println("Nenhum jogo encontrado. Tente novamente e verifique o nome e a categoria.");
        }
    }

    public void mostrarSomatorioPrecoFinalJogos() {
        System.out.println("=== Mostrar somatório do preço final dos jogos ===");
        List<Jogo> jogos = ludoteca.getJogos();
        if (jogos.isEmpty()) {
            System.out.println("6:Nenhum jogo encontrado.");
        } else {
            double somatorio = 0.0;
            for (Jogo jogo : jogos) {
                somatorio += jogo.calculaPrecoFinal();
            }
            System.out.println("6:valor do somatório " + somatorio);
        }
    }

    public void mostrarDadosJogoTabuleiroMaiorPrecoFinal() {
        System.out.println("=== Jogo de tabuleiro com maior preço final ===");
        List<Jogo> jogos = ludoteca.getJogos();
        List<JogoTabuleiro> jogosTabuleiro = new ArrayList<>();

        for (Jogo jogo : jogos) {
            if (jogo.isJogoTabuleiro()) {
                jogosTabuleiro.add((JogoTabuleiro) jogo);
            }
        }

        if (jogosTabuleiro.isEmpty()) {
            System.out.println("7:Nenhum jogo encontrado.");
        } else {
            double maiorPrecoFinal = Double.MIN_VALUE;
            dados.Jogo jogoMaiorPrecoFinal = null;

            for (dados.Jogo jogo : jogosTabuleiro) {
                double precoFinal = jogo.calculaPrecoFinal();
                if (precoFinal > maiorPrecoFinal) {
                    maiorPrecoFinal = precoFinal;
                    jogoMaiorPrecoFinal = jogo;
                }
            }

            if (jogoMaiorPrecoFinal != null) {
                System.out.println("7:" + jogoMaiorPrecoFinal.getNome() + "," + maiorPrecoFinal);
            }
        }

    }

    public void mostrarDadosJogoTabuleiroMaisAntigo() {
        System.out.println("=== Mostrar dados do jogo de tabuleiro mais antigo ===");
        List<Jogo> jogos = ludoteca.getJogos();
        if (jogos.isEmpty()) {
            System.out.println("9:Nenhum jogo encontrado.");
        } else {
            Jogo jogoMaisAntigo = jogos.get(0);
            int anoMaisAntigo = jogoMaisAntigo.getAno();

            for (Jogo jogo : jogos) {
                if (jogo.isJogoTabuleiro() && jogo.getAno() < anoMaisAntigo) {
                    anoMaisAntigo = jogo.getAno();
                    jogoMaisAntigo = jogo;
                }
            }

            if (anoMaisAntigo == jogoMaisAntigo.getAno()) {
                System.out.println("9:" + jogoMaisAntigo.getNome() + "," + jogoMaisAntigo.getAno());
            } else {
                System.out.println("9:Todos os jogos têm o mesmo ano.");
            }
        }
    }


}










