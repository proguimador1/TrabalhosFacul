import java.io.IOException;

public class LimpadorConsole {
    public void limparTela() throws IOException, InterruptedException {
        if (System.getProperty("os.name").contains("Windows")){
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
        else{
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        }

    }
}
