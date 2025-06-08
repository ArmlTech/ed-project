package controller;

import br.edu.fateczl.Lista;
import model.dto.InscritoDisplay;

public class QuickSortInscritos {

	public static Lista<InscritoDisplay> quickSort(Lista<InscritoDisplay> lista, int inicio, int fim) throws Exception {
		if(fim > inicio) {
			int pivoFixo = dividir(lista, inicio, fim);
			quickSort(lista, inicio, pivoFixo - 1); //Esquerda
			quickSort(lista, pivoFixo + 1, fim); //Direita
		}
		return lista;
	}
	
	private static int dividir(Lista<InscritoDisplay> lista, int inicio, int fim) throws Exception {
		InscritoDisplay pivo = lista.get(inicio);
		int esq = inicio + 1;
		int dir = fim;
		
		while(esq <= dir) {
			while(esq <= dir && lista.get(esq).getQtdPontos() >= pivo.getQtdPontos()) {
				esq++;
			}
			while(dir >= esq && lista.get(dir).getQtdPontos() < pivo.getQtdPontos()) {
				dir--;
			}
			if(esq < dir) {
				trocar(lista, esq, dir);
				esq++;
				dir--;
			}
		}
		trocar(lista, inicio, dir);
		return dir;
	}


	private static void trocar(Lista<InscritoDisplay> lista, int i, int j) throws Exception {
		InscritoDisplay aux = lista.get(i);
		
		lista.add(lista.get(j), i);
		lista.remove(i+1);
		
		lista.add(aux, j);
		lista.remove(j+1);
		
	}

}