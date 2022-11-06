package org.fpij.jitakyoei.model.validator;

import org.fpij.jitakyoei.model.beans.Aluno;
import org.fpij.jitakyoei.model.beans.Filiado;

public class AlunoValidator implements Validator<Aluno> {
	public boolean validate(Aluno obj) {
		Filiado filiado = obj.getFiliado();
		if (filiado.getCpf() == null || filiado.getCpf() == "") {
			return false;
		}
		return true;
	}
}