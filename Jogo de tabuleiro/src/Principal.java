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

            System.out.println("E a vez do jogador: " + jogador.get(vez).getCor());

            if(jogador.get(vez).getPodeJogar()){

                System.out.print("\n");
                System.out.print("Digite qualquer coisa para jogar:");
                String jogar = teclado.next();
                System.out.print("\n");

                do{
                    
                    // Verifica se o movimento eh invalido
                    if(!jogador.get(vez).moverSe(tabuleiro.lancarDados())){

                        System.out.print("Numero dos dados ");
                        System.out.print(tabuleiro.getDado1() + tabuleiro.getDado2());
                        System.out.print(" nao compativel com o tipo do jogador.");
                        System.out.println(" Jogando novamente...\n");

                    }
                    else{

                        System.out.print("O jogador andou ");
                        System.out.print((tabuleiro.getDado1()+tabuleiro.getDado2()));
                        System.out.println(" casas.");

                        //Checa se os dados sao iguais e altera a variavel opc responsavel por dar a rodada extra
                        if(tabuleiro.getDado1() == tabuleiro.getDado2()){

                            System.out.print("\n");

                            System.out.println("Dados iguais! O jogador jogara novamente ao final da rodada");

                            System.out.print("\n");
                            
                            opc = 1;
    
                        }
                        
                        //Incrementa a qtd de jogadas
                        jogador.get(vez).setQtdJogadas();
                        
                        break;

                    }
                    
                }while(true);

                switch(jogador.get(vez).getPosicao()){

                    case 10:
                    case 25:
                    case 38:
                    
                    System.out.print("O jogador caiu na casa " + jogador.get(vez).getPosicao());
                    System.out.println(". Portanto nao podera se mover na proxima rodada");

                    //Alterna se ele pode jogar ou nao
                    jogador.get(vez).setPodeJogar();

                    break;

                    case 13:

                    System.out.println("O jogador caiu na casa " + jogador.get(vez).getPosicao());
                    tabuleiro.mudarTipo(jogador.get(vez));

                    break;

                    case 5:
                    case 15:
                    case 30:

                    System.out.println("O jogador caiu na casa " + jogador.get(vez).getPosicao());
                    tabuleiro.avancar(jogador.get(vez));

                    break;

                    case 17:
                    case 27:

                    System.out.print("O jogador caiu na casa " + jogador.get(vez).getPosicao());
                    System.out.print(". Digite a cor do jogador que deseja enviar para o incio");
                    System.out.print(" do jogo: ");


                    String cor = "";
                    
                    // Recebe uma string de entrada, que deve ser a cor
                    do {
                        cor = teclado.next();
                    } while (!cor.equals("vermelho") && !cor.equals("azul") && !cor.equals("amarelo")  && !cor.equals("verde") && !cor.equals("laranja") && !cor.equals("roxo"));
                    
                
                    System.out.println("\n");
                    
                    tabuleiro.enviarInicio(jogador, cor);

                    break;

                    case 20:
                    case 35:

                    System.out.print("O jogador caiu na casa " + jogador.get(vez).getPosicao());
                    System.out.println(".\n");
                    
                    tabuleiro.trocarComUltimo(jogador,vez);

                }

            }
            else{

                System.out.print("O jogador caiu em casa especial");
                System.out.println(". Portanto nao pode se mover nessa rodada.");
                jogador.get(vez).setPodeJogar();

            }
            

            gameover = tabuleiro.verificarVitoria(jogador.get(vez));


            //Verifica se o jogo não acabou e se os dados foram diferentes, para poder prosseguir a vez
            if(!gameover && opc != 1){

                if(vez < 5){

                    vez++;

                }
                else{

                    System.out.print("\n");

                    for(int ind = 0; ind < jogador.size(); ind++){

                        System.out.print("Jogador: " + jogador.get(ind).getCor());
                        System.out.print(".\nPosicao: " + jogador.get(ind).getPosicao());
                        System.out.println(".\n");
            
                    }

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
