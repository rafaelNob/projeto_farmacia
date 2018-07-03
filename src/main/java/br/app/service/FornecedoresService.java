package br.app.service;

import javax.inject.Inject;

import br.app.dao.FornecedoresDao;
import br.app.domain.Fornecedores;

public class FornecedoresService {

	
	@Inject
	private FornecedoresDao fornecedoresDao;
	
	
	public void salvar(Fornecedores fornecedores){
		this.fornecedoresDao.salvar(fornecedores);
	}


	public void filtro(Fornecedores descricao) {
		try {
			//this.fornecedoresDao.filtro(descricao);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
}
