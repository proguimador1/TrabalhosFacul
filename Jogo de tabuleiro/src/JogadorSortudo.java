public class JogadorSortudo extends Jogador{

    JogadorSortudo(String cor, int posicao){

        super(cor,posicao);

    }

    public boolean moverSe(int numcasas){

        if(numcasas < 7)
            return false;

        posicao += numcasas;

        return true;

    }
    
}