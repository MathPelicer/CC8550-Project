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
import org.fpij.jitakyoei.model.validator.ProfessorValidator;
import org.fpij.jitakyoei.util.CorFaixa;
import org.fpij.jitakyoei.util.DatabaseManager;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class ProfessorDaoTest {
    private static DAO<Professor> professorDao;
    private static Aluno aluno;
    private static Entidade entidade;
    private static Endereco endereco;
    private static Filiado f1;
    private static Rg rg;
    private static Filiado filiadoProf;
    private static Professor professor;
    private static ProfessorValidator professorValidator;
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

        entidade = new Entidade();
        entidade.setEndereco(endereco);
        entidade.setCnpj("12345678910");
        entidade.setNome("Academia 1");
        entidade.setTelefone1("(086)1234-5432");
        entidade.setTelefone2("(086)1234-5432");

        professor = new Professor();
        professor.setFiliado(f1);
        List<Entidade> entidades = new ArrayList<Entidade>();
        entidades.add(entidade);
        professor.setEntidades(entidades);

        aluno = new Aluno();
        aluno.setFiliado(f1);
        aluno.setProfessor(professor);
        aluno.setEntidade(entidade);

        professorValidator = new ProfessorValidator();
        professorDao = new DAOImpl<Professor>(Professor.class, professorValidator, true);
    }

    public static void clearDatabase() {
        List<Professor> all = professorDao.list();
        for (Professor each : all) {
            professorDao.delete(each);
        }
        assertEquals(0, professorDao.list().size());
    }

    @Test
    public void testSalvarProfessorComAssociassoes() throws Exception {
        clearDatabase();

        professorDao.save(professor);
        assertEquals("036.464.453-27", professorDao.get(professor).getFiliado().getCpf());
        assertEquals("Aécio", professorDao.get(professor).getFiliado().getNome());
        assertEquals("Academia 1", professorDao.get(professor).getEntidades().get(0).getNome());
    }

    @Test
    public void testSaveProfessorSuccess() throws Exception {
        clearDatabase();

        boolean isSaved = professorDao.save(professor);
        assertEquals(true, isSaved);
    }

    @Test
    public void testSaveProfessorNoName() throws Exception {
        clearDatabase();

        professor.getFiliado().setNome("");
        boolean isSaved = professorDao.save(professor);

        assertEquals(false, isSaved);
    }

    @Test
    public void updateProfessor() throws Exception {
        clearDatabase();
        assertEquals(0, professorDao.list().size());

        professorDao.save(professor);
        assertEquals(1, professorDao.list().size());
        assertEquals("Aécio", professor.getFiliado().getNome());

        Professor a1 = professorDao.get(professor);
        a1.getFiliado().setNome("TesteUpdate");
        professorDao.save(a1);

        Professor a2 = professorDao.get(a1);
        assertEquals("TesteUpdate", a2.getFiliado().getNome());
        assertEquals(1, professorDao.list().size());
    }

    @Test
    public void testListarEAdicionarProfessor() {
        int qtd = professorDao.list().size();

        professorDao.save(new Professor());
        assertEquals(qtd + 1, professorDao.list().size());

        professorDao.save(new Professor());
        assertEquals(qtd + 2, professorDao.list().size());

        professorDao.save(new Professor());
        assertEquals(qtd + 3, professorDao.list().size());

        professorDao.save(new Professor());
        assertEquals(qtd + 4, professorDao.list().size());

        clearDatabase();
        assertEquals(0, professorDao.list().size());

        professorDao.save(new Professor());
        assertEquals(1, professorDao.list().size());
    }

    @Test
    public void testSearchProfessor() throws Exception {
        clearDatabase();
        professorDao.save(professor);

        Filiado f = new Filiado();
        f.setNome("Aécio");
        Professor a = new Professor();
        a.setFiliado(f);

        List<Professor> result = professorDao.search(a);
        assertEquals(1, result.size());
        assertEquals("036.464.453-27", result.get(0).getFiliado().getCpf());

        clearDatabase();
        assertEquals(0, professorDao.search(a).size());
    }

    @AfterClass
    public static void closeDatabase() {
        clearDatabase();
        DatabaseManager.close();
    }
}
