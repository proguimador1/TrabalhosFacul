import java.util.Scanner;
import java.util.ArrayList;

public class Principal{

    public static void main(String args[]){

        int vez = 0;
        boolean gameover = false;

        Scanner teclado = new Scanner(System.in);

        Tabuleiro tabuleiro = new Tabuleiro();

        ArrayList<Jogador> jogador = new ArrayList<Jogador>();

        jogador.add(new Jogador("vermelho", 0));
        jogador.add(new Jogador("azul", 0));
        jogador.add(new Jogador("amarelo", 0));
        jogador.add(new Jogador("verde", 0));
        jogador.add(new JogadorAzarado("roxo", 0));
        jogador.add(new JogadorSortudo("laranja", 0));

        System.out.println("Bem-vindo ao nosso jogo de tabuleiro.");
        System.out.println("Vamos começar!");

        while(!gameover){

            int opc = 0;

            if(jogador.get(vez).getPodeJogar()){

                System.out.println();

                if(jogador.get(vez).moverSe(tabuleiro.lancarDados())){

                    System.out.println();

                }
                else if(tabuleiro.getDado1() == tabuleiro.getDado2()){

                    System.out.println();
                    opc = teclado.nextInt();

                }
                else{

                    System.out.println();

                    opc = 1;

                }

                jogador.get(vez).setQtdJogadas();

            }

            switch(tabuleiro.verificarCasaEspeciail(jogador.get(vez))){

                case 1:

                jogador.get(vez).setPodeJogar();

                break;

                case 2:

                tabuleiro.mudarTipo(jogador.get(vez));

                break;

                case 3:

                tabuleiro.avancar(jogador.get(vez));

                break;

                case 4:

                System.out.println();
                String cor = teclado.next();

                int ind;

                for(ind = 0; ind < jogador.size(); ind++){

                    if(jogador.get(ind).getCor().equals(cor))
                        break;
                    
                }

                tabuleiro.retornarJogador(jogador.get(ind));

                break;

                case 5:

                int ind2 = vez;

                for(ind = 0; ind < jogador.size(); ind++){

                    if(jogador.get(ind2).getPosicao() > jogador.get(ind).getPosicao())
                        ind2 = ind;

                }

                if(ind2 == vez){

                    System.out.println();

                }
                else{

                    tabuleiro.trocarComUltimo(jogador.get(vez), jogador.get(ind2));

                    System.out.println();

                }

            }

            gameover = tabuleiro.verificarVitoria(jogador.get(vez));

            if(!gameover && opc != 1){

                if(vez < 5){

                    vez++;

                }
                else{

                    vez = 0;

                }

            }

        }

        teclado.close();

        jogador.clear();
 
    }

}