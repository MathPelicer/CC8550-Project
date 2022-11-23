package org.fpij.jitakyoei.model.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.fpij.jitakyoei.model.beans.Rg;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class RgValidatorTests {
    private static Rg rg;
    private static RgValidator rgValidator;

    @BeforeAll
    public static void setUp() {
        rg = new Rg();
        rg.setNumero("1234567");
        rg.setOrgaoExpedidor("ssp");

        rgValidator = new RgValidator();
    }

    @Test
    public void testRgValidateSuccess() {
        boolean isValid = rgValidator.validate(rg);
        assertTrue(isValid);
    }

    @Test
    public void testRgValidateNumeroBlank() {
        rg.setNumero("");
        boolean isValid = rgValidator.validate(rg);
        assertFalse(isValid);
    }

    @Test
    public void testRgValidateOrgaoExpedidorBlank() {
        rg.setOrgaoExpedidor("");
        boolean isValid = rgValidator.validate(rg);
        assertFalse(isValid);
    }
}
