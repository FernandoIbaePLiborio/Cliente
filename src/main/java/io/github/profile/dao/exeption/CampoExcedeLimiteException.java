package io.github.profile.dao.exeption;

public class CampoExcedeLimiteException extends BusinessException{

	private static final long serialVersionUID = 2378571817025142871L;

	public CampoExcedeLimiteException() {

		super("Excede o limite de caracteres para campo");
	}

}
