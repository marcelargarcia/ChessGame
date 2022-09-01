import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Peca {
	int jogada = 0;
	int quantidade;

	String cor = null;
	String tipo = null;
	String endereçoImagem;

	Coordenadas posicaoAnterior;
	Coordenadas posicaoAtual;

	List<Coordenadas> listaMovimentos;

	Image imagem;

	int maiorLinha = 0;
	int menorLinha = 7;
	int menorColuna = 7;
	int maiorColuna = 0;
	int linha;
	int coluna;
	
	boolean ehXeque = false;
	boolean enPassant;
	boolean roqueCurto;
	boolean roqueLongo;

	Coordenadas menorDiagonalCimaDireita = new Coordenadas(0, 7);
	Coordenadas menorDiagonalCimaEsquerda = new Coordenadas(0, 0);
	Coordenadas menorDiagonalBaixoDireita = new Coordenadas(7, 7);
	Coordenadas menorDiagonalBaixoEsquerda = new Coordenadas(7, 0);

	public Peca(int linha, int coluna, String endereçoImagem, String cor) {
		this.endereçoImagem = endereçoImagem;
		posicaoAtual = new Coordenadas(linha, coluna);
		this.cor = cor;
		this.linha = linha;
		this.coluna = coluna;
		imagem = new ImageIcon(endereçoImagem, "").getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
	}

	public Peca() {

	}

	void sentidoCoordenada(Coordenadas coordenada) {

	}

	void pintarMovimentoPeca(Casa[][] casas) {

	}

	boolean movimentoPeca(Casa[][] casas, int i, int j) {
		return false;
	}

	boolean dentroDoMovimento(int i, int j) {
		return false;
	}
	public ImageIcon getImagem() {
		return new ImageIcon(imagem);
	}

	void colore(Casa[][] casas, int i, int j) {
		casas[i][j].botão.setBackground(new Color(153, 187, 255));
	}
	
	void adicionaListaMovimentos(Casa[][]casas, int i, int j){

	}
	boolean mover(Casa[][] casas, Coordenadas coordenada) {
		for (Coordenadas i : listaMovimentos) {
			if (coordenada.verificaIgualdade(i)) {
				if (enPassant(casas, coordenada.linha, coordenada.coluna)){
					enPassant = true;
				} else enPassant = false;
				
				if (roqueLongo(casas, coordenada.linha, coordenada.coluna)){
					roqueLongo = true;
				} else roqueLongo = false;
				
				if (roqueCurto(casas, coordenada.linha, coordenada.coluna)){
					roqueCurto = true;
				} else roqueCurto = false;
				
				if (enPassant){
					JOptionPane.showMessageDialog(null, "En Passant!");
					if (cor.equals("preta")){
						casas[4][coordenada.coluna].botão.setIcon(null);
						casas[4][coordenada.coluna].peça = null;
					} else {
						casas[3][coordenada.coluna].botão.setIcon(null);
						casas[3][coordenada.coluna].peça = null;
					}
				}
				if (roqueLongo){
					JOptionPane.showMessageDialog(null, "Roque!");
					if (cor.equals("preta")){
						casas[0][0].botão.setIcon(null);	
						casas[0][3].botão.setIcon(casas[0][0].peça.getImagem());
						casas[0][0].peça.jogada++;
						casas[0][3].peça = casas[0][0].peça;
						casas[0][0].peça = null;
						casas[0][3].peça.posicaoAnterior = new Coordenadas(0,0);
						casas[0][3].peça.posicaoAtual = new Coordenadas(0,3);
						
					} else {
						casas[7][0].botão.setIcon(null);	
						casas[7][3].botão.setIcon(casas[7][0].peça.getImagem());
						casas[7][0].peça.jogada++;
						casas[7][3].peça = casas[7][0].peça;
						casas[7][0].peça = null;
						
						casas[7][3].peça.posicaoAnterior = new Coordenadas(7,0);
						casas[7][3].peça.posicaoAtual = new Coordenadas(7,3);

					}
				}
				
				if (roqueCurto){
					JOptionPane.showMessageDialog(null, "Roque!");
					if (cor.equals("preta")){
						casas[0][7].botão.setIcon(null);	
						casas[0][5].botão.setIcon(casas[0][7].peça.getImagem());
						casas[0][7].peça.jogada++;
						casas[0][5].peça = casas[0][7].peça;
						casas[0][7].peça = null;
						casas[0][5].peça.posicaoAnterior = new Coordenadas(0,7);
						casas[0][5].peça.posicaoAtual = new Coordenadas(0,5);
						
					} else {
						casas[7][7].botão.setIcon(null);	
						casas[7][5].botão.setIcon(casas[7][7].peça.getImagem());
						casas[7][7].peça.jogada++;
						casas[7][5].peça = casas[7][7].peça;
						casas[7][7].peça = null;
						casas[7][5].peça.posicaoAnterior = new Coordenadas(7,7);
						casas[7][5].peça.posicaoAtual = new Coordenadas(7,5);
						
					}
				}
										
				casas[posicaoAtual.linha][posicaoAtual.coluna].botão.setIcon(null);
				casas[coordenada.linha][coordenada.coluna].botão.setIcon(getImagem());
				
				casas[posicaoAtual.linha][posicaoAtual.coluna].peça.jogada++;
				
				casas[coordenada.linha][coordenada.coluna].peça = casas[posicaoAtual.linha][posicaoAtual.coluna].peça;
				casas[posicaoAtual.linha][posicaoAtual.coluna].peça.linha = coordenada.linha;
				casas[posicaoAtual.linha][posicaoAtual.coluna].peça.coluna = coordenada.coluna;
				
				casas[posicaoAtual.linha][posicaoAtual.coluna].peça = null;
				
				posicaoAnterior = new Coordenadas(posicaoAtual.linha, posicaoAtual.coluna);
				posicaoAtual = new Coordenadas(coordenada.linha, coordenada.coluna);

				for (int m = 0; m < casas.length; m++){
					for (int n = 0; n < casas[m].length; n++){
						int contador = 0;
						if (casas[m][n].peça != null){
							confereXeque(casas, m, n);
							if (ehXeque){
								contador++;
							}
							if (contador > 0){
								this.ehXeque = true;
							}
						}
					}
				}
				System.out.println(ehXeque);
				
				
				return true;
			} 
		}
		return false;
	}

	boolean enPassant(Casa[][]casas, int i, int j){
		if (cor.equals("preta") && posicaoAtual.linha == 4){
			if ((i == 5) && (Math.abs(posicaoAtual.coluna - j) == 1)){
				if(casas[4][j].peça != null){
					if (casas[4][j].peça.tipo.equals("peao")){
						if (casas[4][j].peça.posicaoAnterior.linha == 6){
							return true;
						} else return false;
					} else return false;
				} else return false;
			} else return false;
		} else if (cor.equals("branca") && posicaoAtual.linha == 3) {
			if ((i == 2) && (Math.abs(posicaoAtual.coluna - j) == 1)){
				if (casas[3][j].peça != null){
					if (casas[3][j].peça.tipo.equals("peao")){
						if (casas[3][j].peça.posicaoAnterior.linha == 1){
							return true;
						} else return false;
					} else return false;
				} else return false;
			} else return false;
		} else return false;
	}
	
	boolean roqueLongo(Casa[][]casas, int i, int j){
		if (tipo.equals("rei") && (jogada == 0)){
			if (cor.equals("preta")){
				if ((i == 0) && (j == 2)){
					if (casas[0][0].peça != null){
						if (casas[0][0].peça.tipo.equals("torre") && (casas[0][0].peça.cor.equals("preta")) 
								&& (casas[0][0].peça.jogada == 0)){
							if ((casas[0][1].peça == null) && (casas[0][2].peça == null) && (casas[0][3].peça == null)){
								return true;
							} else return false;
						} else return false;
					} else return false;
				} else return false;
			} else {
				if ((i == 7) && (j == 2)){
					if (casas[7][0].peça != null){
						if (casas[7][0].peça.tipo.equals("torre") && (casas[7][0].peça.cor.equals("branca"))
								&& (casas[7][0].peça.jogada == 0)){
							if ((casas[7][1].peça == null) && (casas[7][2].peça == null) && (casas[7][3].peça == null)){
								return true;
							} else return false;
						} else return false;
					} else return false;
				} else return false;
			}
		} else return false;
	}
	
	boolean roqueCurto (Casa[][]casas, int i, int j){
		if (tipo.equals("rei") && (jogada == 0)){
			if (cor.equals("preta")){
				if ((i == 0) && (j == 6)){
					if (casas[0][7].peça != null){
						if ((casas[0][7].peça.tipo.equals("torre")) && (casas[0][7].peça.cor.equals("preta"))
								&& (casas[0][7].peça.jogada == 0)){
							if ((casas[0][5].peça == null) && (casas[0][6].peça == null)){
								return true;
							} else return false;
						} else return false;
					} else return false;
				} else return false;
			} else {
				if ((i == 7) && (j == 6)){
					if (casas[7][7].peça != null){
						if (casas[7][7].peça.tipo.equals("torre") && (casas[7][7].peça.cor.equals("branca")) 
								&& (casas[7][7].peça.jogada == 0)){
							if ((casas[7][5].peça == null) && (casas[7][6].peça == null)){
								return true;
							} else return false;
						} else return false;
					} else return false;
				} else return false;
			}
		} else return false;
	}
	
	void confereXeque(Casa[][] casas, int linha, int coluna) {
		int contador = 0;
		int contador1 = 0;
		int contador2 = 0;
		for (int i = 0; i < casas.length; i++) {
			for (int j = 0; j < casas[i].length; j++) {
				if (casas[linha][coluna].peça.movimentoPeca(casas, i, j)) {
					if (casas[i][j].peça != null) {
						contador1 = i + j;
						if (casas[i][j].peça.tipo.equals("rei")){
							ehXeque = true;
							for (int k = 0; k < casas.length; k++){
								for (int l = 0; l < casas[k].length; l++){
									if (casas[i][j].peça.movimentoPeca(casas, k, l)){
										contador++;
										if (contador == 1){
											if (casas[i][j].peça.cor.equals("branca")) {
												JOptionPane.showMessageDialog(null, "Rei branco em xeque");
											} else {
												JOptionPane.showMessageDialog(null, "Rei preto em xeque");
											}	
										}
									}
									else if (!casas[i][j].peça.movimentoPeca(casas, k, l)){
										contador2++;
										if (contador2 == 64){
											if (casas[i][j].peça.cor.equals("branca")) {
												JOptionPane.showMessageDialog(null, "XEQUE MATE! O jogo acabou! O time preto venceu! Parabéns!");
												System.exit(0);
											} else {
											    JOptionPane.showMessageDialog(null, "XEQUE MATE! O jogo acabou! O time branco venceu! Parabéns!");
												System.exit(0);
											}
										}
									}								
								}
							}
						} else {
							contador1--;
							if (contador1 == 0){
								ehXeque = false;
							}
						}
					}
				} 
			}
		} 
	}


	boolean mesmaLinha(int linha1, int linha2) {
		if (linha1 == linha2) {
			return true;
		} else {
			return false;
		}
	}

	boolean aDireita(int coluna1, int coluna2) {
		if (coluna1 > coluna2) {
			return true;
		} else {
			return false;
		}
	}

	boolean aEsquerda(int coluna1, int coluna2) {
		if (coluna1 < coluna2) {
			return true;
		} else {
			return false;
		}
	}

	boolean mesmaColuna(int coluna1, int coluna2) {
		if (coluna1 == coluna2) {
			return true;
		} else {
			return false;
		}
	}

	boolean aCima(int linha1, int linha2) {
		if (linha1 < linha2) {
			return true;
		} else {
			return false;
		}
	}

	boolean aBaixo(int linha1, int linha2) {
		if (linha1 > linha2) {
			return true;
		} else {
			return false;
		}
	}

	boolean ehUmaPeca(Casa[][] casas, int i, int j) {
		if (casas[i][j].peça != null) {
			return true;
		} else {
			return false;
		}
	}

	boolean ehUmaPecaInimiga(Casa[][] casas, int i, int j) {
		if (casas[i][j].peça.cor.equals(cor)) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * Esses métodos abaixo irão limitar até onde o movimento deve ir; são as
	 * BARREIRAS do movimento em todos os quatro sentidos: para cima, para
	 * baixo, para a direita e para a esquerda. A ideia do nome seria: *para
	 * cima* maior linha em que posso ir *para baixo* menor linha em que posso
	 * ir *para a direita* menor coluna em que posso ir *para a esquerda* maior
	 * coluna em que posso ir
	 */

	void maiorLinha(Casa[][] casas) {
		this.maiorLinha = 0;
		for (int i = 0; i < casas.length; i++) {
			for (int j = 0; j < casas[i].length; j++) {
				if (dentroDoMovimento(i, j)) {
					if (mesmaColuna(j, coluna)) {
						if (aCima(i, linha)) {
							if (ehUmaPeca(casas, i, j)) {
								if (i > maiorLinha) {
									this.maiorLinha = i;
								}
							}
						}
					}
				}
			}
		}
	}

	void maiorColuna(Casa[][] casas) {
		this.maiorColuna = 0;
		for (int i = 0; i < casas.length; i++) {
			for (int j = 0; j < casas[i].length; j++) {
				if (dentroDoMovimento(i, j)) {
					if (mesmaLinha(i, linha)) {
						if (aEsquerda(j, coluna)) {
							if (ehUmaPeca(casas, i, j)) {
								if (j > maiorColuna) {
									this.maiorColuna = j;
								}
							}
						}
					}
				}
			}
		}
	}

	void menorLinha(Casa[][] casas) {
		this.menorLinha = 7;
		for (int i = 0; i < casas.length; i++) {
			for (int j = 0; j < casas[i].length; j++) {
				if (dentroDoMovimento(i, j)) {
					if (mesmaColuna(j, coluna)) {
						if (aBaixo(i, linha)) {
							if (ehUmaPeca(casas, i, j)) {
								if (i < menorLinha) {
									this.menorLinha = i;
								}
							}
						}
					}
				}
			}
		}
	}

	void menorColuna(Casa[][] casas) {
		this.menorColuna = 7;
		for (int i = 0; i < casas.length; i++) {
			for (int j = 0; j < casas[i].length; j++) {
				if (dentroDoMovimento(i, j)) {
					if (mesmaLinha(i, linha)) {
						if (aDireita(j, coluna)) {
							if (ehUmaPeca(casas, i, j)) {
								if (j < menorColuna) {
									this.menorColuna = j;
								}
							}
						}
					}
				}
			}
		}
	}

	void menorDiagonalCimaDireita(Casa[][] casas) {
		menorDiagonalCimaDireita = new Coordenadas(0, 7);
		for (int i = 0; i < casas.length; i++) {
			for (int j = 0; j < casas[i].length; j++) {
				if (dentroDoMovimento(i, j)) {
					if (aDireita(j, coluna) && aCima(i, linha)) {
						if (ehUmaPeca(casas, i, j)) {
							if ((i > menorDiagonalCimaDireita.linha) || (j < menorDiagonalCimaDireita.coluna)) {
								menorDiagonalCimaDireita = new Coordenadas(i, j);
							}
						}
					}
				}
			}
		}
	}

	void menorDiagonalCimaEsquerda(Casa[][] casas) {
		menorDiagonalCimaEsquerda = new Coordenadas(0, 0);
		for (int i = 0; i < casas.length; i++) {
			for (int j = 0; j < casas[i].length; j++) {
				if (dentroDoMovimento(i, j)) {
					if (aEsquerda(j, coluna) && aCima(i, linha)) {
						if (ehUmaPeca(casas, i, j)) {
							if ((i > menorDiagonalCimaEsquerda.linha) || (j > menorDiagonalCimaEsquerda.coluna)) {
								menorDiagonalCimaEsquerda = new Coordenadas(i, j);
							}
						}
					}
				}
			}
		}
	}

	void menorDiagonalBaixoDireita(Casa[][] casas) {
		menorDiagonalBaixoDireita = new Coordenadas(7, 7);
		for (int i = 0; i < casas.length; i++) {
			for (int j = 0; j < casas[i].length; j++) {
				if (dentroDoMovimento(i, j)) {
					if (aDireita(j, coluna) && aBaixo(i, linha)) {
						if (ehUmaPeca(casas, i, j)) {
							if ((i < menorDiagonalBaixoDireita.linha) || (j < menorDiagonalBaixoDireita.coluna)) {
								menorDiagonalBaixoDireita = new Coordenadas(i, j);
							}
						}
					}
				}
			}
		}
	}

	void menorDiagonalBaixoEsquerda(Casa[][] casas) {
		menorDiagonalBaixoEsquerda = new Coordenadas(7, 0);
		for (int i = 0; i < casas.length; i++) {
			for (int j = 0; j < casas[i].length; j++) {
				if (dentroDoMovimento(i, j)) {
					if (aEsquerda(j, coluna) && aBaixo(i, linha)) {
						if (ehUmaPeca(casas, i, j)) {
							if ((i < menorDiagonalBaixoEsquerda.linha) || (j > menorDiagonalBaixoEsquerda.coluna)) {
								menorDiagonalBaixoEsquerda = new Coordenadas(i, j);
							}
						}
					}
				}
			}
		}
	}
}