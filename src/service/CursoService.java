package service;

import java.io.IOException;

import model.dao.CursoDAO;
import model.dto.Curso;

public class CursoService extends GenericService<Curso, Integer, CursoDAO> {

    public CursoService() throws IOException {
        super(new CursoDAO());
    }

}
