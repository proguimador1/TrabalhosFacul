public class Jogador {
    
    protected String cor;
    protected int posicao;
    protected boolean pode_jogar;
    protected int qtd_jogadas;

    Jogador(String cor, int posicao){

        this.cor = cor;

        this.posicao = posicao;

        pode_jogar = true;

    }

    public String getCor(){

        return cor;

    }

    public int getPosicao(){

        return posicao;

    }

    public boolean getPodeJogar(){

        return pode_jogar;

    }

    public void setPodeJogar(){

        pode_jogar = !pode_jogar;

    }

    public void setPosicao(int posicao){

        this.posicao = posicao;

    }

    public boolean moverSe(int numcasas){

        posicao += numcasas;
        
        return true;

    }

    public void setQtdJogadas(){

        qtd_jogadas++;

    }
    
}