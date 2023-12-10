package br.com.dacAtividade2.model.utils;

import javax.persistence.Persistence;

public class CriarTabela {

	public static void main(String[] args) {
		Persistence.createEntityManagerFactory("dacAtividade2");
	}
}
