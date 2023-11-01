package enums;

public enum Categoria {
    ACT("ACAO"),
    STR("ESTRATEGIA"),

    SIM("SIMULACAO");

    private String nome;

    Categoria(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return nome;
    }
}
