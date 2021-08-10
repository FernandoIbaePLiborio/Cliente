package io.github.profile.dao.exeption;

public class CpfCadastradoException extends BusinessException{

	private static final long serialVersionUID = 2378571817025142871L;

	public CpfCadastradoException() {

		super("CPF já cadastrado!");
	}

}
