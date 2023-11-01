package service;

import repository.Iterator;
import model.Jogo;

import java.util.ArrayList;
import java.util.List;

public class Ludoteca implements Iterator<Jogo> {
    private int contador;
    private List<Jogo> jogos;
    private int index;


    public Ludoteca() {
        this.jogos = new ArrayList<>();
        this.index = 0;
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public boolean addJogo(Jogo jogo) {
        if (jogo != null) {
            jogos.add(jogo);
            contador++;;
            return true;
        }
        return false;
    }

    public Jogo consultaPorNome(String nome) {
        for (Jogo jogo : jogos) {
            if (jogo.getNome().equals(nome)) {
                return jogo;
            }
        }
        return null;
    }

    public List<Jogo> consultaPorAno(int ano) {
        List<Jogo> jogosDoAno = new ArrayList<>();
        for (Jogo jogo : jogos) {
            if (jogo.getAno() == ano) {
                jogosDoAno.add(jogo);
            }
        }
        return jogosDoAno;
    }

    @Override
    public void reset() {
        index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < jogos.size();
    }

    @Override
    public Jogo next() {
        return jogos.get(index++);
    }
}
