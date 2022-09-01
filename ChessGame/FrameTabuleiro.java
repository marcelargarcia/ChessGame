import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.lang.String;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

class FrameTabuleiro extends JFrame implements ActionListener {

	static final long serialVersionUID = 1L;

	// imagens das pecas pretas
	Image peaoPreto = new ImageIcon("imagens/pecas/pretas/peao.png", "").getImage().getScaledInstance(60, 60,
			java.awt.Image.SCALE_SMOOTH);
	Image torrePreta = new ImageIcon("imagens/pecas/pretas/torre.png", "").getImage().getScaledInstance(55, 55,
			java.awt.Image.SCALE_SMOOTH);
	Image cavaloPreto = new ImageIcon("imagens/pecas/pretas/cavalo.png", "").getImage().getScaledInstance(60, 60,
			java.awt.Image.SCALE_SMOOTH);
	Image bispoPreto = new ImageIcon("imagens/pecas/pretas/bispo.png", "").getImage().getScaledInstance(60, 60,
			java.awt.Image.SCALE_SMOOTH);
	Image rainhaPreta = new ImageIcon("imagens/pecas/pretas/rainha.png", "").getImage().getScaledInstance(60, 60,
			java.awt.Image.SCALE_SMOOTH);
	Image reiPreto = new ImageIcon("imagens/pecas/pretas/rei.png", "").getImage().getScaledInstance(60, 60,
			java.awt.Image.SCALE_SMOOTH);

	// imagens das pecas brancas
	Image peaoBranco = new ImageIcon("imagens/pecas/brancas/peao.png", "").getImage().getScaledInstance(60, 60,
			java.awt.Image.SCALE_SMOOTH);
	Image torreBranca = new ImageIcon("imagens/pecas/brancas/torre.png", "").getImage().getScaledInstance(55, 55,
			java.awt.Image.SCALE_SMOOTH);
	Image cavaloBranco = new ImageIcon("imagens/pecas/brancas/cavalo.png", "").getImage().getScaledInstance(60, 60,
			java.awt.Image.SCALE_SMOOTH);
	Image bispoBranco = new ImageIcon("imagens/pecas/brancas/bispo.png", "").getImage().getScaledInstance(60, 60,
			java.awt.Image.SCALE_SMOOTH);
	Image rainhaBranca = new ImageIcon("imagens/pecas/brancas/rainha.png", "").getImage().getScaledInstance(60, 60,
			java.awt.Image.SCALE_SMOOTH);
	Image reiBranco = new ImageIcon("imagens/pecas/brancas/rei.png", "").getImage().getScaledInstance(60, 60,
			java.awt.Image.SCALE_SMOOTH);

	GridLayout gerenciadorDeLayout = new GridLayout(8, 8);

	static final String TEXTO_BARRA_SUPERIOR = "Xadrez - Marcela R. Garcia - EP3 - (IP-2016)";
	Tabuleiro tabuleiro = new Tabuleiro();

	FrameTabuleiro() {
		super.setTitle(TEXTO_BARRA_SUPERIOR);
		super.setResizable(false);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.montaTabuleiro();
		super.pack();
		super.setVisible(true);
	}
	Jogo jogo = new Jogo();

	@Override
	public void actionPerformed(ActionEvent e) {
		String[] coordenada = e.getActionCommand().split(",");
		int linha = Integer.parseInt(coordenada[0]);
		int coluna = Integer.parseInt(coordenada[1]);
		tabuleiro.trataCliqueSobreUmaCasa(linha, coluna, jogo);
	}

	void montaTabuleiro() {
		tabuleiro.criaAsCasas();
		tabuleiro.criaAsPecas();
		JPanel painelTabuleiro = new JPanel();
		painelTabuleiro.setLayout(gerenciadorDeLayout);
		adicionaAsCasas(painelTabuleiro);
		adicionaAsPecas(painelTabuleiro);
	}

	void adicionaAsCasas(JPanel painelTabuleiro) {
		for (int linha = 0; linha < 8; linha++) {
			for (int coluna = 0; coluna < 8; coluna++) {
				painelTabuleiro.add(tabuleiro.obtemBotaoCasa(linha, coluna));
				tabuleiro.obtemBotaoCasa(linha, coluna).addActionListener(this);
				tabuleiro.obtemBotaoCasa(linha, coluna).setActionCommand(linha + "," + coluna);
			}
		}
	}

	void adicionaAsPecas(JPanel painelTabuleiro) {
		//pretas
		tabuleiro.obtemBotaoCasa(0, 0).setIcon(new ImageIcon(torrePreta));
		tabuleiro.obtemBotaoCasa(0, 1).setIcon(new ImageIcon(cavaloPreto));
		tabuleiro.obtemBotaoCasa(0, 2).setIcon(new ImageIcon(bispoPreto));
		tabuleiro.obtemBotaoCasa(0, 3).setIcon(new ImageIcon(rainhaPreta));
		tabuleiro.obtemBotaoCasa(0, 4).setIcon(new ImageIcon(reiPreto));
		tabuleiro.obtemBotaoCasa(0, 5).setIcon(new ImageIcon(bispoPreto));
		tabuleiro.obtemBotaoCasa(0, 6).setIcon(new ImageIcon(cavaloPreto));
		tabuleiro.obtemBotaoCasa(0, 7).setIcon(new ImageIcon(torrePreta));

		for (int i = 0; i < 8; i++) {
			tabuleiro.obtemBotaoCasa(1, i).setIcon(new ImageIcon(peaoPreto));
		}

		//brancas
		tabuleiro.obtemBotaoCasa(7, 0).setIcon(new ImageIcon(torreBranca));
		tabuleiro.obtemBotaoCasa(7, 1).setIcon(new ImageIcon(cavaloBranco));
		tabuleiro.obtemBotaoCasa(7, 2).setIcon(new ImageIcon(bispoBranco));
		tabuleiro.obtemBotaoCasa(7, 3).setIcon(new ImageIcon(rainhaBranca));
		tabuleiro.obtemBotaoCasa(7, 4).setIcon(new ImageIcon(reiBranco));
		tabuleiro.obtemBotaoCasa(7, 5).setIcon(new ImageIcon(bispoBranco));
		tabuleiro.obtemBotaoCasa(7, 6).setIcon(new ImageIcon(cavaloBranco));
		tabuleiro.obtemBotaoCasa(7, 7).setIcon(new ImageIcon(torreBranca));

		for (int i = 0; i < 8; i++) {
			tabuleiro.obtemBotaoCasa(6, i).setIcon(new ImageIcon(peaoBranco));
		}

		super.getContentPane().add(painelTabuleiro);
	}

	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		FrameTabuleiro frame = new FrameTabuleiro();
	}
}
