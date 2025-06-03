package model.dto;

public class AreaConhecimento implements IGenericEntity {
	private Integer id;
	private String nome;
	
	public AreaConhecimento(Integer areaID, String nome) {
		super();
		this.id = areaID;
		this.nome = nome;
	}
	public Integer getId() {
		return id;
	}
	public void setID(int areaID) {
		this.id = areaID;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public String toString() {
		return nome;
	}
	@Override
	public boolean equals(Object obj) { //override para combobox funcionar corretamente
		if(this == obj) return true; // se obj e essa instancia apontam pro mesmo objeto em memoria
		if(!(obj instanceof AreaConhecimento)) return false; // se o objeto não é da classe area
		AreaConhecimento area = (AreaConhecimento) obj; // fazendo casting do object para area
		return this.id.equals(area.id); //verificar se o id do obj casteado é igual ao id da instancia atual
	}
	
	//garantir que objetos iguais tenham o mesmo hashcode pra nao quebrar estruturas de dados
	//necessario ao sobsrcrever equals
	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}
