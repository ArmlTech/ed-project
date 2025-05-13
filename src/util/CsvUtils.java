package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import br.edu.fateczl.Lista;

public class CsvUtils {
	
	public static Lista<String> lerCSSV (String caminhoArquivo) {
		
		Lista<String> linhas = new Lista<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader (caminhoArquivo))){
			
			String linha;
			while((linha = br.readLine()) != null) {
				if (!linha.trim().isEmpty()) {
					linhas.addLast(linha);
				}
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return linhas;
	}
	
	public static void escreverCSV (String caminhoArquivo, Lista<String> linhas) {
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))){
			
			for (int i = 0, size = linhas.size(); i < size; i++) {
				bw.write(linhas.get(i));
				bw.newLine();
			}
			
		} catch (Exception e) {

		}
		
	}
	
	public static void adicionarLinhaCSV (String caminhoArquivo, String novaLinha) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))){
			bw.write(novaLinha);
			bw.newLine();
		} catch (Exception e) {
			
		}
	}
	
}
