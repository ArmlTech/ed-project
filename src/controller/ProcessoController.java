package controller;

import br.edu.fateczl.Fila;
import br.edu.fateczl.Lista;
import model.dto.Disciplina;
import model.dto.Processo;
import service.ProcessoService;

public class ProcessoController implements IGenericCrudController<Processo, Integer> {

    private final ProcessoService service;
    private Lista<Processo>[] tabelaHashProcessos;

    public ProcessoController() {
        this.service = new ProcessoService();
        tabelaHashProcessos = new Lista[7];
    }

    private void inicializarTabelaHash() {
        tabelaHashProcessos = new Lista[10]; // Tamanho inicial da tabela hash
        for (int i = 0; i < tabelaHashProcessos.length; i++) {
            tabelaHashProcessos[i] = new Lista<>(); 
        }
    }

    @Override
    public void salvar(Processo entidade) throws Exception {
        service.salvar(entidade);
    }

    public Lista<Processo>[] buscarTabelaHash() throws Exception {
        Fila<Processo> processos = service.buscarTodos();
        inicializarTabelaHash();
        while (!processos.isEmpty()) {
            Processo processo = processos.remove();
            if (processo.isAberto()) {
                int index = hashCode(processo.getId());
                tabelaHashProcessos[index].addLast(processo);
            }
            
        }
        return tabelaHashProcessos;
    }

    private int hashCode(Integer id) {
        return id % tabelaHashProcessos.length;
    }

    @Override
    public void atualizar(Processo entidade) throws Exception {
        service.atualizar(entidade);
    }

    @Override
    public void excluir(Processo entidade) throws Exception {
        service.excluir(entidade.getId());
    }

    @Override
    public Processo buscarPorID(Integer id) throws Exception {
        return service.buscarPorID(id);
    }

    @Override
    public Processo criarEntidade(Processo entidade, Lista<String> dadosInput) throws Exception {
        for(int i = 0, length = dadosInput.size(); i < length; i++){
			if(dadosInput.get(i).isBlank()){
				throw new Exception("Preencha todos os campos");
			}
		}

		Integer id = (entidade == null) ? 0 : entidade.getId();
		boolean isAberto = dadosInput.get(0).equalsIgnoreCase("Ativo");
        Integer idDisciplina = Integer.parseInt(dadosInput.get(1));
	
        Processo processo = new Processo(id, isAberto, idDisciplina);
        return processo;
    }

    public String buscarNomeDisciplina(Integer idDisciplina) throws Exception {
        return service.buscarNomeDisciplina(idDisciplina);
    }

    public Lista<Disciplina> buscarTodasDisciplinas() throws Exception {
        return service.buscarTodasDisciplinas();
    }

    public Disciplina buscarDisciplinaPorId(Integer id) throws Exception {
        return service.buscarDisciplinaPorId(id);
    }

    @Override
    public Fila<Processo> buscarTodos() throws Exception {
        return service.buscarTodos();
    }

}
