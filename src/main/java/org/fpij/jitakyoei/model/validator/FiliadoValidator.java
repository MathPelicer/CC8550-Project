package org.fpij.jitakyoei.model.validator;

import java.util.Date;
import java.util.List;

import org.fpij.jitakyoei.model.beans.Faixa;
import org.fpij.jitakyoei.model.beans.Filiado;

public class FiliadoValidator implements Validator<Filiado> {
    public boolean validate(Filiado obj) {
        Long filiadoId = obj.getId();
        String filiadoNome = obj.getNome();
        Date filiadoNascimento = obj.getDataNascimento();
        Date filiadoCadastro = obj.getDataCadastro(); // its null
        String filiadoTelefone1 = obj.getTelefone1();
        String filiadoTelefone2 = obj.getTelefone2();
        String filiadoEmail = obj.getEmail();
        String filiadoCpf = obj.getCpf();
        String filiadoObservacoes = obj.getObservacoes();
        List<Faixa> filiadoFaixas = obj.getFaixas();

        RgValidator rgValidator = new RgValidator();
        boolean isRgValid = rgValidator.validate(obj.getRg());

        EnderecoValidator enderecoValidator = new EnderecoValidator();
        boolean isEnderecoValid = enderecoValidator.validate(obj.getEndereco());

        if (filiadoId.toString().isBlank() ||
                filiadoNome.isBlank() ||
                filiadoNascimento == null || filiadoNascimento.toString().isBlank() ||
                filiadoCadastro == null || filiadoCadastro.toString().isBlank() ||
                filiadoTelefone1.isBlank() ||
                filiadoTelefone2.isBlank() ||
                filiadoEmail.isBlank() ||
                filiadoCpf.isBlank() ||
                filiadoObservacoes.isBlank() ||
                filiadoFaixas.isEmpty() ||
                !isRgValid ||
                !isEnderecoValid) {
            return false;
        }
        return true;
    }
}
