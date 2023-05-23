import java.util.Scanner;

public class Main {
    public static String resetcolor = "\u001B[0m";
    public static void main(String args[]){
        Scanner teclado = new Scanner(System.in);
        String cor = "";
        String color = "";

        System.out.println("Bem vindo ao jogo do robo movimente-se com as teclas 1, 2, 3, 4 ou digitando up, down, right, left");
        System.out.println("Para ganhar voce deve levar o robo ao alimento");
        System.out.print("\n" + "Digite a coordenada X inicial do alimento: ");
        int alimentoX = teclado.nextInt();
        System.out.print("\n" + "Digite a coordenada Y inicial do alimento: ");
        int alimentoY = teclado.nextInt();

        System.out.println("Selecione uma das cores abaixo para o robo.\n");

        do{
            System.out.println("-Vermelho\n-Azul\n-Verde\n-Amarelo");           
            cor = teclado.next();
            switch(cor){
                case "Vermelho":case "vermelho":
                color = "\u001B[31m";
                break;

                case "Azul":case "azul":
                color = "\u001B[34m";
                break;

                case "Verde":case "verde":
                color = "\u001B[32m";
                break;

                case "Amarelo":case "amarelo":
                color = "\u001B[33m";
                break;

                default:
                System.out.println("\nCor invalida. Digite novamente.\n");
                cor = "";
            }

        }while(cor.equals(""));

        Robo r = new Robo(cor);

        System.out.println("\n" + "O robo esta na coordenada (" + r.getX() + "," + r.getY() + ")" + "\n");

        for(int j = 5; j >= 0 ; j--){
            for(int i = 0; i<= 5;i++){
                if(j == r.getY() && i == r.getX()) {
                    System.out.print(color + "r " + resetcolor);
                    continue;
                }
                if ( j == alimentoY && i == alimentoX) {
                    System.out.print("a ");
                    continue;
                }
                
                System.out.print("- ");
            }
            System.out.println("");
        }

        while(!r.encontrouAlimento(alimentoX, alimentoY)){
            int respInt = 0;
            String resp = "";

            System.out.print("\n" + "Digite o movimento desejado:");
            resp = teclado.next();

            switch(resp){
                case "up":case "down":case "right":case "left":
                case "1":case "2":case "3":case "4":
                break;
                default:
                System.out.println("\nComando invalido.");
                resp = "";
            }

            System.out.print("\n");

            try{
                respInt = Integer.parseInt(resp);
            } catch (NumberFormatException ex) {};

            if(respInt == 0){
                try {
                    r.mover(resp);
                } catch (MovimentoInvalidoException e) {
                    System.out.println("O robo " + e + "\n");
                }
            } else {
                try {
                    r.mover(respInt);
                } catch (MovimentoInvalidoException e) {
                    System.out.println("O robo " + e + "\n");
                }
            }

            System.out.println("O robo esta na coordenada (" + r.getX() + "," + r.getY() + ")" + "\n");
            for(int j = 5; j >= 0 ; j--){
                for(int i = 0; i<= 5;i++){
                    if(j == r.getY() && i == r.getX()) {
                        System.out.print(color + "r " + resetcolor);
                        continue;
                    }
                    if ( j == alimentoY && i == alimentoX) {
                        System.out.print("a ");
                        continue;
                    }
                    System.out.print("- ");

                }
                System.out.println("");
            }        
        }

        System.out.println("\n" + "O robo chegou no alimento !!!");
        teclado.close();
    } 
}
