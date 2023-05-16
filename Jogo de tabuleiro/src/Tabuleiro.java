import java.util.Random;

public class Tabuleiro {
    
    private Random valor = new Random();
    private int dado1;
    private int dado2;

    public int getDado1(){

        return dado1;

    }

    public int getDado2(){

        return dado2;

    }

    public int lancarDados(){

        dado1 = valor.nextInt(6) + 1;
        dado2 = valor.nextInt(6) + 1;

        return dado1 + dado2;

    }

    public boolean verificarVitoria(Jogador jogador){

        if(jogador.getPosicao() >= 40){

            jogador.setPosicao(40);

            return true;

        }

        return false;

    }

    public void mudarTipo(Jogador jogador){

        int tipo = valor.nextInt(3);
        int posic = jogador.getPosicao();
        String cor = jogador.getCor();
        boolean verif1 = (jogador instanceof JogadorSortudo);
        boolean verif2 = (jogador instanceof JogadorAzarado);

        if(tipo == 0){

            if(!verif2){

                jogador = new JogadorAzarado(cor, posic);

                System.out.println("Mudou de tipo para azarado.\n");

            }
            else{

                System.out.println("Permanece com o mesmo tipo.\n");

            }

        }
        else if(tipo == 1){

            if(!verif1){

                jogador = new JogadorSortudo(cor, posic);

                System.out.println("Mudou de tipo para sortudo.\n");

            }
            else{

                System.out.println("Permanece com o mesmo tipo.\n");

            }

        }
        else{

            if(verif1 || verif2){

                jogador = new JogadorSortudo(cor, posic);

                System.out.println("Mudou de tipo para normal.\n");

            }
            else{

                System.out.println("Permanece com o mesmo tipo.\n");

            }

        }

    }

    public void avancar(Jogador jogador){

        if(!(jogador instanceof JogadorAzarado)){

            System.out.println("Avancou 3 casas.\n");
            
            jogador.moverSe(3);

        }
        else{

            System.out.println("Permanece no mesmo lugar por ser do tipo azarado.\n");

        }

    }

    public void trocarComUltimo(Jogador jogador1, Jogador jogador2){

        int ultimo = jogador2.getPosicao();
        int outro = jogador1.getPosicao();

        jogador1.setPosicao(ultimo);

        jogador2.setPosicao(outro);

    }
    
    public void enviarInicio(ArrayList<Jogador> jogador){
        
        int ind;

        for(ind = 0; ind < jogador.size(); ind++){

            if(jogador.get(ind).getCor().equals(cor))
                break;
                    
        }

            jogador.get(ind).setPosicao(0);
        
    }
    
}
