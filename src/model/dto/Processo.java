package model.dto;

public class Processo implements IGenericEntity {
	private Integer id;
	private Integer idDisciplina;

	public Processo(Integer id,Integer idDisciplina) {
		this.id = id;
		this.idDisciplina = idDisciplina;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(Integer idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	@Override
	public String toString() {
		return "Processo{" +
				"id=" + id +
				", idDisciplina=" + idDisciplina +
				'}';
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Processo)) return false;
		Processo processo = (Processo) obj;
		return id != null && id.equals(processo.id);
	}
	
	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}
