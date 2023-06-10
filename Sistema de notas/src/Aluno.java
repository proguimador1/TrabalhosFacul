public class Aluno {

    private double nota;
    private String nome;
    private String resposta;

    Aluno(String nome, String resposta){

        this.nome = nome;

        this.resposta = resposta;

    }

    public void setNota(double nota){

        this.nota = nota;
        
    }

    public String getNome(){

        return nome;

    }

    public double getNota(){

        return nota;

    }

    public String getResposta(){

        return resposta;

    }
    
}