package dados;

import dados.Categoria;
import dados.Jogo;

import java.sql.SQLOutput;

public class JogoEletronico extends Jogo {
    private String plataforma;
    private Categoria categoria;

    public JogoEletronico(String nome, int ano, double precoBase, String plataforma, Categoria categoria){
        super(nome, ano, precoBase);
        this.plataforma = plataforma;
        this.categoria = categoria;
    }

    public Categoria getCategoria(){
        return categoria;
    }

    @Override
    public double calculaPrecoFinal() {
        double percentual = 0.0;
        switch (categoria) {
            case ACAO:
                percentual = 0.1;
                break;
            case SIMULACAO:
                percentual = 0.3;
                break;
            case ESTRATEGIA:
                percentual = 0.7;
                break;
        }
        double precoFinal = getPrecoBase() + (getPrecoBase() * percentual);
        precoFinal = Math.round(precoFinal * 100.0) / 100.0;
        return precoFinal;
    }

    @Override
    public boolean isJogoTabuleiro() {
        return false;
    }

    @Override
    public boolean verificaCategoria(String categoria) {
        return categoria.equals(this.categoria.toString());
    }
}
