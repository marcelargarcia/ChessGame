import javax.swing.JOptionPane;

public class Jogo {
	int jogada = 0;
	
	boolean jogadaEhDaBranca(){
		if (jogada % 2 == 0){
			return true;
		} else {
			return false;
		}
	}
	boolean jogadaCorreta(Casa[][] casas, int linha, int coluna){
		if (jogadaEhDaBranca()){
			if (casas[linha][coluna].peça.cor.equals("branca")){
				return true;
			} else {
				return false;
			}
		} else {
			if (casas[linha][coluna].peça.cor.equals("preta")){
				return true;
			} else {
				return false;
			}
		}
	}
}
