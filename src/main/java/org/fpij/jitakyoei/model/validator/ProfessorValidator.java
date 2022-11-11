package org.fpij.jitakyoei.model.validator;

import java.util.List;

import org.fpij.jitakyoei.model.beans.Entidade;
import org.fpij.jitakyoei.model.beans.Professor;

public class ProfessorValidator implements Validator<Professor> {

    @Override
    public boolean validate(Professor obj) {
        FiliadoValidator filiadoValidator = new FiliadoValidator();
        boolean isFiliadoValid = filiadoValidator.validate(obj.getFiliado());

        List<Entidade> professorEntidades = obj.getEntidades();

        if (!isFiliadoValid ||
                professorEntidades.isEmpty()) {
            return false;
        }

        return true;
    }

}
