package model.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import br.edu.fateczl.Fila;
import br.edu.fateczl.Lista;
import model.dto.IGenericEntity;
import util.CsvUtils;

public abstract class GenericDAO<T extends IGenericEntity, ID>{

    private String caminhoArquivo;

    public GenericDAO(String nomeArquivoCSV) throws IOException{
        caminhoArquivo = resolveCaminho(nomeArquivoCSV);
    }

    private static String resolveCaminho(String nomeArquivoCSV) throws IOException {
        String os = System.getProperty("os.name").toLowerCase();
        String baseDir = os.contains("win") ? "C:\\TEMP\\" : "/tmp/";

        Path diretorio = Paths.get(baseDir);
        Files.createDirectories(diretorio);
        
        return diretorio.resolve(nomeArquivoCSV).toString();
    }

    public void salvar(T entidade) throws IOException {
        CsvUtils.adicionarLinhaCSV(caminhoArquivo, entityToCSV(entidade));
    }

    public Fila<T> buscarTodos() throws Exception {
        Lista<String[]> dados = CsvUtils.lerArquivo(caminhoArquivo);
        Fila<T> entidades = new Fila<>();

        for (int i = 0, length = dados.size(); i < length; i++) {
            T entidade = csvToEntity(dados.get(i));
            entidades.insert(entidade);
        }
        return entidades;
    }

    public void atualizar(T entidadeAtualizada) throws Exception {
        Fila<T> entidades = buscarTodos();
        Lista<String> entidadesCSV = new Lista<>();

        while (!entidades.isEmpty()) {
            T entidade = entidades.remove();
            if (entidade.getId().equals(entidadeAtualizada.getId())) {
                entidade = entidadeAtualizada;
            }
            entidadesCSV.addLast(entityToCSV(entidade));
        }

        CsvUtils.escreverCSV(caminhoArquivo, entidadesCSV);
    }

    public void excluir(Integer id) throws Exception {
        Fila<T> entidades = buscarTodos();
        Lista<String> entidadesCSV = new Lista<>();

        while (!entidades.isEmpty()) {
            T entidade = entidades.remove();
            if (!entidade.getId().equals(id)) {
                entidadesCSV.addLast(entityToCSV(entidade));
            }
        }

        CsvUtils.escreverCSV(caminhoArquivo, entidadesCSV);
    }

    public T buscarPorID(Integer id) throws Exception {
        Fila<T> entidades = buscarTodos();

        while (!entidades.isEmpty()) {
            T entidade = entidades.remove();
            if (entidade.getId().equals(id)) {
                return entidade;
            }
        }

        throw new Exception("T não encontrado");
    }

    protected abstract String entityToCSV(T entidade);
    protected abstract T csvToEntity(String[] strings);
      
}
