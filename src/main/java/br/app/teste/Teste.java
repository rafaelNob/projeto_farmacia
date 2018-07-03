package br.app.teste;

import br.app.dao.FornecedoresDao;


public class Teste {
	
	private FornecedoresDao dao;
	
		
	public static void main(String[] args) {
		FornecedoresDao dao = new FornecedoresDao();
		try {
			//dao.excluir(1L);
			System.out.println("Excluido com sucesso!!");
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	}
	
}
