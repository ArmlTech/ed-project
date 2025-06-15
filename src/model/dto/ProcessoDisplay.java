package model.dto;

public class ProcessoDisplay implements IGenericEntity {

    private Integer idProcesso;
    private String nomeDisciplina;

    public ProcessoDisplay(Integer idProcesso, String nomeDisciplina) {
        this.idProcesso = idProcesso;
        this.nomeDisciplina = nomeDisciplina;
    }

    @Override
    public Integer getId() {
        return idProcesso;
    }
    @Override
    public void setId(Integer idProcesso) {
        this.idProcesso = idProcesso;
    }
    public String getNomeDisciplina() {
        return nomeDisciplina;
    }
    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    @Override
    public String toString() {
        return "Processo ID: " + idProcesso + " | Disciplina: " + nomeDisciplina;
    }

     @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ProcessoDisplay)) return false;
        ProcessoDisplay other = (ProcessoDisplay) obj;
        return this.idProcesso.equals(other.idProcesso);
    }

    @Override
    public int hashCode() {
        return idProcesso != null ? idProcesso.hashCode() : 0;
    }
}
