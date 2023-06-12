import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner teclado = new Scanner(System.in);
        teclado.useDelimiter("\r?\n");
        LimpadorConsole limpadorTela = new LimpadorConsole();
        Disciplina disciplina;


        while(true) {     

            try {
                limpadorTela.limparTela();
            } catch (IOException e) {
                System.out.println(e);
            } catch (InterruptedException e2) {
                System.out.println(e2);
            }

            System.out.println("------------Menu Principal------------");

            
            System.out.println("\n" + "Digite a opcao desejada:" + "\n");
            System.out.println("1 - Adicionar respostas de alunos de uma disciplina");
            System.out.println("2 - Gerar arquivos contendo gabarito dos alunos de uma disciplina");
            System.out.println("3 - Sair do sistema");
            String opcao;
            do {
                System.out.print("Opc: ");
                opcao = teclado.next();
            } while (!(opcao.equals("1") || opcao.equals("2") || opcao.equals("3")));
    
            if (opcao.equals("1")) {
                File diretorio = new File("./");
                File subdir1 = new File(diretorio, "RespostasDisciplinas");
                subdir1.mkdir();
                System.out.print("\n" + "Digite o nome da disciplina a adicionar: ");
                String nomeDisciplina = teclado.next();
                File arquivo = new File(subdir1, nomeDisciplina + ".txt");

                try {
                    FileWriter fw = new FileWriter(arquivo, true);
                    BufferedWriter bw = new BufferedWriter(fw);

                    while (true){


                        //Submenu 1
                        System.out.println("\n" + "Digite a opcao desejada:" + "\n");
                        System.out.println("1 - Adicionar as respostas de um aluno");
                        System.out.println("2 - Voltar ao menu");
        
                        do {
                            System.out.print("Opc: ");
                            opcao = teclado.next();
                        } while (!(opcao.equals("1") || opcao.equals("2")));
        
                        if(opcao.equals("1")){
                            System.out.print("\n" + "Digite as 10 respostas (ex: VFVFVFVFVF): ");
                            String respostas = teclado.next();
                            System.out.print("\n" + "Digite agora o nome do aluno: ");
                            String nomeAluno = teclado.next();

                            bw.write(respostas + "\t" + nomeAluno);
                            bw.newLine();
                            bw.flush();
                        } else {
                            fw.close();
                            bw.close();
                            break;
                        }
                    }
                } catch (IOException e) {
                    System.out.println(e);
                }

            }  
            else if (opcao.equals("2")) {

                while(true){
                
                    System.out.println("\nDigite o nome da disciplina: ");
                    String nomeDisciplina = teclado.next();

                    File diretorio = new File("./");
                    File subdir1 = new File(diretorio, "RespostasDisciplinas");
                    subdir1.mkdir();
                    File arquivo = new File(subdir1, nomeDisciplina + ".txt");

                    boolean verify;

                    while(verify = !arquivo.exists()){

                        System.out.println("\nArquivo de disciplina não encontrado.");
                        System.out.println("\nDigite a opcao desejada:\n");
                        System.out.println("1 - Tentar novamente");
                        System.out.println("2 - Voltar ao menu");
                        do {
                            System.out.print("Opc: ");
                            opcao = teclado.next();
                        } while (!(opcao.equals("1") || opcao.equals("2")));

                        if(opcao.equals("1")){

                            System.out.println("Digite o nome da disciplina: ");
                            nomeDisciplina = teclado.next();

                            arquivo = new File(subdir1, nomeDisciplina + ".txt");

                        }
                        else{

                            System.out.println("\n");

                            break;

                        }

                    }

                    if(verify)
                        continue;                

                    File subdir2 = new File(diretorio, "ResultadosOrdemAlfabetica"); 
                    subdir2.mkdir();
                    File subdir3 = new File(diretorio, "ResultadosOrdemNotas");
                    subdir3.mkdir();
                    File subdir4 = new File(diretorio, "GabaritosDisciplinas");
                    subdir4.mkdir();
                    File arquiv2 = new File(subdir2, nomeDisciplina + "OrdemAlfabetica.txt");
                    File arquiv3 = new File(subdir3, nomeDisciplina + "OrdemNotas.txt");
                    File arquiv4 = new File(subdir4, "gabarito" + nomeDisciplina + ".txt");

                    try{

                        FileWriter fw = new FileWriter(arquiv4);

                        System.out.println("Digite o gabarito da disciplia: ");
                        String gabarito = teclado.next();

                        fw.write(gabarito);

                        fw.close();

                        System.out.println("\nLocalização do arquivo contendo gabarito:\n" + arquiv4.getAbsolutePath() + "\n");

                        FileReader fr = new FileReader(arquivo);

                        BufferedReader br = new BufferedReader(fr);

                        ArrayList<String> nomes = new ArrayList<String>(), respostas = new ArrayList<String>();

                        String linha = br.readLine();

                        while(linha != null){

                            String aluno[] = linha.split("\t");

                            respostas.add(aluno[0]);

                            nomes.add(aluno[1]);

                            linha = br.readLine();

                        }

                        br.close();
                        fr.close();

                        disciplina = new Disciplina(respostas, nomes);

                        disciplina.definirNotas(gabarito);

                        ArrayList<String> listaAlfabetica = disciplina.ordenarPorNome(), listaNotas = disciplina.ordenarPorNota();


                        for(int ind = 0; ind < listaAlfabetica.size(); ind++){

                            FileWriter fw2 = new FileWriter(arquiv2, (ind >= 1));
                            FileWriter fw3 = new FileWriter(arquiv3, (ind >= 1));

                            fw2.write(listaAlfabetica.get(ind) + "\n");
                            fw3.write(listaNotas.get(ind) + "\n");

                            fw2.close();
                            fw3.close();

                        }

                        disciplina.imprimirMedia();

                    }catch(IOException e){

                        System.out.println(e.getMessage());

                    }

                    System.out.println("Digite a opcao desejada:\n");
                    System.out.println("1 - Adicionar gabarito de outra disciplina");
                    System.out.println("2 - Voltar ao menu");
                    opcao = teclado.next();

                    if(opcao.equals("2"))
                        break;

                }

            }
            else if(opcao.equals("3"))
                break;

        }

        teclado.close();

    }
}