package model.dao;

import br.edu.fateczl.Fila;
import model.dto.Professor;


public class ProfessorDAO extends GenericDAO<Professor, Integer> {

<<<<<<< HEAD
	final private String caminhoArquivo = "C:\\TEMP\\professores.csv";

	@Override
	public void salvar(Professor professor) throws IOException {
		CsvUtils.adicionarLinhaCSV(caminhoArquivo, toCSV(professor));
	}

	@Override
	public Pilha<Professor> buscarTodos() throws Exception {

		Lista<String[]> dados = CsvUtils.lerArquivo(caminhoArquivo);
		Pilha<Professor> professores = new Pilha<>();

        for(int i = 0, length = dados.size(); i < length; i++){
            Integer id = Integer.parseInt(dados.get(i)[0]);
            String cpf = dados.get(i)[1];
            String nome = dados.get(i)[2];
            Float qtdPontos = Float.parseFloat(dados.get(i)[3]);
            Integer areaID = Integer.parseInt(dados.get(i)[4]);
            Professor professor = new Professor(id, cpf, nome, qtdPontos, areaID);
            professores.push(professor);
        }
        return professores;
    }
    
    @Override
    public void atualizar(Professor professorAtualizado) throws Exception {

		Pilha<Professor> professores = buscarTodos();
		Lista<String> professoresCSV = new Lista<>();

        while(!professores.isEmpty()){
            Professor professor = professores.pop();
            if(professor.getCpf().equals(professorAtualizado.getCpf())){
                professor = professorAtualizado;
            }
            professoresCSV.addFirst(toCSV(professor));
        }

        CsvUtils.escreverCSV(caminhoArquivo, professoresCSV);
    }
    
    @Override
    public void excluir (Integer id) throws Exception {

		Pilha<Professor> professores = buscarTodos();
		Lista<String> professoresCSV = new Lista<>();

        while(!professores.isEmpty()){
            Professor professor = professores.pop();
            if(id != professor.getId()){
                professoresCSV.addFirst(toCSV(professor));
            }
        }

        CsvUtils.escreverCSV(caminhoArquivo, professoresCSV);
    }
    
    public Professor buscarPorCPF(String cpf) throws Exception {

		Pilha<Professor> professores = buscarTodos();

		while (!professores.isEmpty()) {
			Professor professor = professores.pop();
			if (professor.getCpf().equals(cpf)) {
				return professor;
			}
		}
=======
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
>>>>>>> 18d9c03 (refactor: generalizando dao pra simplificar o codigo e criação do crud inscrição)

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
