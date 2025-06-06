package controller;

import java.io.IOException;

import br.edu.fateczl.Fila;
import br.edu.fateczl.Lista;
import model.dto.AreaConhecimento;
import model.dto.Professor;
import service.AreaConhecimentoService;
import service.ProfessorService;

public class ProfessorController implements IGenericController<Professor, Integer>{

    private final ProfessorService service;
    private final AreaConhecimentoService areaService;

    public ProfessorController() {
        this.service = new ProfessorService();
        this.areaService = new AreaConhecimentoService();
    }

	@Override
	public void salvar(Professor entidade) throws Exception {
		service.salvar(entidade);
	}
	
	@Override
	public Fila<Professor> buscarTodos() throws Exception {
		return service.buscarTodos();
	}
	
	@Override
	public void atualizar(Professor entidade) throws Exception {
		service.atualizar(entidade);
	}

	@Override
	public void excluir(Professor entidade) throws Exception {
		service.excluir(entidade.getId());
	}

	@Override
	public Professor buscarPorID(Integer id) throws Exception {
		return service.buscarPorID(id);
	}

	public Professor buscarPorCPF(String id) throws Exception {
		return service.buscarPorCPF(id);
	}

	public Fila<AreaConhecimento> buscarTodosArea() throws Exception {
		return areaService.buscarTodos();
	}

	public AreaConhecimento buscarAreaPorId(Integer id) throws Exception {
		return areaService.buscarPorID(id);
	}

	@Override
	public Professor criarEntidade(Professor entidade, Lista<String> dadosInput) throws Exception {

		for(int i = 0, length = dadosInput.size(); i < length; i++){
			if(dadosInput.get(i).isBlank()){
				throw new Exception("Preencha todos os campos");
			}
		}

		//TODO fazer lógica de verificação CPF aqui ou na service

		Integer id = (entidade == null) ? 0 : entidade.getId();
		String cpf = dadosInput.get(0);
		String nome = dadosInput.get(1);
		Float qtdPontos;
		try {
			qtdPontos = Float.parseFloat(dadosInput.get(2).replace(',', '.'));
		} catch (NumberFormatException e) {
			throw new Exception("Insira apenas números no campo Quantidade de Pontos");
		}
		Integer idArea = Integer.parseInt(dadosInput.get(3));

		Professor professor = new Professor(id, cpf, nome, qtdPontos, idArea);
		return professor;
	}



}
