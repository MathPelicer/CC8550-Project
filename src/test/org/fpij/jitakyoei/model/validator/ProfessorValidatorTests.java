package org.fpij.jitakyoei.model.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.fpij.jitakyoei.model.beans.Endereco;
import org.fpij.jitakyoei.model.beans.Entidade;
import org.fpij.jitakyoei.model.beans.Faixa;
import org.fpij.jitakyoei.model.beans.Filiado;
import org.fpij.jitakyoei.model.beans.Professor;
import org.fpij.jitakyoei.model.beans.Rg;
import org.fpij.jitakyoei.util.CorFaixa;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ProfessorValidatorTests {
    private static Entidade entidade;
    private static Endereco endereco;
    private static Rg rg;
    private static Faixa faixa;
    private static Filiado f1;
    private static Professor professor;

    private static ProfessorValidator professorValidator;

    @BeforeAll
    private static void setUp() {
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

        professorValidator = new ProfessorValidator();
    }

    @Test
    public void testProfessorValidatorSuccess() {
        boolean isValid = professorValidator.validate(professor);
        assertTrue(isValid);
    }

    @Test
    public void testProfessorValidatorProfessorEntidadesEmpty() {
        professor.getEntidades().clear();
        boolean isValid = professorValidator.validate(professor);
        assertFalse(isValid);
    }
}
