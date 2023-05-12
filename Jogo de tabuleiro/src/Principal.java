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
        System.out.println("Vamos começar!\n\n");

        while(!gameover){

            int opc = 0;

            if(jogador.get(vez).getPodeJogar()){

                System.out.print("E a vez do jogador: " + jogador.get(vez).getCor());
                System.out.println("\n");

                if(!jogador.get(vez).moverSe(tabuleiro.lancarDados())){

                    System.out.print("Numero dos dados nao compativel com o tipo do jogador.");
                    System.out.println(" Jogue novamente.\n");

                    opc = 1;

                }
                else{

                    System.out.print("O jogador andou ");
                    System.out.print((tabuleiro.getDado1()+tabuleiro.getDado2()));
                    System.out.println(" casas.\n");

                    if(tabuleiro.getDado1() == tabuleiro.getDado2()){

                        System.out.print("Dados iguais! ");
                        System.out.print("Digite 1 para jogar novamente, ou qualquer ");
                        System.out.println("outro numero para passar a vez:");
                        opc = teclado.nextInt();

                        System.out.println("\n");
    
                    }

                }

                jogador.get(vez).setQtdJogadas();

            }
            else{

                System.out.print("O jogador " + jogador.get(vez).getCor());
                System.out.print(" esta na casa " + jogador.get(vez).getPosicao());
                System.out.println(". Portanto nao pode se mover nessa rodada.");

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

                System.out.print("O jogador " + jogador.get(vez).getCor());
                System.out.print(" esta na casa " + jogador.get(vez).getPosicao());
                System.out.print(". Digite a cor do jogador que deseja enviar para ");
                System.out.println("o incio do jogo:");
                String cor = teclado.next();
                
                System.out.println("\n");

                int ind;

                for(ind = 0; ind < jogador.size(); ind++){

                    if(jogador.get(ind).getCor().equals(cor))
                        break;
                    
                }

                tabuleiro.retornarJogador(jogador.get(ind));

                break;

                case 5:

                System.out.print("O jogador " + jogador.get(vez).getCor());
                System.out.print(" esta na casa " + jogador.get(vez).getPosicao());
                System.out.println(".\n");

                int ind2 = vez;

                for(ind = 0; ind < jogador.size(); ind++){

                    if(jogador.get(ind2).getPosicao() > jogador.get(ind).getPosicao())
                        ind2 = ind;

                }

                if(ind2 == vez){

                    System.out.println("Permanece no mesmo lugar por ja ser o ultimo.\n");

                }
                else{

                    tabuleiro.trocarComUltimo(jogador.get(vez), jogador.get(ind2));

                    System.out.print("O jogador " + jogador.get(vez).getCor());
                    System.out.print(" trocou de lugar com o jogador ");
                    System.out.print(jogador.get(ind2).getCor());
                    System.out.println(".\n");

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

            for(int ind = 0; ind < jogador.size(); ind++){

                System.out.print("Jogador: " + jogador.get(ind).getCor());
                System.out.print(".\nPosicao: " + jogador.get(ind).getPosicao());
                System.out.print(".\nNumero de jogadas: " + jogador.get(ind).getQtdJogadas());
                System.out.println(".\n");

            }

            System.out.println("\n");

        }

        System.out.print("Vencedor: Jogador " + jogador.get(vez).getCor() + "!");

        teclado.close();

        jogador.clear();
 
    }

}