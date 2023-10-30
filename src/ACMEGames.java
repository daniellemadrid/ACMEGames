
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ACMEGames {
    private List<Jogo> jogos;
    Scanner scanner = new Scanner(System.in);

    public ACMEGames() {
        this.jogos = new ArrayList<>();
    }

    public void executa() {
        int line;
        do {
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
                case 3:
                    mostrarDadosJogo();
                    break;
                case 4:
//                    mostrarDadosPorAnoJogo();
                    break;
                case 5:
//                    mostrarDadoJogoEletronicoPorCategoria();
                    break;
                case 6:
//                    mostrarSomatorioPrecoFinalJogos();
                    break;
                case 7:
//                    MostrarDadosJogoTabuleiroMaiorPrecoFinal();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
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

            if (!existeJogo(nome)) {
                jogos.add(jogoEletronico);
                System.out.println("1:" + jogoEletronico.getNome() + "," + jogoEletronico.calculaPrecoFinal());
            } else {
                System.out.println("1:Erro-jogo com nome repetido: " + jogoEletronico.getNome());
            }

        }
    }

    private boolean existeJogo(String nome) {
        for (Jogo jogo : jogos) {
            if (jogo.getNome().equals(nome)) {
                return true;
            }
        }
        return false;
    }

    private void cadastrarJogosTabuleiro(){
        System.out.println("=== Cadastrar jogo de tabuleiro ===");

        while (true) {
            System.out.println("Nome (ou -1 para sair): ");
            String nome = scanner.next();
            scanner.nextLine();

            if ("-1".equals(nome)) {
                break;
            }

            System.out.print("Ano: ");
            int ano = scanner.nextInt();

            System.out.print("Preço base: ");
            double precoBase = scanner.nextDouble();

            System.out.print("Numero de peças: ");
            int numeroPecas = scanner.nextInt();


            JogoTabuleiro jogoTabuleiro = new JogoTabuleiro(nome, ano, precoBase, numeroPecas);

            if (!existeJogo(nome)) {
                jogos.add(jogoTabuleiro);
                System.out.println("1:" + jogoTabuleiro.getNome() + "," + jogoTabuleiro.calculaPrecoFinal());
            } else {
                System.out.println("1:Erro-jogo com nome repetido: " + jogoTabuleiro.getNome());
            }

        }
    }
    private void mostrarDadosJogo() {
        System.out.println("=== Mostrar Dados de um Jogo ===");
        System.out.print("Nome do Jogo: ");
        String nome = scanner.nextLine();

        for (Jogo jogo : jogos) {
            if (jogo.getNome().equals(nome)) {
                System.out.println("3:" + jogo.getNome() + "," + jogo.getPrecoBase() + "," + jogo.getAno() + jogo.calculaPrecoFinal());
                return;
            }
        }
        System.out.println("3:Nome inexistente.");
    }


}



