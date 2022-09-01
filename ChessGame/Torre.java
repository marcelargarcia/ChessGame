import java.util.*;

public class Torre extends Peca {

	public Torre(int linha, int coluna, String cor) {
		super(linha, coluna, pegaEnderecoImagem(cor), cor);
		super.tipo = "torre";
		super.listaMovimentos = new ArrayList<Coordenadas>();
	}
	
	private static String pegaEnderecoImagem(String cor) {
		if (cor.equals("branca")) {
			return "imagens/pecas/brancas/torre.png";
		} else {
			return "imagens/pecas/pretas/torre.png";
		}
	}
	@Override
	void adicionaListaMovimentos(Casa[][]casas, int i, int j){
		if (movimentoPeca(casas, i, j)){
			listaMovimentos.add(new Coordenadas(i, j));
			System.out.println("maior linha " + maiorLinha );
			System.out.println("menor linha " + menorLinha );
			System.out.println("maior coluna " + maiorColuna );
			System.out.println("menor coluna " + menorColuna );

			System.out.println(i + "," + j);
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
		super.maiorLinha(casas);
		super.maiorColuna(casas);
		super.menorLinha(casas);
		super.menorColuna(casas);
		if (dentroDoMovimento(i, j)) {
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
			} else if (super.aBaixo(i, super.linha)) {
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
	}

	@Override
	boolean dentroDoMovimento(int i, int j) {
		if ((super.mesmaLinha(i, super.posicaoAtual.linha)) || (super.mesmaColuna(j, super.posicaoAtual.coluna))) {
			return true;
		} else {
			return false;
		}
	}
}
