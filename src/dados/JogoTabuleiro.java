package dados;


public class JogoTabuleiro extends Jogo {
    private int numeroPecas;

    public JogoTabuleiro(String nome, int ano, double precobase, int numeroPecas) {
        super(nome, ano, precobase);
        this.numeroPecas = numeroPecas;
    }

    @Override
    public double calculaPrecoFinal() {
        return getPrecoBase() + (getPrecoBase() * 0.01 * numeroPecas);
    }

    @Override
    public boolean isJogoTabuleiro() {
        return true;
    }

}
