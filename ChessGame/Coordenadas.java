class Coordenadas {
	int linha, coluna;

	public Coordenadas(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}

	boolean verificaIgualdade(int linha, int coluna) {
		if ((linha == this.linha) && (coluna == this.coluna)) {
			return true;
		} else {
			return false;
		}
	}

	boolean verificaIgualdade(Coordenadas coordenada) {
		if ((coordenada.linha == this.linha) && (coordenada.coluna == this.coluna)) {
			return true;
		} else {
			return false;
		}
	}

}
