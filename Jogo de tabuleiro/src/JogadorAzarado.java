public class JogadorAzarado extends Jogador{

    JogadorAzarado(String cor, int posicao){

        super(cor,posicao);

    }

    public boolean moverSe(int numcasas){

        if(numcasas > 6)
            return false;

        posicao += numcasas;

        return true;

    }
    
}