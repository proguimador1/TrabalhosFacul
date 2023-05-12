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
    
    public int verificarCasaEspeciail(Jogador jogador){
    
       switch(jogador.getPosicao()){
           
           case 10:
           case 25:
           case 38:
               return 1;
           case 13:
               return 2;
           case 5:
           case 15:
           case 30:
               return 3;
           case 17:
           case 27:
               return 4;
           case 20:
           case 35:
               return 5;
               
       }
        
        return -1;
        
    }

    public void mudarTipo(Jogador jogador){

        int tipo = valor.nextInt(3);
        int posic = jogador.getPosicao();
        String cor = jogador.getCor();
        boolean verif1 = (jogador instanceof JogadorSortudo);
        boolean verif2 = (jogador instanceof JogadorAzarado);

        if(tipo == 0){

            if(verif1 || !(verif1 || verif2)){

                jogador = new JogadorAzarado(cor, posic);

                System.out.println();

            }
            else{

                System.out.println();

            }

        }
        else if(tipo == 1){

            if(verif2 || !(verif1 || verif2)){

                jogador = new JogadorSortudo(cor, posic);

                System.out.println();

            }
            else{

                System.out.println();

            }

        }
        else{

            if(verif1 || verif2){

                jogador = new JogadorSortudo(cor, posic);

                System.out.println();

            }
            else{

                System.out.println();

            }

        }

    }

    public void retornarJogador(Jogador jogador){

        jogador.setPosicao(0);

    }

    public void avancar(Jogador jogador){

        if(!(jogador instanceof JogadorAzarado)){
            
            jogador.moverSe(3);

        }
        else{

            System.out.println();

        }

    }

    public void trocarComUltimo(Jogador jogador1, Jogador jogador2){

        int ultimo = jogador2.getPosicao();
        int outro = jogador1.getPosicao();

        jogador1.setPosicao(ultimo);

        jogador2.setPosicao(outro);

    }
    
}