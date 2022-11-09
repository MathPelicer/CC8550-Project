package org.fpij.jitakyoei.model.validator;

import org.fpij.jitakyoei.model.beans.Rg;

public class RgValidator implements Validator<Rg> {

    @Override
    public boolean validate(Rg obj) {
        String RgNumero = obj.getNumero();
        String RgOrgaoExpedidor = obj.getOrgaoExpedidor();

        if (RgNumero.isBlank() ||
                RgOrgaoExpedidor.isBlank()) {
            return false;
        }

        return true;
    }

}
