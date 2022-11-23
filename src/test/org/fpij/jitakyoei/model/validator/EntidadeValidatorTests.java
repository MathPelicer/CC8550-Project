package org.fpij.jitakyoei.model.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.fpij.jitakyoei.model.beans.Endereco;
import org.fpij.jitakyoei.model.beans.Entidade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class EntidadeValidatorTests {
    private static Entidade entidade;
    private static Endereco endereco;

    private static EntidadeValidator entidadeValidator;

    @BeforeAll
    private static void setUp() {
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
    }

    @Test
    public void testEntidadeValidatorSuccess() {
        boolean isValid = entidadeValidator.validate(entidade);
        assertTrue(isValid);
    }

    @Test
    public void testEntidadeValidatorCnpjBlanck() {
        entidade.setCnpj("");
        boolean isValid = entidadeValidator.validate(entidade);
        assertFalse(isValid);
    }

    @Test
    public void testEntidadeValidatorNomeBlanck() {
        entidade.setNome("");
        boolean isValid = entidadeValidator.validate(entidade);
        assertFalse(isValid);
    }

    @Test
    public void testEntidadeValidatorTelefone1Blanck() {
        entidade.setTelefone1("");
        boolean isValid = entidadeValidator.validate(entidade);
        assertFalse(isValid);
    }

    @Test
    public void testEntidadeValidatorTelefone2Blanck() {
        entidade.setTelefone2("");
        boolean isValid = entidadeValidator.validate(entidade);
        assertFalse(isValid);
    }
}
