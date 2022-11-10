package org.fpij.jitakyoei.model.validator;

import org.fpij.jitakyoei.model.beans.Endereco;

public class EnderecoValidator implements Validator<Endereco> {

    @Override
    public boolean validate(Endereco obj) {
        String enderecoRua = obj.getRua();
        String enderecoNumero = obj.getNumero();
        String enderecoBairro = obj.getBairro();
        String enderecoCidade = obj.getCidade();
        String enderecoEstado = obj.getEstado();
        String enderecoCep = obj.getCep();

        if (enderecoRua.isBlank() ||
                enderecoNumero.isBlank() ||
                enderecoBairro.isBlank() ||
                enderecoCidade.isBlank() ||
                enderecoEstado.isBlank() ||
                enderecoCep.isBlank()) {
            return false;
        }

        return true;
    }

}
