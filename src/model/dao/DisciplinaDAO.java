package model.dao;

import br.edu.fateczl.Lista;
import br.edu.fateczl.pilha.Pilha;
import model.dto.Disciplina;
import util.CsvUtils;

public class DisciplinaDAO implements IGenericDAO<Disciplina, Integer> {

    final private String caminhoArquivo = "C:\\TEMP\\disciplinas.csv";

    @Override
    public void salvar(Disciplina entidade) throws Exception {
        CsvUtils.adicionarLinhaCSV(caminhoArquivo, toCSV(entidade));
    }

    @Override
    public Pilha<Disciplina> buscarTodos() throws Exception {
        Lista<String[]> dados = CsvUtils.lerArquivo(caminhoArquivo);
        Pilha<Disciplina> disciplinas = new Pilha<>();

        for(int i = 0, length = dados.size(); i < length; i++){
                Integer id = Integer.parseInt(dados.get(i)[0]);
                String nome = dados.get(i)[1];
                String diaSemana = dados.get(i)[2];
                String horaInicial = dados.get(i)[3];
                Double qtdHoras = Double.parseDouble(dados.get(i)[4]);
                Integer idCurso = Integer.parseInt(dados.get(i)[5]);
                Disciplina disciplina = new Disciplina(id, nome, diaSemana, horaInicial, qtdHoras, idCurso);
                disciplinas.push(disciplina);
        }
        return disciplinas;
    }

    @Override
    public void atualizar(Disciplina entidade) throws Exception {
        Pilha<Disciplina> disciplinas = buscarTodos();
        Lista<String> disciplinasCSV = new Lista<>();

        while(!disciplinas.isEmpty()){
            Disciplina disciplina = disciplinas.pop();

            if(disciplina.getId() == entidade.getId()){
                disciplina = entidade;
            }
            disciplinasCSV.addLast(toCSV(entidade));

        }

        CsvUtils.escreverCSV(caminhoArquivo, disciplinasCSV);
    }

    @Override
    public void excluir(Integer id) throws Exception {
        Pilha<Disciplina> disciplinas = buscarTodos();
        Lista<String> disciplinasCSV = new Lista<>();

        while(!disciplinas.isEmpty()){
            Disciplina disciplina = disciplinas.pop();

            if(disciplina.getId() != id){
                disciplinasCSV.addLast(toCSV(disciplina));
            }

        }

        CsvUtils.escreverCSV(caminhoArquivo, disciplinasCSV);
    }

    @Override
    public Disciplina buscarPorID(Integer id) throws Exception {
        
        Pilha<Disciplina> disciplinas = buscarTodos();
        while(!disciplinas.isEmpty()){
            Disciplina disciplina = disciplinas.pop();

            if(disciplina.getId() == id){
                return disciplina;
            }

        }

        throw new Exception("Disciplina não encontrada");
    }

    public Disciplina buscarPorNome(String nome) throws Exception {
        
        Pilha<Disciplina> disciplinas = buscarTodos();
        while(!disciplinas.isEmpty()){
            Disciplina disciplina = disciplinas.pop();

            if(disciplina.getNome().equals(nome)){
                return disciplina;
            }

        }

        throw new Exception("Disciplina não encontrada");
    }


    @Override
    public String toCSV(Disciplina entidade) {
        return 
            entidade.getId() + ";" + 
            entidade.getNome() + ";" + 
            entidade.getDiaSemana() + ";" + 
            entidade.getHoraInicial() + ";" + 
            entidade.getQtdHoras() + ";" + 
            entidade.getIdCurso();
    }
    

}
