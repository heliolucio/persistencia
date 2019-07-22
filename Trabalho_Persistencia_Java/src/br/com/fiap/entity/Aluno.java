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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_aluno")
public class Aluno implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idAluno;
	private String nomeAluno;
	private String enderecoAluno;
	private int telefoneAluno;
	private Set<Curso> cursos = new HashSet<>();
	private Set<AlunoCurso> alunoCurso = new HashSet<>();

	public Aluno() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_aluno")
	public int getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(int idAluno) {
		this.idAluno = idAluno;
	}

	@Column(name = "nome_aluno", nullable = false, length = 255)
	public String getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	@Column(name = "endereco_aluno", nullable = false, length = 255)
	public String getEnderecoAluno() {
		return enderecoAluno;
	}

	public void setEnderecoAluno(String enderecoAluno) {
		this.enderecoAluno = enderecoAluno;
	}

	@Column(name = "telefone_aluno", nullable = false)
	public int getTelefoneAluno() {
		return telefoneAluno;
	}

	public void setTelefoneAluno(int telefoneAluno) {
		this.telefoneAluno = telefoneAluno;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "tb_aluno_curso", joinColumns = { @JoinColumn(name = "fk_id_aluno", nullable = false, updatable = false) },
	inverseJoinColumns = { @JoinColumn(name = "fk_id_curso", nullable = false, updatable = false) })
	public Set<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(Set<Curso> cursos) {
		this.cursos = cursos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "aluno")
	public Set<AlunoCurso> getAlunoCurso() {
		return alunoCurso;
	}

	public void setAlunoCurso(Set<AlunoCurso> alunoCurso) {
		this.alunoCurso = alunoCurso;
	}

}
