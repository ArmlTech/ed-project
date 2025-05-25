package model.dto;

public class AreaConhecimento {
	private Integer id;
	private String nome;
	
	public AreaConhecimento(Integer areaID, String nome) {
		super();
		this.id = areaID;
		this.nome = nome;
	}
	public int getAreaID() {
		return id;
	}
	public void setAreaID(int areaID) {
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
	
	
	
}
