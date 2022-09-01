import java.util.*;

public class Peao extends Peca {

	public Peao(int linha, int coluna, String cor) {
		super(linha, coluna, pegaEnderecoImagem(cor), cor);
		super.tipo = "peao";
		super.listaMovimentos = new ArrayList<Coordenadas>();
	}

	private static String pegaEnderecoImagem(String cor) {
		if (cor.equals("branca")) {
			return "imagens/pecas/brancas/peao.png";
		} else {
			return "imagens/pecas/pretas/peao.png";
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
		super.maiorLinha(casas);
		super.menorLinha(casas);

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
		if ((super.jogada == 0)) {
			if (super.cor.equals("branca")) {
				if (dentroDoMovimento(i, j)) {
					if (!super.ehUmaPeca(casas, i, j)) {
						if (i >= super.maiorLinha) {
							return true;
						} else
							return false;
					} else
						return false;
				} else if (movimentoComidaPeao(casas, i, j)) {
					if (super.ehUmaPecaInimiga(casas, i, j)) {
						if (super.posicaoAtual.linha > i) {
							return true;
						} else
							return false;
					} else
						return false;
				} else
					return false;
			} else {
				if (dentroDoMovimento(i, j)) {
					if (!super.ehUmaPeca(casas, i, j)) {
						if (i <= super.menorLinha) {
							return true;
						} else
							return false;
					} else
						return false;
				} else if (movimentoComidaPeao(casas, i, j)) {
					if (super.ehUmaPecaInimiga(casas, i, j)) {
						if (super.posicaoAtual.linha < i) {
							return true;
						} else
							return false;
					} else
						return false;
				} else
					return false;
			}
		} else {
			if (super.cor.equals("branca")) {
				if (dentroDoMovimento(i, j)) {
					if (!super.ehUmaPeca(casas, i, j)) {
						if (i <= super.menorLinha) {
							return true;
						} else
							return false;
					} else
						return false;
				} else if (movimentoComidaPeao(casas, i, j)) {
					if (super.ehUmaPecaInimiga(casas, i, j)) {
						if (super.posicaoAtual.linha > i) {
							return true;
						} else
							return false;
					} else
						return false;
				} else if (super.enPassant(casas, i, j)){
					return true;
				} else
						return false;
			} else {
				if (dentroDoMovimento(i, j)) {
					if (!super.ehUmaPeca(casas, i, j)) {
						if (i >= super.maiorLinha) {
							return true;
						} else
							return false;
					} else
						return false;
				} else if (movimentoComidaPeao(casas, i, j)) {
					if (super.ehUmaPecaInimiga(casas, i, j)) {
						if (super.posicaoAtual.linha < i) {
							return true;
						} else
							return false;
					} else
						return false;
				} else if (super.enPassant(casas, i, j)){
					return true;
				} else
					return false;
			}
		}
	}
	
	@Override
	boolean dentroDoMovimento(int i, int j){
		if (super.jogada == 0){
			if (super.cor.equals("branca")) {
				if (((super.posicaoAtual.linha - i == 1) || (super.posicaoAtual.linha - i == 2))
						&& (super.posicaoAtual.coluna == j)) {
					return true;
				} else {
					return false;
				}
			} else {
				if (((i - super.posicaoAtual.linha == 1) || (i - super.posicaoAtual.linha == 2))
						&& (super.posicaoAtual.coluna == j)) {
					return true;
				} else {
					return false;
				}
			}
		} else {
			if (super.cor.equals("branca")) {
				if ((super.posicaoAtual.linha - i == 1) && (super.posicaoAtual.coluna == j)) {
					return true;
				} else {
					return false;
				}
				
			} else {
				if ((i - super.posicaoAtual.linha == 1) && (super.posicaoAtual.coluna == j)) {
					return true;
				} else {
					return false;
				}
			}
		}
	}

	boolean movimentoComidaPeao(Casa[][] casas, int i, int j) {
		if ((Math.abs(posicaoAtual.linha - i) == 1)
				&& ((Math.abs(super.posicaoAtual.coluna - j) == 1) || (Math.abs(super.posicaoAtual.coluna - j) == 0))
				|| ((Math.abs(super.posicaoAtual.linha - i) == 0) && (Math.abs(super.posicaoAtual.coluna - j) == 1))) {
			if ((casas[i][j].peça != null)
					&& (Math.abs(super.posicaoAtual.linha - i)) == (Math.abs(super.posicaoAtual.coluna - j))) {
				return true;
			} else
				return false;
		} else
			return false;
	}
	
}