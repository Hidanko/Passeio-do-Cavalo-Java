package br.com.nemeth.chess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class App {
	// +2 em cada lado do tabuleiro para que em vez de validar as 8 posições, apenas validar se o numero é 0
	private final static int tamanho = 12;
	private static int[][] matriz;
	private static int total = 64;
	private static List<Posicao> movimentos;
	private static int iteracoes = 0;
	private static int iteracoesIgnoradas = 0;

	public static void main(String[] args) {
		movimentos = new ArrayList<Posicao>();
		movimentos.add(new Posicao(1, -2));
		movimentos.add(new Posicao(2, -1));
		movimentos.add(new Posicao(2, 1));
		movimentos.add(new Posicao(1, 2));
		movimentos.add(new Posicao(-1, 2));
		movimentos.add(new Posicao(-2, 1));
		movimentos.add(new Posicao(-2, -1));
		movimentos.add(new Posicao(-1, -2));

		matriz = new int[12][12];

		for (int i = 0; i < tamanho; i++) {
			for (int j = 0; j < tamanho; j++) {
				if (i < 2 || i > tamanho - 3 || j < 2 || j > tamanho - 3) {
					matriz[i][j] = -1;
				}
			}
		}
		//adicionar 2 por causa da borda
		int x = 2 + 0;
		int y = 2 + 0;

		matriz[x][y] = 1;
		Long antes = System.currentTimeMillis();
		run(x, y, 2);
		Long depois = System.currentTimeMillis();
		System.out.println("Tempo de execução: "+(depois - antes));
		System.out.println("Iteracões realizadas: "+ iteracoes);
		System.out.println("Iterações ignoradas com a otimização: "+ iteracoesIgnoradas);
		imprimeMatriz();

	}

	// recursividade
	private static boolean run(int x, int y, int count) {
		iteracoes++;
		// criterio de parada
		if (count > total) {
			return true;
		}
		List<Vizinho> vizinhos = getVizinhos(x, y);

		if (vizinhos.isEmpty() && count != total) {
			return false;
		}
		// Ordenação para dar preferencia aos vizinhos com mais vizinhos
		Collections.sort(vizinhos, new Comparator<Vizinho>() {
			public int compare(Vizinho a, Vizinho b) {
				return a.getNumVizinhos() - b.getNumVizinhos();
			}
		});

		for (Vizinho vizinho : vizinhos) {
			matriz[vizinho.getX()][vizinho.getY()] = count;
			// Se o vizinho não possui vizinhos, ignorar iteração
			if (!semSaida(count, vizinho.getX(), vizinho.getY())) {
				if (run(vizinho.getX(), vizinho.getY(), count + 1)) {
					return true;
				}
			} else {
				iteracoesIgnoradas++;
			}
			matriz[x][y] = 0;
		}

		return false;
	}

	// retornar todos os movimentos possiveis
	private static List<Vizinho> getVizinhos(int x, int y) {
		List<Vizinho> vizinhos = new ArrayList<Vizinho>();
		for (Posicao p : movimentos) {
			if (matriz[x + p.getY()][y + p.getX()] == 0) {
				int num = numeroVizinhos(x + p.getY(), y + p.getX());
				vizinhos.add(new Vizinho(x + p.getY(), y + p.getX(), num));
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

	private static void imprimeMatriz() {
		for (int[] row : matriz) {
			for (int i : row) {
				if (i == -1) {
					continue;
				}
				System.out.print(i + "\t");
			}
			System.out.println("\n");
		}
	}

	// otimizacao
	private static boolean semSaida(int cont, int x, int y) {
		if (cont < total - 1) {
			List<Vizinho> vizinhos = getVizinhos(x, y);
			for (Vizinho vizinho : vizinhos) {
				if (numeroVizinhos(vizinho.getX(), vizinho.getY()) == 0) {
					return true;
				}
			}
		}
		return false;
	}

}