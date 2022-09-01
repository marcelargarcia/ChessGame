import java.util.*;

public class Rainha extends Peca {

	public Rainha(int linha, int coluna, String cor) {
		super(linha, coluna, pegaEnderecoImagem(cor), cor);
		super.tipo = "rainha";
		super.listaMovimentos = new ArrayList<Coordenadas>();
	}

	private static String pegaEnderecoImagem(String cor) {
		if (cor.equals("branca")) {
			return "imagens/pecas/brancas/rainha.png";
		} else {
			return "imagens/pecas/pretas/rainha.png";
		}
	}
	@Override
	void adicionaListaMovimentos(Casa[][]casas, int i, int j){
		if (movimentoPeca(casas, i, j)){
			listaMovimentos.add(new Coordenadas(i, j));
		}
	}
	@Override
	void pintarMovimentoPeca(Casa[][] casas) {
		// System.out.println("menor" + menorLinha);

		for (int i = 0; i < casas.length; i++) {
			for (int j = 0; j < casas[i].length; j++) {
				if (movimentoPeca(casas, i, j)) {
					super.colore(casas, i, j);
				}
				adicionaListaMovimentos(casas, i, j);
			}
		}
	}

	@Override
	boolean movimentoPeca(Casa[][] casas, int i, int j) {
		super.maiorLinha(casas);
		super.maiorColuna(casas);
		super.menorLinha(casas);
		super.menorColuna(casas);
		super.menorDiagonalCimaDireita(casas);
		super.menorDiagonalCimaEsquerda(casas);
		super.menorDiagonalBaixoDireita(casas);
		super.menorDiagonalBaixoEsquerda(casas);
		if (dentroDoMovimento(i, j)) {
			if (!super.mesmaLinha(i, super.posicaoAtual.linha) && (!super.mesmaColuna(j, super.posicaoAtual.coluna))) {
				if (super.aDireita(j, super.coluna) && super.aCima(i, super.linha)) {
					if (super.ehUmaPeca(casas, i, j)) {
						if (super.ehUmaPecaInimiga(casas, i, j)) {
							if ((i == super.menorDiagonalCimaDireita.linha)
									&& (j == super.menorDiagonalCimaDireita.coluna)) {
								return true;
							} else
								return false;
						} else
							return false;
					} else {
						if ((i >= super.menorDiagonalCimaDireita.linha)
								&& (j <= super.menorDiagonalCimaDireita.coluna)) {
							return true;
						} else
							return false;
					}
				} else if (super.aEsquerda(j, super.coluna) && super.aCima(i,super.linha)) {
					if (super.ehUmaPeca(casas, i, j)) {
						if (super.ehUmaPecaInimiga(casas, i, j)) {
							if ((i == super.menorDiagonalCimaEsquerda.linha)
									&& (j == super.menorDiagonalCimaEsquerda.coluna)) {
								return true;
							} else
								return false;
						} else
							return false;
					} else {
						if ((i >= super.menorDiagonalCimaEsquerda.linha)
								&& (j >= super.menorDiagonalCimaEsquerda.coluna)) {
							return true;
						} else
							return false;
					}
				} else if (super.aDireita(j, super.coluna) && super.aBaixo(i, super.linha)) {
					if (super.ehUmaPeca(casas, i, j)) {
						if (super.ehUmaPecaInimiga(casas, i, j)) {
							if ((i == super.menorDiagonalBaixoDireita.linha)
									&& (j == super.menorDiagonalBaixoDireita.coluna)) {
								return true;
							} else
								return false;
						} else
							return false;
					} else {
						if ((i <= super.menorDiagonalBaixoDireita.linha)
								&& (j <= super.menorDiagonalBaixoDireita.coluna)) {
							return true;
						} else
							return false;
					}
				} else if (super.aEsquerda(j, super.coluna) && super.aBaixo(i, super.linha)) {
					if (super.ehUmaPeca(casas, i, j)) {
						if (super.ehUmaPecaInimiga(casas, i, j)) {
							if ((i == super.menorDiagonalBaixoEsquerda.linha)
									&& (j == super.menorDiagonalBaixoEsquerda.coluna)) {
								return true;
							} else
								return false;
						} else
							return false;
					} else {
						if ((i <= super.menorDiagonalBaixoEsquerda.linha)
								&& (j >= super.menorDiagonalBaixoEsquerda.coluna)) {
							return true;
						} else
							return false;
					}
				} else
					return false;
			} else if (mesmaLinha(i, super.posicaoAtual.linha)) {
				if (super.aDireita(j, super.coluna)) {
					if (super.ehUmaPeca(casas, i, j)) {
						if (super.ehUmaPecaInimiga(casas, i, j)) {
							if (j == super.menorColuna) {
								return true;
							} else
								return false;
						} else
							return false;
					} else {
						if (j <= super.menorColuna) {
							return true;
						} else
							return false;
					}
				} else if (super.aEsquerda(j, super.coluna)) {
					if (super.ehUmaPeca(casas, i, j)) {
						if (super.ehUmaPecaInimiga(casas, i, j)) {
							if (j == super.maiorColuna) {
								return true;
							} else
								return false;
						} else
							return false;
					} else {
						if (j >= super.maiorColuna) {
							return true;
						} else
							return false;
					}
				} else
					return false;
			} else if (mesmaColuna(j, super.posicaoAtual.coluna)) {
				if (super.aBaixo(i, super.linha)) {
					if (super.ehUmaPeca(casas, i, j)) {
						if (super.ehUmaPecaInimiga(casas, i, j)) {
							if (i == super.menorLinha) {
								return true;
							} else
								return false;
						} else
							return false;
					} else {
						if (i <= super.menorLinha) {
							return true;
						} else
							return false;
					}
				} else if (super.aCima(i, super.linha)) {
					if (super.ehUmaPeca(casas, i, j)) {
						if (super.ehUmaPecaInimiga(casas, i, j)) {
							if (i == super.maiorLinha) {
								return true;
							} else
								return false;
						} else
							return false;
					} else {
						if (i >= super.maiorLinha) {
							return true;
						} else
							return false;
					}
				} else
					return false;
			} else
				return false;
		} else
			return false;
	}

	@Override
	boolean dentroDoMovimento(int i, int j) {
		if ((Math.abs(super.posicaoAtual.linha - i)) == (Math.abs(super.posicaoAtual.coluna - j))
				&& (super.posicaoAtual.linha != i) && (super.posicaoAtual.coluna != j)) {
			return true;
		} else if ((super.mesmaLinha(i, super.posicaoAtual.linha))
				|| (super.mesmaColuna(j, super.posicaoAtual.coluna))) {
			return true;
		} else
			return false;
	}
}
