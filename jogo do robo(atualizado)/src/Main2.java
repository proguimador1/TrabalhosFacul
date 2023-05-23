import java.util.Random;
import java.util.Scanner;

public class Main2 {
    public static String resetcolor = "\u001B[0m";
    public static void main(String args[]) throws InterruptedException{

        Scanner teclado = new Scanner(System.in);
        Random rand = new Random();
        String cor[] = {"",""};
        String color[] = {"",""};
        int qtdrobos = 0;

        System.out.println("Bem vindo ao jogo do robo");
        System.out.println("Para ganhar o robo deve chegar ao alimento primeiro");

        System.out.print("\n" + "Digite a coordenada X inicial do alimento: ");
        int alimentoX = teclado.nextInt();

        System.out.print("\n" + "Digite a coordenada Y inicial do alimento: ");
        int alimentoY = teclado.nextInt();

        System.out.println("Selecione as cores para os dois robos.\n(Digite cores diferentes para ambos)\n");

        do{
            System.out.println("\n-Vermelho\n-Azul\n-Verde\n-Amarelo");           
            cor[qtdrobos] = teclado.next();

            switch(cor[qtdrobos]){
                case "Vermelho":case "vermelho":
                color[qtdrobos] = "\u001B[31m";
                break;
                case "azul":case "Azul":
                color[qtdrobos] = "\u001B[34m";
                break;
                case "amarelo":case"Amarelo":
                color[qtdrobos] = "\u001B[33m";
                break;     
                case "verde":case "Verde":
                color[qtdrobos] = "\u001B[32m";
                break;          
                default:
                System.out.println("\nCor invalida. Digite novamente.\n");
                cor[qtdrobos] = "";
                continue;
            }
            qtdrobos++;
        }while(qtdrobos<2);

        Robo r1 = new Robo(cor[0]);
        Robo r2 = new Robo(cor[1]);

        System.out.println("\n" + "O robo 1 esta na coordenada (" + r1.getX() + "," + r1.getY() + ")");
        System.out.println("\n" + "O robo 2 esta na coordenada (" + r2.getX() + "," + r2.getY() + ")");

        for(int j = 5; j >= 0 ; j--){
            for(int i = 0; i<= 5;i++){

                if(j == 0 && i == 0) {
                    System.out.print(color[0] + "r1" + color[1] + "r2 " + resetcolor);                  
                    continue;
                }
                if ( j == alimentoY && i == alimentoX) {
                    System.out.print("alim ");
                    continue;
                }
                System.out.print("---- ");
                
            }
            System.out.println("");
        }
        
        int movR1 = 0;
        int movR2 = 0;
        int movValR1 = 0;
        int movInvalR1 = 0;
        int movValR2 = 0;
        int movInvalR2 = 0;
        

        while(!r1.encontrouAlimento(alimentoX, alimentoY) && !r2.encontrouAlimento(alimentoX, alimentoY)){
            //Delay em java
            Thread.sleep(750);

            movR1 = rand.nextInt(4) + 1;
            movR2 = rand.nextInt(4) + 1;
            
            try{
                r1.mover(movR1);
                movValR1++;
            } catch (MovimentoInvalidoException e) {
                System.out.println("\n" + "O robo 1 " + e);
                movInvalR1++;
            }

            System.out.println("\n" + "O robo 1 esta na coordenada (" + r1.getX() + "," + r1.getY() + ")");
            System.out.println("O robo 2 esta na coordenada (" + r2.getX() + "," + r2.getY() + ")" + "\n");


            for(int j = 5; j >= 0 ; j--){
                for(int i = 0; i<= 5;i++){
    
                    if(j == r1.getY() && i == r1.getX()) {
                        if(j == r2.getY() && i == r2.getX()) {
                            System.out.print(color[0] + "r1" + color[1] + "r2 " + resetcolor);
                            continue;
                        } 
                        System.out.print(color[0] + "r1" + resetcolor + "-- ");
                        continue;
                    }
    
                    if(j == r2.getY() && i == r2.getX()) {
                        System.out.print(color[1] + "r2" + resetcolor + "-- ");
                        continue;
                    }
    
                    if ( j == alimentoY && i == alimentoX) {
                        System.out.print("alim ");
                        continue;
                    }
    
                    System.out.print("---- ");
                }
                System.out.println("");
            }

            if(r1.encontrouAlimento(alimentoX, alimentoY)){
                System.out.println("\n" + "o robo 1 chegou no alimento !!!");
                break;
            }

           //Delay em java
            Thread.sleep(750);


            try{
                r2.mover(movR2);
                movValR2++;
            } catch (MovimentoInvalidoException e) {
                System.out.println("\n" + "O robo 2 " + e);
                movInvalR2++;
            }
        

            System.out.println("\n" + "O robo 1 esta na coordenada (" + r1.getX() + "," + r1.getY() + ")");
            System.out.println("O robo 2 esta na coordenada (" + r2.getX() + "," + r2.getY() + ")" + "\n");

            for(int j = 5; j >= 0 ; j--){
                for(int i = 0; i<= 5;i++){
    
                    if(j == r1.getY() && i == r1.getX()) {
                        if(j == r2.getY() && i == r2.getX()) {
                            System.out.print(color[0] + "r1" + color[1] + "r2 " + resetcolor);
                            continue;
                        } 
                        System.out.print(color[0] + "r1" + resetcolor + "-- ");
                        continue;
                    }
    
                    if(j == r2.getY() && i == r2.getX()) {
                        System.out.print(color[1] + "r2" + resetcolor + "-- ");
                        continue;
                    }
    
                    if ( j == alimentoY && i == alimentoX) {
                        System.out.print("alim ");
                        continue;
                    }
    
                    System.out.print("---- ");
                }
                System.out.println("");
            }

            if(r2.encontrouAlimento(alimentoX, alimentoY)){
                System.out.println("\n" + "o robo 2 chegou no alimento !!!");
                break;
            }
                
        }


        System.out.println("\nO numero de movimento validos do robo " + r1.getCor() + " foi: " + movValR1);
        System.out.println("O numero de invalidos foi: " + movInvalR1);
        System.out.println("O numero de movimento validos do robo " + r2.getCor() + " foi: " + movValR2);
        System.out.println("O numero de invalidos foi: " + movInvalR2);
        teclado.close();
    }
}