package org.fpij.jitakyoei.model.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.fpij.jitakyoei.model.beans.Endereco;
import org.fpij.jitakyoei.model.beans.Faixa;
import org.fpij.jitakyoei.model.beans.Filiado;
import org.fpij.jitakyoei.model.beans.Rg;
import org.fpij.jitakyoei.util.CorFaixa;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class FiliadoValidatorTests {
    private static Endereco endereco;
    private static Rg rg;
    private static Faixa faixa;
    private static Filiado f1;
    private static FiliadoValidator filiadoValidator;

    @BeforeAll
    public static void setUp() {
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

        filiadoValidator = new FiliadoValidator();
    }

    @Test
    public void testFiliadoValidatorSuccess() throws Exception {

        boolean isValid = filiadoValidator.validate(f1);
        assertTrue(isValid);
    }

    @Test
    public void testFiliadoValidatorRgNumeroBlank() throws Exception {
        rg.setNumero("");
        f1.setRg(rg);
        boolean isValid = filiadoValidator.validate(f1);
        assertFalse(isValid);
    }

    @Test
    public void testFiliadoValidatorRgOrgaoBlank() throws Exception {
        rg.setOrgaoExpedidor("");
        f1.setRg(rg);
        boolean isValid = filiadoValidator.validate(f1);
        assertFalse(isValid);
    }

    @Test
    public void testFiliadoValidatorFaixaEmpty() throws Exception {
        f1.getFaixas().clear();

        boolean isValid = filiadoValidator.validate(f1);
        assertFalse(isValid);
    }

    @Test
    public void testFiliadoValidatorNomeBlank() throws Exception {
        f1.setNome("");
        boolean isValid = filiadoValidator.validate(f1);
        assertFalse(isValid);
    }

    @Test
    public void testFiliadoValidatorDataNascimentoNull() throws Exception {
        f1.setDataNascimento(null);
        boolean isValid = filiadoValidator.validate(f1);
        assertFalse(isValid);
    }

    // @Test
    // public void testFiliadoValidatorDataNascimentoBlank() throws Exception {
    // f1.setDataNascimento("");
    // boolean isValid = filiadoValidator.validate(f1);
    // assertFalse(isValid);
    // }

    @Test
    public void testFiliadoValidatorDataCadastroNull() throws Exception {
        f1.setDataCadastro(null);
        boolean isValid = filiadoValidator.validate(f1);
        assertFalse(isValid);
    }

    @Test
    public void testFiliadoValidatorTelefone1Blank() throws Exception {
        f1.setTelefone1("");
        boolean isValid = filiadoValidator.validate(f1);
        assertFalse(isValid);
    }

    @Test
    public void testFiliadoValidatorTelefone2Blank() throws Exception {
        f1.setTelefone2("");
        boolean isValid = filiadoValidator.validate(f1);
        assertFalse(isValid);
    }

    @Test
    public void testFiliadoValidatorEmailBlank() throws Exception {
        f1.setEmail("");
        boolean isValid = filiadoValidator.validate(f1);
        assertFalse(isValid);
    }

    @Test
    public void testFiliadoValidatorCpfBlank() throws Exception {
        f1.setCpf("");
        boolean isValid = filiadoValidator.validate(f1);
        assertFalse(isValid);
    }

    @Test
    public void testFiliadoValidatorObservacoesBlank() throws Exception {
        f1.setObservacoes("");
        boolean isValid = filiadoValidator.validate(f1);
        assertFalse(isValid);
    }

}
