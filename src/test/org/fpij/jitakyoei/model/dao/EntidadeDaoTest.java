package org.fpij.jitakyoei.model.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.fpij.jitakyoei.model.beans.Endereco;
import org.fpij.jitakyoei.model.beans.Entidade;
import org.fpij.jitakyoei.model.validator.EntidadeValidator;
import org.fpij.jitakyoei.util.DatabaseManager;
import org.junit.AfterClass;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class EntidadeDaoTest {
    private static DAO<Entidade> entidadeDao;
    private static Entidade entidade;
    private static Endereco endereco;
    private static EntidadeValidator entidadeValidator;

    @BeforeAll
    public static void setUp() {
        DatabaseManager.setEnviroment(DatabaseManager.TEST);

        endereco = new Endereco();
        endereco.setBairro("Dirceu");
        endereco.setCep("64078-213");
        endereco.setCidade("Teresina");
        endereco.setEstado("PI");
        endereco.setRua("Rua Des. Berilo Mota");
        endereco.setNumero("100");

        entidade = new Entidade();
        entidade.setEndereco(endereco);
        entidade.setCnpj("12345678910");
        entidade.setNome("Academia 1");
        entidade.setTelefone1("(086)1234-5432");
        entidade.setTelefone2("(086)1234-5432");

        entidadeValidator = new EntidadeValidator();
        entidadeDao = new DAOImpl<Entidade>(Entidade.class, entidadeValidator, true);
    }

    public static void clearDatabase() {
        List<Entidade> all = entidadeDao.list();
        for (Entidade each : all) {
            entidadeDao.delete(each);
        }
        assertEquals(0, entidadeDao.list().size());
    }

    @Test
    public void testSaveEntidadeSuccess() throws Exception {
        clearDatabase();

        boolean isSaved = entidadeDao.save(entidade);
        assertEquals(true, isSaved);
    }

    @Test
    public void testSaveEntidadeNoCnpj() throws Exception {
        clearDatabase();

        entidade.setCnpj("");
        boolean isSaved = entidadeDao.save(entidade);

        assertEquals(false, isSaved);
    }

    @Test
    public void updateProfessor() throws Exception {
        clearDatabase();
        assertEquals(0, entidadeDao.list().size());

        entidadeDao.save(entidade);
        assertEquals(1, entidadeDao.list().size());
        assertEquals("12345678910", entidade.getCnpj());

        Entidade a1 = entidadeDao.get(entidade);
        a1.setNome("TesteUpdate");
        entidadeDao.save(a1);

        Entidade a2 = entidadeDao.get(a1);
        assertEquals("TesteUpdate", a2.getNome());
        assertEquals(1, entidadeDao.list().size());
    }

    /*
     * @Test
     * public void testListarEAdicionarProfessor() {
     * int qtd = professorDao.list().size();
     * 
     * professorDao.save(new Professor());
     * assertEquals(qtd + 1, professorDao.list().size());
     * 
     * professorDao.save(new Professor());
     * assertEquals(qtd + 2, professorDao.list().size());
     * 
     * professorDao.save(new Professor());
     * assertEquals(qtd + 3, professorDao.list().size());
     * 
     * professorDao.save(new Professor());
     * assertEquals(qtd + 4, professorDao.list().size());
     * 
     * clearDatabase();
     * assertEquals(0, professorDao.list().size());
     * 
     * professorDao.save(new Professor());
     * assertEquals(1, professorDao.list().size());
     * }
     */

    @Test
    public void testSearchEntidade() throws Exception {
        clearDatabase();
        entidadeDao.save(entidade);

        Entidade a = new Entidade();
        a.setNome("Academia 1");

        List<Entidade> result = entidadeDao.search(a);
        assertEquals(1, result.size());
        assertEquals("12345678910", result.get(0).getCnpj());

        clearDatabase();
        assertEquals(0, entidadeDao.search(a).size());
    }

    @AfterClass
    public static void closeDatabase() {
        clearDatabase();
        DatabaseManager.close();
    }
}
