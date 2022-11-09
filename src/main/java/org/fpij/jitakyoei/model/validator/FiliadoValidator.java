package org.fpij.jitakyoei.model.validator;

import java.util.Date;
import java.util.List;

import org.fpij.jitakyoei.model.beans.Endereco;
import org.fpij.jitakyoei.model.beans.Faixa;
import org.fpij.jitakyoei.model.beans.Filiado;
import org.fpij.jitakyoei.model.beans.Rg;

public class FiliadoValidator implements Validator<Filiado> {
    public boolean validate(Filiado obj) {
        Long filiadoId = obj.getId();
        String filiadoNome = obj.getNome();
        Date filiadoNascimento = obj.getDataNascimento();
        Date filiadoCadastro = obj.getDataCadastro();
        String filiadoTelefone1 = obj.getTelefone1();
        String filiadoTelefone2 = obj.getTelefone1();
        String filiadoEmail = obj.getEmail();
        String filiadoCpf = obj.getCpf();
        String filiadoObservacoes = obj.getObservacoes();
        Rg filiadoRg = obj.getRg();
        Endereco filiadoEndereco = obj.getEndereco();
        List<Faixa> filiadoFaixas = obj.getFaixas();

        if (filiadoId.toString().isBlank() ||
                filiadoNome.isBlank() ||
                filiadoNascimento == null || filiadoNascimento.toString().isBlank() ||
                filiadoCadastro == null || filiadoCadastro.toString().isBlank() ||
                filiadoTelefone1.isBlank() ||
                filiadoTelefone2.isBlank() ||
                filiadoEmail.isBlank() ||
                filiadoCpf.isBlank() ||
                filiadoObservacoes.isBlank() ||
                filiadoFaixas.isEmpty()) {
            return false;
        }
        return true;
    }
}
