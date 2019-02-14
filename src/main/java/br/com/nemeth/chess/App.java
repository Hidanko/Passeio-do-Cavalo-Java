package br.com.nemeth.chess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class App {
	private final static int base = 12;
	private static int[][] matriz;
	private static int total;
	private static List<Posicao> movimentos;

	public static void main(String[] args) {

		matriz = new int[12][12];
		total = 64;

		movimentos = new ArrayList<Posicao>();
		movimentos.add(new Posicao(1, -2));
		movimentos.add(new Posicao(2, -1));
		movimentos.add(new Posicao(2, 1));
		movimentos.add(new Posicao(1, 2));
		movimentos.add(new Posicao(-1, 2));
		movimentos.add(new Posicao(-2, 1));
		movimentos.add(new Posicao(-2, -1));
		movimentos.add(new Posicao(-1, -2));

		for (int i = 0; i < base; i++) {
			for (int j = 0; j < base; j++) {
				if (i < 2 || i > base - 3 || j < 2 || j > base - 3) {
					matriz[i][j] = -1;
				}
			}
		}

		int x = 2 + 4;
		int y = 2 + 3;

		matriz[x][y] = 1;

		run(x, y, 2);
		imprimeMatriz();

	}

	// recursividade
	private static boolean run(int x, int y, int count) {
		// criterio de parada
		if (count > total) {
			return true;
		}
		List<Vizinho> vizinhos = getVizinhos(x, y);

		if (vizinhos.isEmpty() && count != total)
			return false;

		// ordenação
		Collections.sort(vizinhos, new Comparator<Vizinho>() {
			public int compare(Vizinho a, Vizinho b) {
				return a.getNumVizinhos() - b.getNumVizinhos();
			}
		});

		for (Vizinho vizinho : vizinhos) {
			matriz[vizinho.getX()][vizinho.getY()] = count;
			if (!semSaida(count, vizinho.getX(), vizinho.getY()) && run(vizinho.getX(), vizinho.getY(), count + 1)) {
				return true;
			}
			matriz[x][y] = 0;
		}

		return false;
	}

	// retornar todos os movimentos possiveis
	private static List<Vizinho> getVizinhos(int r, int c) {
		List<Vizinho> vizinhos = new ArrayList<Vizinho>();
		for (Posicao p : movimentos) {
			if (matriz[r + p.getY()][c + p.getX()] == 0) {
				int num = numeroVizinhos(r + p.getY(), c + p.getX());
				vizinhos.add(new Vizinho(r + p.getY(), c + p.getX(), num));
			}
		}
		return vizinhos;
	}

	private static int numeroVizinhos(int x, int y) {
		int num = 0;
		for (Posicao p : movimentos) {
			if (matriz[x + p.getY()][y + p.getX()] == 0) {
				num++;
			}
		}
		return num;
	}

	private static boolean semSaida(int cont, int r, int c) {
		if (cont < total - 1) {
			List<Vizinho> vizinhos = getVizinhos(r, c);
			for (Vizinho vizinho : vizinhos) {
				if (numeroVizinhos(vizinho.getX(), vizinho.getY()) == 0) {
					return true;
				}
			}
		}
		return false;
	}

	private static void imprimeMatriz() {
		for (int[] row : matriz) {
			for (int i : row) {
				if (i == -1)
					continue;
				System.out.print(i + "\t");
			}
			System.out.println("\n");
		}
	}
}