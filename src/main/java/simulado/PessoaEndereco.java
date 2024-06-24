package main.java.simulado;

public class PessoaEndereco {
    private int codigo;
    private String nome;
    private String rua;
    private String cidade;

    public PessoaEndereco(int codigo, String nome, String rua, String cidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.rua = rua;
        this.cidade = cidade;
    }

    public String toCsv() {
        return codigo + ";" + nome + ";" + rua + ";" + cidade;
    }
}
