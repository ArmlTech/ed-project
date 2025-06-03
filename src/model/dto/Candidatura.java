package model.dto;

import java.time.LocalDate;

public class Candidatura implements IGenericEntity {
	private Integer id;
	private Integer idProcesso;
	private Integer idProfessor;
	private LocalDate data;

	public Candidatura(Integer id, Integer idProcesso, Integer idProfessor, LocalDate data) {
		this.id = id;
		this.idProcesso = idProcesso;
		this.idProfessor = idProfessor;
		this.data = data;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdProcesso() {
		return idProcesso;
	}

	public void setIdProcesso(Integer idProcesso) {
		this.idProcesso = idProcesso;
	}

	public Integer getIdProfessor() {
		return idProfessor;
	}

	public void setIdProfessor(Integer idProfessor) {
		this.idProfessor = idProfessor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getDataFormatada() {
		return data.format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	}

	@Override
	public String toString() {
		return "Candidatura{" +
				"id=" + id +
				", idProcesso=" + idProcesso +
				", idProfessor=" + idProfessor +
				", data=" + getDataFormatada() +
				'}';
	}
}
