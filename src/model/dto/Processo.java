package model.dto;

public class Processo implements IGenericEntity {
	private Integer id;
	private boolean aberto;
	private Integer idDisciplina;

	public Processo(Integer id, boolean aberto, Integer idDisciplina) {
		this.id = id;
		this.aberto = aberto;
		this.idDisciplina = idDisciplina;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isAberto() {
		return aberto;
	}

	public void setAberto(boolean aberto) {
		this.aberto = aberto;
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
				", status=" + aberto +
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
