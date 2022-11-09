package org.fpij.jitakyoei.model.validator;

import org.fpij.jitakyoei.model.beans.Aluno;
import org.fpij.jitakyoei.model.beans.Filiado;

public class AlunoValidator implements Validator<Aluno> {
	public boolean validate(Aluno obj) {
		Filiado filiado = obj.getFiliado();

		FiliadoValidator filiadoValidator = new FiliadoValidator();
		boolean isFiliadoValid = filiadoValidator.validate(filiado);
		if (!isFiliadoValid) {
			return false;
		}
		return true;
	}
}