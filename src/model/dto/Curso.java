package model.dto;

public class Curso implements IGenericEntity {
	private Integer id;
	private String nome;
	private Integer idAreaConhecimento;

	public Curso(Integer id, String nome, Integer idAreaConhecimento) {
<<<<<<< HEAD

		this.id = id;
		this.nome = nome;
		this.idAreaConhecimento = idAreaConhecimento;

=======
		this.id = id;
		this.nome = nome;
		this.idAreaConhecimento = idAreaConhecimento;
>>>>>>> 18d9c03 (refactor: generalizando dao pra simplificar o codigo e criação do crud inscrição)
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
<<<<<<< HEAD
		return "Curso [id=" + id + ", nome=" + nome + ", idAreaConhecimento=" + idAreaConhecimento + "]";
	}

=======
		return nome;
	}
>>>>>>> 18d9c03 (refactor: generalizando dao pra simplificar o codigo e criação do crud inscrição)
}
