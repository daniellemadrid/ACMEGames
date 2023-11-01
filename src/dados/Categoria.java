package dados;

public enum Categoria {
    ACAO("ACT"),
    ESTRATEGIA("STR"),

    SIMULACAO("SIM");

    private String nome;

    Categoria(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
