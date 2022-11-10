package org.fpij.jitakyoei.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.fpij.jitakyoei.facade.AppFacade;
import org.fpij.jitakyoei.model.beans.Aluno;
import org.fpij.jitakyoei.view.forms.AlunoForm;
import org.fpij.jitakyoei.view.gui.AlunoCadastrarPanel;

public class AlunoCadastrarView implements ViewComponent {

	private AlunoCadastrarPanel gui;
	private AlunoForm alunoForm;
	private AppFacade facade;
	private MainAppView parent;

	public AlunoCadastrarView(MainAppView parent) {
		this.parent = parent;
		gui = new AlunoCadastrarPanel();
		gui.getCadastrar().addActionListener(new CadastrarActionHandler());
		gui.getCancelar().addActionListener(new CancelarActionHandler());
		gui.setVisible(true);
		alunoForm = new AlunoForm(gui.getAlunoPanel());
	}

	@Override
	public JPanel getGui() {
		return gui;
	}

	@Override
	public void registerFacade(AppFacade fac) {
		this.facade = fac;
	}

	/**
	 * Classe interna responsável por responder aos cliques no botão "Cadastrar".
	 * 
	 * @author Aécio
	 */
	public class CadastrarActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Aluno aluno = alunoForm.getAluno();
			try {
				boolean isCreated = facade.createAluno(aluno);
				if (isCreated) {
					JOptionPane.showMessageDialog(gui, "Aluno cadastrado com sucesso!");
				} else {
					JOptionPane.showMessageDialog(gui, "Aluno não foi cadastrado!");
				}
				parent.removeTabPanel(gui);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Classe interna responsável por responder aos cliques no botão "Cancelar".
	 * 
	 * @author Aécio
	 */
	public class CancelarActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			parent.removeTabPanel(gui);
		}
	}
}
