package model.dto;

public class Curso implements IGenericEntity {
	private Integer id;
	private String nome;
	private Integer idAreaConhecimento;

	public Curso(Integer id, String nome, Integer idAreaConhecimento) {
		this.id = id;
		this.nome = nome;
		this.idAreaConhecimento = idAreaConhecimento;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdAreaConhecimento() {
		return idAreaConhecimento;
	}

	public void setIdAreaConhecimento(Integer idAreaConhecimento) {
		this.idAreaConhecimento = idAreaConhecimento;
	}

	@Override
	public String toString() {
		return nome;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Curso)) return false;
		Curso curso = (Curso) obj;
		return id != null && id.equals(curso.id);
	}
	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}
