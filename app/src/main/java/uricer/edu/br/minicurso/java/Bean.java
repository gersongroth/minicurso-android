package uricer.edu.br.minicurso.java;

public class Bean {
    private final String nome;
    private final int idade;

    public Bean(String nome,
                int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }


}
