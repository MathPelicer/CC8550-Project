package org.fpij.jitakyoei.model.validator;

import org.fpij.jitakyoei.model.beans.Entidade;

public class EntidadeValidator implements Validator<Entidade> {

    @Override
    public boolean validate(Entidade obj) {
        String entidadeNome = obj.getNome();
        String entidadeCnpj = obj.getCnpj();
        String entidadeTelefone1 = obj.getTelefone1();
        String entidadeTelefone2 = obj.getTelefone2();

        EnderecoValidator enderecoValidator = new EnderecoValidator();
        boolean isEnderecoValid = enderecoValidator.validate(obj.getEndereco());

        if (entidadeNome.isBlank() ||
                entidadeCnpj.isBlank() ||
                entidadeTelefone1.isBlank() ||
                entidadeTelefone2.isBlank() ||
                !isEnderecoValid) {
            return false;
        }

        return true;
    }

}
