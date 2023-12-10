package br.com.dacAtividade2.controller.exceptions;

public class BusinessException extends Exception {
	private static final long serialVersionUID = 1L;

	public BusinessException(String msg) {
		super(msg);
	}
}
