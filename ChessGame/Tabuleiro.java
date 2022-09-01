import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

class Tabuleiro {
	Casa casa = new Casa();
	Casa[][] casas = new Casa[8][8];
	Peca pecaClicada;
	boolean clicado = false;
	
	void criaAsCasas() {
		for (int linha = 0; linha < casas.length; linha++) {
			for (int coluna = 0; coluna < casas[linha].length; coluna++) {
				casas[linha][coluna] = new Casa();
				casas[linha][coluna].botão = new JButton();
				obtemBotaoCasa(linha, coluna).setPreferredSize(new Dimension(80, 80));
				pintaAsCasas(linha, coluna);
			}
		}
	}

	void criaAsPecas() {
		// pretas
		casas[0][2].peça = new Bispo(0, 2, "preta");
		casas[0][5].peça = new Bispo(0, 5, "preta");
		casas[0][0].peça = new Torre(0, 0, "preta");
		casas[0][7].peça = new Torre(0, 7, "preta");
		casas[0][1].peça = new Cavalo(0, 1, "preta");
		casas[0][6].peça = new Cavalo(0, 6, "preta");
		casas[0][3].peça = new Rainha(0, 3, "preta");
		casas[0][4].peça = new Rei(0, 4, "preta");
		for (int j = 0; j < 8; j++) {
			casas[1][j].peça = new Peao(1, j, "preta");
		}
		// brancas
		casas[7][2].peça = new Bispo(7, 2, "branca");
		casas[7][5].peça = new Bispo(7, 5, "branca");
		casas[7][0].peça = new Torre(7, 0, "branca");
		casas[7][7].peça = new Torre(7, 7, "branca");
		casas[7][1].peça = new Cavalo(7, 1, "branca");
		casas[7][6].peça = new Cavalo(7, 6, "branca");
		casas[7][3].peça = new Rainha(7, 3, "branca");
		casas[7][4].peça = new Rei(7, 4, "branca");
		for (int j = 0; j < 8; j++) {
			casas[6][j].peça = new Peao(6, j, "branca");
		}
	}

	void pintaAsCasas(int linha, int coluna) {
		if ((linha % 2 == 0 && coluna % 2 == 1) || (linha % 2 == 1 && coluna % 2 == 0)) {
			obtemBotaoCasa(linha, coluna).setBackground(new Color(107, 106, 104));
		} else {
			obtemBotaoCasa(linha, coluna).setBackground(Color.PINK);
		}
	}

	JButton obtemBotaoCasa(int linha, int coluna) {
		return casas[linha][coluna].botão;
	}

	void trataCliqueSobreUmaCasa(int linha, int coluna, Jogo jogo) {
		for (int i = 0; i < casas.length; i++) {
			for (int j = 0; j < casas[i].length; j++) {
				if (casas[i][j].peça != null) {
					if ((i == linha) && (j == coluna)) {
						if (casas[i][j].peça.posicaoAtual.verificaIgualdade(i, j)) {
							if (jogo.jogadaCorreta(casas, linha, coluna)){
								casas[i][j].peça.listaMovimentos = new ArrayList<Coordenadas>();
								casas[i][j].peça.pintarMovimentoPeca(casas);
								obtemBotaoCasa(i, j).setBackground(new Color(255, 51, 153));
								clicado = false;
								System.out.println(casas[i][j].peça.tipo + " " + casas[i][j].peça.cor + " jogada nº " + 
													casas[i][j].peça.jogada);
							} 
						}
					} else 
						pintaAsCasas(i, j);
				} else 
					pintaAsCasas(i, j);
		}
	}
		
		if (clicado) {
			if (pecaClicada.mover(casas, new Coordenadas(linha, coluna))) {
				jogo.jogada++;
				clicado = !clicado;
			} else if (casas[linha][coluna].peça == null){
				JOptionPane.showMessageDialog(null, "clique inválido");
			} else if (!jogo.jogadaCorreta(casas, linha, coluna)){
				JOptionPane.showMessageDialog(null, "não é sua jogada");
			}
		} else {
			if (casas[linha][coluna].peça == null){
				JOptionPane.showMessageDialog(null, "clique inválido");
			} else if (casas[linha][coluna].peça.posicaoAtual.verificaIgualdade(linha, coluna)){
				pecaClicada = casas[linha][coluna].peça;
				clicado = !clicado;
				if (jogo.jogadaCorreta(casas, linha, coluna)){
					casas[linha][coluna].peça.pintarMovimentoPeca(casas);
				} else {
					JOptionPane.showMessageDialog(null, "não é sua jogada");
				}
			}

		}
	}
}