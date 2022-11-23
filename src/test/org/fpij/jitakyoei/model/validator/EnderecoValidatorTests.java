package org.fpij.jitakyoei.model.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.fpij.jitakyoei.model.beans.Endereco;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class EnderecoValidatorTests {
    private static Endereco endereco;
    private static EnderecoValidator enderecoValidator;

    @BeforeAll
    private static void setUp() {
        endereco = new Endereco();
        endereco.setBairro("Dirceu");
        endereco.setCep("64078-213");
        endereco.setCidade("Teresina");
        endereco.setEstado("PI");
        endereco.setRua("Rua Des. Berilo Mota");
        endereco.setNumero("100");

        enderecoValidator = new EnderecoValidator();
    }

    @Test
    public void testEnderecoValidateSuccess() {
        boolean isValid = enderecoValidator.validate(endereco);
        assertTrue(isValid);
    }

    @Test
    public void testEnderecoValidateBairroBlank() {
        endereco.setBairro("");
        boolean isValid = enderecoValidator.validate(endereco);
        assertFalse(isValid);
    }

    @Test
    public void testEnderecoValidateCepBlank() {
        endereco.setCep("");
        boolean isValid = enderecoValidator.validate(endereco);
        assertFalse(isValid);
    }

    @Test
    public void testEnderecoValidateCidadeBlank() {
        endereco.setCidade("");
        boolean isValid = enderecoValidator.validate(endereco);
        assertFalse(isValid);
    }

    @Test
    public void testEnderecoValidateEstadoBlank() {
        endereco.setCep("");
        boolean isValid = enderecoValidator.validate(endereco);
        assertFalse(isValid);
    }

    @Test
    public void testEnderecoValidateRuaBlank() {
        endereco.setCep("");
        boolean isValid = enderecoValidator.validate(endereco);
        assertFalse(isValid);
    }

    @Test
    public void testEnderecoValidateNumeroBlank() {
        endereco.setCep("");
        boolean isValid = enderecoValidator.validate(endereco);
        assertFalse(isValid);
    }
}
