package br.com.nemeth.chess;

import java.util.ArrayList;
import java.util.List;

public class Cavalo {
	Integer[][] matriz;
	List<Posicao> resposta;

	public Cavalo() {
		matriz = new Integer[8][8];

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				matriz[i][j] = -1;
			}
		}
		
	}

	public void run() {
		Posicao inicial = new Posicao(0, 0);
		resposta = new ArrayList<Posicao>();
		List<Posicao> resultado = rec(inicial, matriz, resposta);
		System.out.println(resultado);
	}

	private List<Posicao> rec(Posicao pos, Integer[][] matriz, List<Posicao> lista ){
		int x = pos.getX();
		int y = pos.getY();
		
		if (lista.size() == 64) {
			return lista;
		}
		
		matriz[x][y] = lista.size();
		lista.add(new Posicao(x, y));
		
		List<List<Posicao>> possibilidades = new ArrayList<List<Posicao>>();
		
	    if (x - 1 >= 0 && y + 2 <= 7) {
	        if (matriz[x - 1][y + 2] < 0) {
	          possibilidades.add(rec(new Posicao(x - 1, y + 2), matriz, lista));
	        }}
	    if (x - 1 >= 0 && y - 2 >= 0){
	        if (matriz[x - 1][y - 2] < 0){
	          possibilidades.add(rec(new Posicao(x - 1, y - 2), matriz, lista));
	        }}
	    if (x + 1 <= 7 && y + 2 <= 7){
	        if (matriz[x + 1][y + 2] < 0){
	          possibilidades.add(rec(new Posicao(x + 1, y + 2), matriz, lista));
	        }}
	    if (x + 1 <= 7 && y - 2 >= 0){
	        if (matriz[x + 1][y - 2] < 0){
	          possibilidades.add(rec(new Posicao(x + 1, y - 2), matriz, lista));
	        }}
	    if (x - 2 >= 0 && y - 1 >= 0){
	        if (matriz[x - 2][y - 1] < 0){
	          possibilidades.add(rec(new Posicao(x - 2, y - 1), matriz, lista));
	        }}
	    if (x + 2 <= 7 && y - 1 >= 0){
	        if (matriz[x + 2][y - 1] < 0){
	          possibilidades.add(rec(new Posicao(x + 2, y - 1), matriz, lista));
	        }}
	    if (x - 2 >= 0 && y + 1 <= 7){
	        if (matriz[x - 2][y + 1] < 0){
	          possibilidades.add(rec(new Posicao(x - 2, y + 1), matriz, lista));
	        }}
	    if (x + 2 <= 7 && y + 1 <= 7){
	        if (matriz[x + 2][y + 1] < 0){
	          possibilidades.add(rec(new Posicao(x + 2, y + 1), matriz, lista));
	        }}

		return getMaiorLista(possibilidades);
	}
	
	private List<Posicao> getMaiorLista(List<List<Posicao>> listas){
		
		int tam = -1;
		List<Posicao> retorno = new ArrayList<Posicao>();
		
		for (List<Posicao> lista : listas) {
			if (lista.size() > tam) {
				retorno = lista;
			}
		}
		return retorno;
	}

}