import java.util.ArrayList;

public class Disciplina {

    private ArrayList<Aluno> alunos;
    private String nomeDisciplina;
    private String resultado;

    Disciplina(String nomeDisciplina, ArrayList<String> nomeAlunos, ArrayList<String> respostaAlunos){

        alunos = new ArrayList<Aluno>();

        this.nomeDisciplina = nomeDisciplina;

        for(int i = 0; i < nomeAlunos.size(); i++){

            alunos.add(new Aluno(nomeAlunos.get(i), respostaAlunos.get(i)));

        }

    }

    public String getNome(){

        return nomeDisciplina;

    }

    public String getResult() {
        
        return resultado;

    }

    public void setResult(String resultado){

        this.resultado = resultado;
        
    }
    
}