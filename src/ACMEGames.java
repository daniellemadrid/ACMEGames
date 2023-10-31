
import java.util.List;
import java.util.Scanner;

public class ACMEGames {

    Ludoteca ludoteca = new Ludoteca();
    Scanner scanner = new Scanner(System.in);

    public ACMEGames() {
    }

    public void executa() {
        int line;
        do {
            cadastrarJogosEletronicos();
            cadastrarJogosTabuleiro();
            mostrarDadosJogo();
            mostrarDadosPorAnoJogo();
//          mostrarDadoJogoEletronicoPorCategoria();
//            mostrarSomatorioPrecoFinalJogos();
//          mostrarDadosJogoTabuleiroMaiorPrecoFinal();
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

    }

    private void exibirMenu() {
        System.out.println("=== Menu ===");
        System.out.println("[1] Cadastrar jogos eletrônicos");
        System.out.println("[2] Cadastrar jogos de tabuleiro");
        System.out.println("[0] Sair do sistema");
    }

    private void cadastrarJogosEletronicos() {
        System.out.println("=== Cadastrar Jogo Eletrônico ===");

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

            System.out.print("Categoria (ACT, SIM, STR): ");
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
        System.out.println("=== Mostrar Dados de um Jogo ===");
        System.out.print("Nome do Jogo: ");
        String nome = scanner.nextLine();

        Ludoteca ludoteca = new Ludoteca();
        Jogo jogo = ludoteca.consultaPorNome(nome);

        if (jogo != null) {
            System.out.println("3:" + jogo.getNome() + "," + jogo.getPrecoBase() + "," + jogo.getAno() + "," + jogo.calculaPrecoFinal());
        } else {
            System.out.println("3:Nome inexistente.");
        }
    }

    public void mostrarDadosPorAnoJogo() {
        System.out.println("=== Mostrar Dados de um Jogo pelo ano ===");
        System.out.print("Ano do Jogo: ");
        int ano = scanner.nextInt();
        Ludoteca ludoteca = new Ludoteca();
        List<Jogo> jogosDoAno = ludoteca.consultaPorAno(ano);

        if (jogosDoAno.isEmpty()) {
            System.out.println("4; Ano inexistente");
        } else {
            for (Jogo jogo : jogosDoAno) {
                System.out.println("4:" + jogo.getNome() + "," + jogo.getPrecoBase() + "," + jogo.getAno() + "," + jogo.calculaPrecoFinal());
            }
        }
    }

//    public void mostrarSomatorioPrecoFinalJogos() {
//        System.out.println("=== Mostrar Somatório do Preço Final dos Jogos ===");
//        if (jogos.isEmpty()) {
//            System.out.println("6:Nenhum jogo encontrado.");
//        } else {
//            double somatorio = 0;
//            for (Jogo jogo : jogos) {
//                somatorio += jogo.calculaPrecoFinal();
//            }
//            System.out.println("6:Somatório do preço final dos jogos: " + somatorio);
//        }
//    }

}







