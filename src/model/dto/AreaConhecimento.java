package model.dto;

public class AreaConhecimento {
	private int areaID;
	private String nome;
	
	
	
	public AreaConhecimento(int areaID, String nome) {
		super();
		this.areaID = areaID;
		this.nome = nome;
	}
	public int getAreaID() {
		return areaID;
	}
	public void setAreaID(int areaID) {
		this.areaID = areaID;
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
