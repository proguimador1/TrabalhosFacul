import java.util.Scanner;
import java.util.ArrayList;

public class Principal{

    public static void main(String args[]){

        int vez = 0;
        int qtd_jogadores = 0;
        boolean gameover = false;

        Scanner teclado = new Scanner(System.in);

        Tabuleiro tabuleiro = new Tabuleiro();

        ArrayList<Jogador> jogador = new ArrayList<Jogador>();

        jogador.add(new Jogador("vermelho",0));
        jogador.add(new Jogador("azul",0));
        jogador.add(new Jogador("amarelo",0));
        jogador.add(new Jogador("verde",0));
        jogador.add(new JogadorSortudo("laranja",0));
        jogador.add(new JogadorAzarado("roxo",0));

        while(!gameover){

            int opc = 0;

            if(jogador.get(vez).getPodeJogar()){

                System.out.print("E a vez do jogador: " + jogador.get(vez).getCor());
                System.out.println("\n");
                System.out.print("Pressione enter para jogar:");
                String jogar = teclado.next();
                System.out.println("\n");

                do{
                    
                    if(!jogador.get(vez).moverSe(tabuleiro.lancarDados())){

                        System.out.print("Numero dos dados nao compativel com o tipo do jogador.");
                        System.out.println(" Jogue novamente.\n");

                    }
                    else{

                        System.out.print("O jogador andou.\n");
                        System.out.print((tabuleiro.getDado1()+tabuleiro.getDado2()));
                        System.out.println(" casas.\n");

                        if(tabuleiro.getDado1() == tabuleiro.getDado2()){

                            System.out.println("Dados iguais! Jogando novamente...");

                            System.out.println("\n");
                            
                            opc = 1;
    
                        }
                        
                        jogador.get(vez).setQtdJogadas();
                        
                        break;

                    }
                    
                }while(true);

            }
            else{

                System.out.print("O jogador esta na casa " + jogador.get(vez).getPosicao());
                System.out.println(". Portanto nao pode se mover nessa rodada.");

            }
            
            switch(jogador.get(vez).getPosicao()){

                case 10:
                case 25:
                case 38:
                    
                jogador.get(vez).setPodeJogar();

                break;

                case 13:

                tabuleiro.mudarTipo(jogador.get(vez));

                break;

                case 5:
                case 15:
                case 30:

                tabuleiro.avancar(jogador.get(vez));

                break;

                case 17:
                case 27:

                System.out.print("O jogador esta na casa " + jogador.get(vez).getPosicao());
                System.out.print(". Digite a cor do jogador que deseja enviar para o incio");
                System.out.println(" do jogo:");
                String cor = teclado.next();
                
                System.out.println("\n");
                    
                tabuleiro.enviarInicio(jogador);

                break;

                case 20:
                case 35:

                System.out.print("O jogador esta na casa " + jogador.get(vez).getPosicao());
                System.out.println(".\n");
                    
                tabuleiro.trocarComUltimo(jogador,vez);

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

            System.out.println("\n");

        }

        for(int ind = 0; ind < jogador.size(); ind++){

            System.out.print("Jogador: " + jogador.get(ind).getCor());
            System.out.print(".\nPosicao: " + jogador.get(ind).getPosicao());
            System.out.print(".\nNumero de jogadas: " + jogador.get(ind).getQtdJogadas());
            System.out.println(".\n");
            
        }

        System.out.print("Vencedor: Jogador " + jogador.get(vez).getCor() + "!");

        teclado.close();

        jogador.clear();
 
    }

}
