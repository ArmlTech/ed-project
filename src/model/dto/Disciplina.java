package model.dto;

public class Disciplina implements IGenericEntity {
	private Integer id;
	private String nome;
	private String diaSemana;
	private String horaInicial;
	private Double qtdHoras;
	private Integer idCurso;
	public Disciplina(Integer id, String nome, String diaSemana, String horaInicial, Double qtdHoras, Integer idCurso) {
		this.id = id;
		this.nome = nome;
		this.diaSemana = diaSemana;
		this.horaInicial = horaInicial;
		this.qtdHoras = qtdHoras;
		this.idCurso = idCurso;
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
	public String getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}
	public String getHoraInicial() {
		return horaInicial;
	}
	public void setHoraInicial(String horaInicial) {
		this.horaInicial = horaInicial;
	}
	public Double getQtdHoras() {
		return qtdHoras;
	}
	public void setQtdHoras(Double qtdHoras) {
		this.qtdHoras = qtdHoras;
	}
	public Integer getIdCurso() {
		return idCurso;
	}
	public void setIdCurso(Integer idCurso) {
		this.idCurso = idCurso;
	}
	@Override
	public String toString() {
		return nome;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Disciplina)) return false;
		Disciplina disciplina = (Disciplina) obj;
		return id != null && id.equals(disciplina.id);
	}
	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	
	
}
