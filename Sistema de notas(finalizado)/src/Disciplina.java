import java.util.ArrayList;
import java.util.Collections;

public class Disciplina{

    private ArrayList<Aluno> alunos;

    Disciplina(ArrayList<String> respostas,  ArrayList<String> nomes){

        alunos = new ArrayList<Aluno>();
        
        for(int ind = 0; ind < respostas.size(); ind++){

            alunos.add(new Aluno(nomes.get(ind), respostas.get(ind)));

        }

    }

    public void definirNotas(String gabarito){

        for(int ind = 0; ind < alunos.size(); ind++){

            int nota = 0;

            Aluno alu = alunos.get(ind);

            if(!(alu.getResposta().equals("VVVVVVVVVV") || alu.getResposta().equals("FFFFFFFFFF"))){

                for(int ind2 = 0; ind2 < gabarito.length(); ind2++){

                    if(alu.getResposta().charAt(ind2) == gabarito.charAt(ind2))
                        nota++;

                }

            }

            alunos.get(ind).setNota(nota);

        }

    }

    public ArrayList<String> ordenarPorNota(){

        ArrayList<String> listaOrdenada = new ArrayList<String>();

        ArrayList<Integer> listaNotas = new ArrayList<Integer>();

        int indlist = 0;

        for(int ind = 0; ind < alunos.size(); ind++){

            listaNotas.add(alunos.get(ind).getNota());
            
        }

        Collections.sort(listaNotas, Collections.reverseOrder());

        for(int ind1 = 0; ind1 < alunos.size(); ind1++){

            for(int ind2 = ind1; ind2 < alunos.size(); ind2++){

                if(listaNotas.get(ind1) == alunos.get(ind2).getNota()){

                    Aluno alun1 = alunos.get(indlist);
                    Aluno alun2 = alunos.get(ind2);

                    alunos.set(ind2, alun1);
                    alunos.set(indlist, alun2);

                    listaOrdenada.add(alunos.get(indlist).getNota() + "\t" + alunos.get(indlist).getNome());

                    indlist++;

                }

            }

        }

        return listaOrdenada;

    }

    public ArrayList<String> ordenarPorNome(){

        ArrayList<String> listaAlunos = new ArrayList<String>(), listaOrdenada = new ArrayList<String>();

        for(int ind  = 0; ind < alunos.size(); ind++){

            listaAlunos.add(alunos.get(ind).getNome());

        }

        Collections.sort(listaAlunos);

        for(int ind1 = 0; ind1 < alunos.size(); ind1++){

            for(int ind2 = ind1; ind2 < alunos.size(); ind2++){

                if(listaAlunos.get(ind1).equals(alunos.get(ind2).getNome())){

                    Aluno alun1 = alunos.get(ind1);
                    Aluno alun2 = alunos.get(ind2);

                    alunos.set(ind1, alun2);
                    alunos.set(ind2, alun1);

                }

            }

            listaOrdenada.add(alunos.get(ind1).getNota() + "\t" + alunos.get(ind1).getNome());

        }

        return listaOrdenada;

    }
    
    public void imprimirMedia(){

        int total = 0;

        double media;

        for(int ind  = 0; ind < alunos.size(); ind++){

            total += alunos.get(ind).getNota();

        }

        media = total/alunos.size();

        System.out.println("\nMedia da turma: " + media + "\n");

    }

}