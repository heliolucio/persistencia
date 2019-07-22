package br.com.fiap.helper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.fiap.entity.Aluno;
import br.com.fiap.entity.AlunoCurso;
import br.com.fiap.entity.Curso;
import br.com.fiap.entity.Escola;

public class Helper {

	private EntityManager entityManager;

	public Helper(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	public List<Aluno> listarAlunos() {
		Query query = entityManager.createQuery("select a from Aluno a");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Curso> listarCursos() {
		Query query = entityManager.createQuery("select c from Curso c");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<AlunoCurso> listarAlunoCurso() {
		Query query = entityManager.createQuery("select ac from AlunoCurso ac");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Escola> listarEscola() {
		Query query = entityManager.createQuery("select e from Escola e");
		return query.getResultList();
	}

	public Aluno buscarAluno(int numMatricula) {
		Query query = entityManager
				.createQuery("select a from Aluno a where idAluno = :idAluno");
		query.setParameter("idAluno", numMatricula);
		Aluno aluno = (Aluno) query.getSingleResult();
		return aluno;
	}

	public Curso buscarCurso(int numCurso) {
		Query query = entityManager
				.createQuery("select c from Curso c where idCurso = :idCurso");
		query.setParameter("idCurso", numCurso);
		Curso curso = (Curso) query.getSingleResult();
		return curso;
	}

	public Escola buscarEscola(int codEscola) {
		Query query = entityManager
				.createQuery("select e from Escola e where idEscola =:idEscola");
		query.setParameter("idEscola", codEscola);
		Escola escola = (Escola) query.getSingleResult();
		return escola;
	}

	public void salvarAluno(Aluno aluno) throws Exception {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(aluno);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	public void salvarEscola(Escola escola) throws Exception {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(escola);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	public void salvarCurso(Curso curso) throws Exception {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(curso);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	public void salvarAlunoCursoNota(AlunoCurso alunoCurso) throws Exception {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(alunoCurso);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		} finally {
			entityManager.close();
		}
	}

}
