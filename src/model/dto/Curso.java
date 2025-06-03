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

	public Integer getId() {
		return id;
	}

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
}
