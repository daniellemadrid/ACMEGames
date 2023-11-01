package controller;

import enums.Categoria;
import model.Jogo;
import model.JogoEletronico;
import model.JogoTabuleiro;
import service.Ludoteca;

import java.util.List;
import java.util.Scanner;

public class ACMEGames {

    Ludoteca ludoteca;
    Scanner scanner = new Scanner(System.in);

    public ACMEGames() {
        this.ludoteca = new Ludoteca();
    }

    public void executa() {
//        try (BufferedReader in = new BufferedReader(new FileReader("dadosin.txt"));
//             BufferedWriter out = new BufferedWriter(new FileWriter("dadosout.txt"))) {
            int line;
            do {
                cadastrarJogosEletronicos();
                cadastrarJogosTabuleiro();
                mostrarDadosJogo();
                mostrarDadosPorAnoJogo();
//          mostrarDadoJogoEletronicoPorCategoria();
                mostrarSomatorioPrecoFinalJogos();
//            mostrarDadosJogoTabuleiroMaiorPrecoFinal();
                exibirMenu();
                line = scanner.nextInt();
                scanner.nextLine();

                switch (line) {
                    case 1:
                        cadastrarJogosEletronicos();
                        break;
                    case 2:
                        cadastrarJogosTabuleiro();
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        exibirMenu();
                }
            } while (line != 0);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    private void exibirMenu() {
        System.out.println("=== Menu ===");
        System.out.println("[1] Cadastrar jogos eletrônicos");
        System.out.println("[2] Cadastrar jogos de tabuleiro");
        System.out.println("[0] Sair do sistema");
    }

    private void cadastrarJogosEletronicos() {
        System.out.println("=== Cadastrar model.Jogo Eletrônico ===");

        while (true) {
            System.out.println("Nome (ou -1 para sair): ");
            String nome = scanner.nextLine();

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
            String plataforma = scanner.nextLine();

            System.out.print("enums.Categoria (ACT, SIM, STR): ");
            String categorias = scanner.nextLine();
            Categoria categoria = Categoria.valueOf(categorias);

            JogoEletronico jogoEletronico = new JogoEletronico(nome, ano, precoBase, plataforma, categoria);
            if (ludoteca.addJogo(jogoEletronico)) {
                System.out.println("1:" + jogoEletronico.getNome() + "," + jogoEletronico.calculaPrecoFinal());
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
            scanner.nextLine();

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
        System.out.println("=== Mostrar Dados de um model.Jogo ===");
        System.out.print("Nome do model.Jogo: ");
        String nome = scanner.next();

        Jogo jogo = ludoteca.consultaPorNome(nome);

        if (jogo != null) {
            System.out.println("3:" + jogo.getNome() + "," + jogo.getPrecoBase() + "," + jogo.getAno() + "," + jogo.calculaPrecoFinal());
        } else {
            System.out.println("3:Nome inexistente.");
        }
    }

    public void mostrarDadosPorAnoJogo() {
        System.out.println("=== Mostrar Dados de um model.Jogo pelo ano ===");
        System.out.print("Ano do model.Jogo: ");
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

//    public void  mostrarDadosJogoEletronicoPorCategoria(){
//        System.out.println("=== Mostrar dados de jogo por categoria ===");
//        System.out.println("enums.Categoria do jogo: ");
//
//        model.JogoEletronico jogoEletronico = new model.JogoEletronico(nome, ano, precoBase, plataforma, categoria);
//
//    }

    public void mostrarSomatorioPrecoFinalJogos() {
        System.out.println("=== Mostrar somatório do preço final dos jogos ===");
        List<Jogo> jogos = ludoteca.getJogos();
        if (jogos.isEmpty()) {
            System.out.println("6:Nenhum jogo encontrado.");
        } else {
            double somatorio = 0;
            for (Jogo jogo : jogos) {
                somatorio += jogo.calculaPrecoFinal();
            }
            System.out.println("6:" + somatorio);
        }
    }

//    public void  mostrarDadosJogoTabuleiroMaiorPrecoFinal(){
//        System.out.println("=== model.Jogo de tabuleiro com maior preço final ===");
//        List<model.Jogo> jogos = ludoteca.getJogos();
//        if (jogos.isEmpty()) {
//            System.out.println("7:Nenhum jogo encontrado.");
//        }else{
//            double somatorio = 0;
//            for (model.Jogo jogo : jogos) {
//                somatorio += jogo.calculaPrecoFinal();
//            }
//            System.out.println("7:");
//        }
//        Mostrar os dados do jogo de tabuleiro com maior preço final: localiza o jogo
//        de tabuleiro cadastrado com maior preço final.
//                Se existir, mostra os dados do jogo no formato: 7:nome,preço final

}









