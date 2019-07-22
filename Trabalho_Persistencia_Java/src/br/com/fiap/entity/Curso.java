package br.com.fiap.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_curso")
public class Curso implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idCurso;
	private String nomeCurso;
	private int cargaHoraria;
	private Escola escola;
	private Set<Aluno> alunos = new HashSet<>();
	private Set<AlunoCurso> alunoCurso = new HashSet<>();

	public Curso() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_curso")
	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}

	@Column(name = "nome_curso", nullable = false, length = 255)
	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	@Column(name = "carga_horaria", nullable = false)
	public int getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_id_escola")
	public Escola getEscola() {
		return escola;
	}

	public void setEscola(Escola escola) {
		this.escola = escola;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy="cursos")
	public Set<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(Set<Aluno> alunos) {
		this.alunos = alunos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "curso")
	public Set<AlunoCurso> getAlunoCurso() {
		return alunoCurso;
	}

	public void setAlunoCurso(Set<AlunoCurso> alunoCurso) {
		this.alunoCurso = alunoCurso;
	}

}
