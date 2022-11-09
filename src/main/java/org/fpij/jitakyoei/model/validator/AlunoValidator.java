package org.fpij.jitakyoei.model.validator;

import org.fpij.jitakyoei.model.beans.Aluno;
import org.fpij.jitakyoei.model.beans.Entidade;
import org.fpij.jitakyoei.model.beans.Filiado;

public class AlunoValidator implements Validator<Aluno> {
	public boolean validate(Aluno obj) {
		Entidade entidade = obj.getEntidade();

		FiliadoValidator filiadoValidator = new FiliadoValidator();
		boolean isFiliadoValid = filiadoValidator.validate(obj.getFiliado());

		EntidadeValidator entidadeValidator = new EntidadeValidator();
		boolean isEntidadeValid = entidadeValidator.validate(obj.getEntidade());
		if (!isFiliadoValid ||
				!isEntidadeValid) {
			return false;
		}
		return true;
	}
}