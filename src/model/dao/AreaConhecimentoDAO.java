package model.dao;

import java.io.IOException;

import br.edu.fateczl.Lista;
import br.edu.fateczl.pilha.Pilha;
import model.dto.AreaConhecimento;
import util.CsvUtils;

public class AreaConhecimentoDAO {
	
	final private String caminhoArquivo = "C:\\TEMP\\areas.csv";
    public Pilha<AreaConhecimento> buscarTodos() throws Exception {

        Lista<String[]> dados = CsvUtils.lerArquivo(caminhoArquivo);
        Pilha<AreaConhecimento> areas = new Pilha<>();

        for(int i = 0, length = dados.size(); i < length; i++){
            int id = Integer.parseInt(dados.get(i)[0]);
            String nome = dados.get(i)[1];
            AreaConhecimento area = new AreaConhecimento(id, nome);
            areas.push(area);
        }
        return areas;
    }

    public AreaConhecimento procurarPorID(int id) throws Exception {

        Pilha<AreaConhecimento> areas = buscarTodos();

        while(!areas.isEmpty()){
        	AreaConhecimento area = areas.pop();
            if(area.getAreaID() == id){
                return area;
            }
        }

        throw new Exception("Professor não encontrado");
    }

    public void cadastrarNovo(AreaConhecimento area) throws IOException {
        CsvUtils.adicionarLinhaCSV(caminhoArquivo, area.toCSV());
    }

    public void atualizarDados(AreaConhecimento areaAtualizada) throws Exception {

    	Pilha<AreaConhecimento> areas = buscarTodos();
        Lista<String> areasCSV = new Lista<>();

        while(!areas.isEmpty()){
        	AreaConhecimento area = areas.pop();
            if(area.getAreaID() == areaAtualizada.getAreaID()){
                area = areaAtualizada;
            }
            areasCSV.addLast(area.toCSV());
        }

        CsvUtils.escreverCSV(caminhoArquivo, areasCSV);
    }

    public void excluir (int id) throws Exception {

    	Pilha<AreaConhecimento> areas = buscarTodos();
        Lista<String> areasCSV = new Lista<>();

        while(!areas.isEmpty()){
        	AreaConhecimento area = areas.pop();
            if(area.getAreaID() != id){
            	areasCSV.addLast(area.toCSV());
            }
        }

        CsvUtils.escreverCSV(caminhoArquivo, areasCSV);
    }
    
}
