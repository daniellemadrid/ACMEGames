package model;

import enums.Categoria;

public class JogoEletronico extends Jogo {
    private String plataforma;
    private Categoria categoria;

    public JogoEletronico(String nome, int ano, double precoBase, String plataforma, Categoria categoria){
        super(nome, ano, precoBase);
        this.plataforma = plataforma;
        this.categoria = categoria;
    }

    @Override
    public double calculaPrecoFinal() {
        double percentual = 0.0;
        switch (categoria) {
            case ACT:
                percentual = 0.1;
                break;
            case SIM:
                percentual = 0.3;
                break;
            case STR:
                percentual = 0.7;
                break;
        }
        double precoFinal = getPrecoBase() + (getPrecoBase() * percentual);
        precoFinal = Math.round(precoFinal * 100.0) / 100.0;
        return precoFinal;
    }
}
