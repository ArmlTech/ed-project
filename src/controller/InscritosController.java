package controller;

import br.edu.fateczl.Fila;
import br.edu.fateczl.Lista;
import model.dto.Candidatura;
import model.dto.InscritoDisplay;
import model.dto.Professor;
import service.AreaConhecimentoService;
import service.InscricaoService;
import service.ProcessoService;
import service.ProfessorService;


public class InscritosController{

    private final InscricaoService service;
    private final ProfessorService profService;
    private final ProcessoService processoService;
    private final AreaConhecimentoService areaConhecimentoService;

    public InscritosController() {
        this.service = new InscricaoService();
        this.profService = new ProfessorService();
        this.processoService = new ProcessoService();
        this.areaConhecimentoService = new AreaConhecimentoService();
    }

    public Lista<InscritoDisplay> buscarTodos() throws Exception {
        
        Fila<Candidatura> candidaturas = service.buscarTodos();
        Lista<InscritoDisplay> inscritos = new Lista<>();

        while (!candidaturas.isEmpty()) {
            Candidatura candidatura = candidaturas.remove();

            Fila<Professor> professores = profService.buscarTodos();
            while (!professores.isEmpty()) {
                Professor professor = professores.remove();
                if (candidatura.getIdProfessor() == professor.getId()) {

                    String area = areaConhecimentoService.buscarPorID(candidatura.getIdProcesso()).getNome();
                    String disciplina = processoService.buscarNomeDisciplina(candidatura.getIdProcesso());

                    InscritoDisplay inscrito = new InscritoDisplay(
                        candidatura.getId(),
                        professor.getCpf(),
                        professor.getNome(),
                        professor.getQtdPontos(),
                        area,
                        disciplina,
                        candidatura.getIdProcesso()
                    );
                    inscritos.addLast(inscrito);
                    break;
                }
            }
        
        }

        // Ordena os inscritos por pontuação com QuickSort
        inscritos = QuickSortInscritos.quickSort(inscritos, 0, inscritos.size() - 1);
        return inscritos;
    }

}
