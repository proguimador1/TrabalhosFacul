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

        String[] cores = {"vermelho", "azul", "amarelo", "verde", "laranja", "roxo"};

        System.out.println("Bem-vindo ao nosso jogo de tabuleiro.\n");
        System.out.print("Primeiramente, crie os jogadores(min 2/ max 6):");
        System.out.println("\n");

        do{

            if(qtd_jogadores >= 6){

                System.out.println("Numero maximo de jogadores alcancado.\n");
                
                break;

            }

            System.out.print("Escolha o tipo do jogador.");
            System.out.print("Digite 1 para tipo sortudo, 2 para tipo azarado ");
            System.out.println("ou 3 para tipo nomal:");
            String tipo = teclado.next();
            System.out.println("\n");

            if(tipo.equals("1")){

                jogador.add(new JogadorAzarado(cores[qtd_jogadores], 0));

            }
            else if(tipo.equals("2")){

                jogador.add(new JogadorSortudo(cores[qtd_jogadores], 0));

            }
            else{

                jogador.add(new Jogador(cores[qtd_jogadores], 0));

            }

            System.out.print("Jogador ");
            System.out.print(jogador.get(qtd_jogadores + 1).getCor());
            System.out.println(" acabou de ser criado.\n");

            if(qtd_jogadores >= 2){

                    System.out.print("Deseja adicionar outro jogador?");
                    System.out.print("Digite 1 para sim, ou qualquer outro numero");
                    System.out.println(" para nao: ");
                    String opc = teclado.next();
                    System.out.println("\n");

                    if(opc.equals("1"))
                        break;

            }

            qtd_jogadores++;

        }while(true);

        System.out.println("Agora vamos comecar!\n");

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

                int ind;

                for(ind = 0; ind < jogador.size(); ind++){

                    if(jogador.get(ind).getCor().equals(cor))
                        break;
                    
                }

                jogador.get(ind).setPosicao(0);

                break;

                case 20:
                case 35:

                System.out.print("O jogador esta na casa " + jogador.get(vez).getPosicao());
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

                    System.out.print("Trocou de lugar com o jogador ");
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