package io.github.profile.dao.exeption;

public class CampoNaoInformadoException extends BusinessException{

	private static final long serialVersionUID = 2378571817025142871L;

	public CampoNaoInformadoException() {

		super("O campo CPF não pode estar em branco.");
	}

}
