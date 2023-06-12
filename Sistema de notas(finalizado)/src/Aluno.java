public class Aluno{

    private String nome;
    private String resposta;
    private int nota;

    Aluno(String nome, String resposta){

        this.nome = nome;

        this.resposta = resposta;
        
    }

    public String getNome(){

        return nome;

    }

    public String getResposta(){

        return resposta;

    }

    public int getNota(){

        return nota;

    }

    public void setNota(int nota){

        this.nota = nota;

    }
    
}