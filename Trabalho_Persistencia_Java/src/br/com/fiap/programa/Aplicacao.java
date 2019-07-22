package br.com.fiap.programa;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.fiap.entity.Aluno;
import br.com.fiap.entity.AlunoCurso;
import br.com.fiap.entity.AlunoCursoPK;
import br.com.fiap.entity.Curso;
import br.com.fiap.entity.Escola;
import br.com.fiap.helper.Helper;

public class Aplicacao {

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Scanner entrada = new Scanner(System.in);
		int opcao;
		int idAluno = 0;
		int idCurso = 0;
		System.out.println("Escolha uma das opções abaixo:\n"
				+ "1 - Listar Todos os alunos.\n"
				+ "2 - Listar todos os cursos.\n"
				+ "3 - Listar todos os alunos e cursos matriculados.\n"
				+ "4 - Incluir Aluno.\n"
				+ "5 - Incluir Curso.\n"
				+ "6 - Buscar Aluno.\n"
				+ "7 - Buscar Curso.\n"
				+ "8 - Incluir Nota Aluno.\n"
				+ "0 - Finalizar.\n");

		opcao = entrada.nextInt();
		while (opcao != 0) {
			switch (opcao) {
			case 1:
				listarAlunos(entityManager);
				break;
			case 2:
				listarCursos(entityManager);
				break;
			case 3:
				listarAlunoCurso(entityManager);
				break;
			case 4:
				incluirAluno(entityManager);
				break;
			case 5:
				incluirCurso(entityManager);
				break;
			case 6:
				buscarAluno(entityManager, idAluno);
				break;
			case 7:
				buscarCurso(entityManager, idCurso);
				break;
			case 8:
				incluirAlunoCursoNota(entityManager);
				break;
			default:
				System.out.println("Opção inválida, favor selecionar umas das opções válidas.\n");
			}
			System.out.println("\nEscolha uma das opções abaixo:\n"
					+ "1 - Listar Todos os alunos.\n"
					+ "2 - Listar todos os cursos.\n"
					+ "3 - Listar todos os alunos e cursos matriculados.\n"
					+ "4 - Incluir Aluno.\n" 
					+ "5 - Incluir Curso.\n"
					+ "6 - Buscar Aluno.\n" 
					+ "7 - Buscar Curso.\n"
					+ "8 - Incluir Nota Aluno.\n"
					+ "0 - Finalizar.\n");
			opcao = entrada.nextInt();

		}
		System.out.println("Operação de consulta finalizado!!!!");
		entityManager.close();
	}

	private static void listarAlunos(EntityManager entityManager) {
		Helper dao = new Helper(entityManager);
		List<Aluno> alunos = dao.listarAlunos();
		for (Aluno aluno : alunos) {
			System.out.println("Matrícula: " + aluno.getIdAluno() + " Nome: "
					+ aluno.getNomeAluno() + "  Endereço: "
					+ aluno.getEnderecoAluno() + " Telefone: "
					+ aluno.getTelefoneAluno() + "\n");
		}
	}

	private static void listarCursos(EntityManager entityManager) {
		Helper dao = new Helper(entityManager);
		List<Curso> cursos = dao.listarCursos();
		for (Curso curso : cursos) {
			System.out.println("Curso: " + curso.getNomeCurso()
					+ " Carga Horária: " + curso.getCargaHoraria() + "\n");
		}
	}

	private static void listarAlunoCurso(EntityManager entityManager) {
		Helper dao = new Helper(entityManager);
		List<AlunoCurso> alunoCursos = dao.listarAlunoCurso();
		for (AlunoCurso alunoCurso : alunoCursos) {
			System.out.println("Matrícula Aluno: "
					+ alunoCurso.getAlunoCursoPk().getFkIdAluno()
					+ " Nome Aluno: " + alunoCurso.getAluno().getNomeAluno()
					+ " Curso: " + alunoCurso.getCurso().getNomeCurso() 
					+ " Nota: " + alunoCurso.getNota() + "\n");
		}
	}

	private static void buscarAluno(EntityManager entityManager, int idAluno) {
		Helper dao = new Helper(entityManager);
		Scanner buscarAluno = new Scanner(System.in);
		System.out.println("Informe a matrícula do aluno: ");
		String matricula = buscarAluno.nextLine();
		idAluno = Integer.parseInt(matricula);
		Aluno aluno = dao.buscarAluno(idAluno);
		System.out.println("Matrícula: " + aluno.getIdAluno() + " Nome: "
				+ aluno.getNomeAluno() + " Endereço: "
				+ aluno.getEnderecoAluno() + " Telefone: ");
	}

	private static void buscarCurso(EntityManager entityManager, int idCurso) {
		Helper dao = new Helper(entityManager);
		Scanner buscarCurso = new Scanner(System.in);
		System.out.println("Informe o código do curso: ");
		String curso = buscarCurso.nextLine();
		idCurso = Integer.parseInt(curso);
		Curso cursos = dao.buscarCurso(idCurso);
		System.out.println("Curso: " + cursos.getNomeCurso()
				+ " Carga Horária: " + cursos.getCargaHoraria());
	}

	
	private static void incluirAluno(EntityManager entityManager) {
		Helper dao = new Helper(entityManager);
		try {
			Scanner entradaAluno = new Scanner(System.in);
			System.out.println("Informe o nome do aluno: ");
			String nome = entradaAluno.nextLine();
			System.out.println("Informe endereço do aluno: ");
			String endereco = entradaAluno.nextLine();
			System.out.println("Informe telefone do aluno: ");
			String telefone = entradaAluno.nextLine();
			int telefoneAluno = Integer.parseInt(telefone);

			Aluno aluno = new Aluno();
			aluno.setNomeAluno(nome);
			aluno.setEnderecoAluno(endereco);
			aluno.setTelefoneAluno(telefoneAluno);

			dao.salvarAluno(aluno);
			System.out.println("Aluno incluído com sucesso.");
		} catch (Exception e) {
			System.out.println("Erro na inclusão do aluno: " + e.getMessage());
		}
	}

	private static void incluirCurso(EntityManager entityManager) {
		Helper dao = new Helper(entityManager);
		try {
			Scanner entradaEscola = new Scanner(System.in);
			System.out.println("Informe o código da Escola a qual pertence o curso: ");
			String escola = entradaEscola.nextLine();
			int idEscola = Integer.parseInt(escola);

			Escola escolas = dao.buscarEscola(idEscola);

			Scanner entradaCurso = new Scanner(System.in);
			System.out.println("Informe nome do Curso: ");
			String nomeCurso = entradaCurso.nextLine();
			System.out.println("Informa a carga horária do curso: ");
			String cargaHoraria = entradaCurso.nextLine();
			int cargHoraria = Integer.parseInt(cargaHoraria);

			Escola novaEscola = new Escola();
			novaEscola.setIdEscola(escolas.getIdEscola());
			novaEscola.setNomeEscola(escolas.getNomeEscola());
			novaEscola.setEnderecoEscola(escolas.getEnderecoEscola());

			Curso curso = new Curso();
			curso.setEscola(novaEscola);
			curso.setNomeCurso(nomeCurso);
			curso.setCargaHoraria(cargHoraria);

			novaEscola.getCursos().add(curso);

			dao.salvarCurso(curso);
			System.out.println("Curso incluído com sucesso.");
		} catch (Exception e) {
			System.out.println("Erro na inclusão do curso: " + e.getMessage());
		}

	}

	public static void incluirAlunoCursoNota(EntityManager entityManager) {
		Helper dao = new Helper(entityManager);
		try {
			Scanner entradaAluno = new Scanner(System.in);
			System.out.println("Informe a matrícula do aluno: ");
			String aluno = entradaAluno.nextLine();
			int idAluno = Integer.parseInt(aluno);

			Aluno alunos = dao.buscarAluno(idAluno);

			System.out.println("Informe código do curso na qual o aluno está matriculado: ");
			String curso = entradaAluno.nextLine();
			int idCurso = Integer.parseInt(curso);

			Curso cursos = dao.buscarCurso(idCurso);

			System.out.println("Informe a nota do aluno "
					+ alunos.getNomeAluno() + " matriculado no curso "
					+ cursos.getNomeCurso() + ": ");
			String notas = entradaAluno.nextLine();
			int nota = Integer.parseInt(notas);
			
			if(nota < 0 || nota > 10){
				System.out.println("Nota inválida!!!\nFavor informar nota de 0 a 10!!!");
			}else{
				AlunoCursoPK alunoCursoPk = new AlunoCursoPK();
				alunoCursoPk.setFkIdAluno(alunos.getIdAluno());
				alunoCursoPk.setFkIdCurso(cursos.getIdCurso());
				
				AlunoCurso alunoCurso = new AlunoCurso();
				alunoCurso.setAlunoCursoPk(alunoCursoPk);
				alunoCurso.setNota(nota);
				
				dao.salvarAlunoCursoNota(alunoCurso);
				System.out.println("Nota do aluno incluido com sucesso.");				
			}
		} catch (Exception e) {
			System.out.println("Erro na inclusão da nota do aluno: " + e.getMessage());
		}

	}

}
