package br.com.nemeth.chess;

public class Vizinho {

	private int x;
	private int y;
	private int numVizinhos;

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getNumVizinhos() {
		return numVizinhos;
	}

	public void setNumVizinhos(int numVizinhos) {
		this.numVizinhos = numVizinhos;
	}

	public Vizinho(int x, int y, int numVizinhos) {
		this.x = x;
		this.y = y;
		this.numVizinhos = numVizinhos;
	}
}
