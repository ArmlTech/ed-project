package util;

import java.io.*;
import br.edu.fateczl.Lista;

public class CsvUtils {
	
	public static Lista<String[]> lerArquivo (String caminhoArquivo) throws IOException {
		File arquivo = new File(caminhoArquivo);
		if(!arquivo.exists() || !arquivo.isFile()){
			throw new IOException("Caminho do arquivo inválido");
		}

		Lista<String[]> linhas = new Lista<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader (arquivo))){

			String linha;
			while((linha = br.readLine()) != null) {
				if (!linha.trim().isEmpty()) {
					linhas.addLast(linha.split(";"));
				}
			}
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}

		return linhas;
	}

	public static void escreverCSV (String caminhoArquivo, Lista<String> linhas) throws IOException {
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))){
			
			for (int i = 0, size = linhas.size(); i < size; i++) {
				bw.write(linhas.get(i));
				bw.newLine();
			}
			
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
		
	}
	
	public static void adicionarLinhaCSV (String caminhoArquivo, String novaLinha) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo, true))){
			bw.write(novaLinha);
			bw.newLine();
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
	}

	
}
