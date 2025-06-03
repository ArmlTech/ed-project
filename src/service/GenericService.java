package service;

import br.edu.fateczl.Fila;
import model.dao.GenericDAO;
import model.dto.IGenericEntity;

public abstract class GenericService<T extends IGenericEntity, ID, D extends GenericDAO<T, ID>> {

    protected final D dao;
    public GenericService(D dao){
        this.dao = dao;
    }
    
    public void salvar(T entidade) throws Exception {
        dao.salvar(entidade);
    }

    public Fila<T> buscarTodos() throws Exception {
        return dao.buscarTodos();
    }

    public void atualizar(T entidade) throws Exception {
        dao.atualizar(entidade);
    }

    public void excluir(Integer id) throws Exception {
        dao.excluir(id);
    }

    public T buscarPorID(Integer id) throws Exception {
        return dao.buscarPorID(id);
    }
}
