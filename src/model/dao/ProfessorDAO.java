package model.dao;

import br.edu.fateczl.Fila;
import model.dto.Professor;


public class ProfessorDAO extends GenericDAO<Professor, Integer> {

    public ProfessorDAO() {
        super("professores.csv");
    }
        
    public Professor buscarPorCPF(String cpf) throws Exception {

        Fila<Professor> professores = buscarTodos();

        while(!professores.isEmpty()){
            Professor professor = professores.remove();
            if(professor.getCpf().equals(cpf)){
                return professor;
            }
        }

        throw new Exception("Professor não encontrado");
    }

    @Override
    protected String entityToCSV(Professor entidade) {
        return 
            "" + entidade.getId() + ";" +
            entidade.getCpf() + ';' + 
            entidade.getNome() + ';' + 
            entidade.getQtdPontos() + ";" + 
            entidade.getAreaID();
    }

    @Override
    protected Professor csvToEntity(String[] dados) {
        Integer id = Integer.parseInt(dados[0]);
        String cpf = dados[1];
        String nome = dados[2];
        Float qtdPontos = Float.parseFloat(dados[3]);
        Integer areaID = Integer.parseInt(dados[4]);
        return new Professor(id, cpf, nome, qtdPontos, areaID);
    }

 }
