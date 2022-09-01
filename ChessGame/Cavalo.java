import java.util.*;

public class Cavalo extends Peca {

	public Cavalo(int linha, int coluna, String cor) {
		super(linha, coluna, pegaEnderecoImagem(cor), cor);
		super.tipo = "cavalo";
		super.listaMovimentos = new ArrayList<Coordenadas>();
	}

	private static String pegaEnderecoImagem(String cor) {
		if (cor.equals("branca")) {
			return "imagens/pecas/brancas/cavalo.png";
		} else {
			return "imagens/pecas/pretas/cavalo.png";
		}
	}
	@Override
	void adicionaListaMovimentos(Casa[][] casas, int i, int j){
		if(movimentoPeca(casas, i, j)){
			listaMovimentos.add(new Coordenadas(i, j));
		}
	}
	@Override
	void pintarMovimentoPeca(Casa[][] casas) {
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
		if (dentroDoMovimento(i, j)) {
			if (super.ehUmaPeca(casas, i, j)) {
				if (super.ehUmaPecaInimiga(casas, i, j)) {
					return true;
				} else
					return false;
			} else {
				return true;
			}
		} else
			return false;
	}

	@Override
	boolean dentroDoMovimento(int i, int j) {
		if (((Math.abs(super.posicaoAtual.linha - i) == 1) && (Math.abs(super.posicaoAtual.coluna - j) == 2))
				|| ((Math.abs(super.posicaoAtual.linha - i) == 2) && (Math.abs(super.posicaoAtual.coluna - j) == 1))) {
			if (!super.mesmaLinha(i, super.posicaoAtual.linha) && !super.mesmaColuna(j, super.posicaoAtual.coluna)) {
				return true;
			} else
				return false;
		} else
			return false;
	}
}
