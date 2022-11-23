package org.fpij.jitakyoei.model.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.fpij.jitakyoei.model.beans.Aluno;
import org.fpij.jitakyoei.model.beans.Endereco;
import org.fpij.jitakyoei.model.beans.Entidade;
import org.fpij.jitakyoei.model.beans.Faixa;
import org.fpij.jitakyoei.model.beans.Filiado;
import org.fpij.jitakyoei.model.beans.Professor;
import org.fpij.jitakyoei.model.beans.Rg;
import org.fpij.jitakyoei.model.validator.AlunoValidator;
import org.fpij.jitakyoei.util.CorFaixa;
import org.fpij.jitakyoei.util.DatabaseManager;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class AlunoDaoTest {

	private static DAO<Aluno> alunoDao;
	private static Aluno aluno;
	private static Entidade entidade;
	private static Endereco endereco;
	private static Filiado f1;
	private static Rg rg;
	private static Filiado filiadoProf;
	private static Professor professor;
	private static AlunoValidator alunoValidator;
	private static Faixa faixa;

	@Before
	public void setUp() {
		DatabaseManager.setEnviroment(DatabaseManager.TEST);
		rg = new Rg();
		rg.setNumero("1234567");
		rg.setOrgaoExpedidor("ssp");

		faixa = new Faixa();
		faixa.setCor(CorFaixa.BRANCA);
		faixa.setDataEntrega(new Date());

		endereco = new Endereco();
		endereco.setBairro("Dirceu");
		endereco.setCep("64078-213");
		endereco.setCidade("Teresina");
		endereco.setEstado("PI");
		endereco.setRua("Rua Des. Berilo Mota");
		endereco.setNumero("100");

		f1 = new Filiado();
		f1.setNome("Aécio");
		f1.setCpf("036.464.453-27");
		f1.setDataNascimento(new Date());
		f1.setDataCadastro(new Date());
		f1.setId(1332L);
		f1.setRegistroCbj("registro 1");
		f1.setTelefone1("12345678");
		f1.setTelefone2("87654321");
		f1.setEmail("aecio@gmail.com");
		f1.setRg(rg);
		f1.setObservacoes("observacoes");
		List<Faixa> faixas = new ArrayList<Faixa>();
		faixas.add(faixa);
		f1.setFaixas(faixas);
		f1.setEndereco(endereco);

		filiadoProf = new Filiado();
		filiadoProf.setNome("Professor");
		filiadoProf.setCpf("036.464.453-27");
		filiadoProf.setDataNascimento(new Date());
		filiadoProf.setDataCadastro(new Date());
		filiadoProf.setId(3332L);
		filiadoProf.setEndereco(endereco);

		professor = new Professor();
		professor.setFiliado(filiadoProf);

		entidade = new Entidade();
		entidade.setEndereco(endereco);
		entidade.setCnpj("12345678910");
		entidade.setNome("Academia 1");
		entidade.setTelefone1("(086)1234-5432");
		entidade.setTelefone2("(086)1234-5432");

		aluno = new Aluno();
		aluno.setFiliado(f1);
		aluno.setProfessor(professor);
		aluno.setEntidade(entidade);

		alunoValidator = new AlunoValidator();
		alunoDao = new DAOImpl<Aluno>(Aluno.class, alunoValidator, true);
	}

	public static void clearDatabase() {
		List<Aluno> all = alunoDao.list();
		for (Aluno each : all) {
			alunoDao.delete(each);
		}
		assertEquals(0, alunoDao.list().size());
	}

	@Test
	public void testSalvarAlunoComAssociassoes() throws Exception {
		clearDatabase();

		alunoDao.save(aluno);
		assertEquals("036.464.453-27", alunoDao.get(aluno).getFiliado().getCpf());
		assertEquals("Aécio", alunoDao.get(aluno).getFiliado().getNome());
		assertEquals("Professor", alunoDao.get(aluno).getProfessor().getFiliado().getNome());
		assertEquals("Dirceu", alunoDao.get(aluno).getProfessor().getFiliado().getEndereco().getBairro());
	}

	@Test
	public void testSaveAlunoSuccess() throws Exception {
		clearDatabase();

		boolean isSaved = alunoDao.save(aluno);
		assertEquals(true, isSaved);
	}

	@Test
	public void testSaveAlunoFailed() throws Exception {
		clearDatabase();

		aluno.getEntidade().setCnpj("");
		boolean isSaved = alunoDao.save(aluno);
		assertEquals(false, isSaved);
	}

	@Test
	public void updateAluno() throws Exception {
		clearDatabase();
		assertEquals(0, alunoDao.list().size());

		alunoDao.save(aluno);
		assertEquals(1, alunoDao.list().size());
		assertEquals("Aécio", aluno.getFiliado().getNome());

		Aluno a1 = alunoDao.get(aluno);
		a1.getFiliado().setNome("TesteUpdate");
		alunoDao.save(a1);

		Aluno a2 = alunoDao.get(a1);
		assertEquals("TesteUpdate", a2.getFiliado().getNome());
		assertEquals(1, alunoDao.list().size());
	}

	@Test
	public void testListarEAdicionarAlunos() {
		int qtd = alunoDao.list().size();

		alunoDao.save(new Aluno());
		assertEquals(qtd + 1, alunoDao.list().size());

		alunoDao.save(new Aluno());
		assertEquals(qtd + 2, alunoDao.list().size());

		alunoDao.save(new Aluno());
		assertEquals(qtd + 3, alunoDao.list().size());

		alunoDao.save(new Aluno());
		assertEquals(qtd + 4, alunoDao.list().size());

		clearDatabase();
		assertEquals(0, alunoDao.list().size());

		alunoDao.save(new Aluno());
		assertEquals(1, alunoDao.list().size());
	}

	@Test
	public void testSearchAluno() throws Exception {
		clearDatabase();
		alunoDao.save(aluno);

		Filiado f = new Filiado();
		f.setNome("Aécio");
		Aluno a = new Aluno();
		a.setFiliado(f);

		List<Aluno> result = alunoDao.search(a);
		assertEquals(1, result.size());
		assertEquals("036.464.453-27", result.get(0).getFiliado().getCpf());

		clearDatabase();
		assertEquals(0, alunoDao.search(a).size());
	}

	@AfterClass
	public static void closeDatabase() {
		clearDatabase();
		DatabaseManager.close();
	}

}