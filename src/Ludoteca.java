import java.util.ArrayList;
import java.util.List;

public class Ludoteca {
    private int contador;
    private List<Jogo> jogos;


    public Ludoteca() {
        this.jogos = new ArrayList<>();
    }

    public boolean addJogo(Jogo jogo) {
        if (jogo != null) {
            jogos.add(jogo);
            contador++;
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
}
