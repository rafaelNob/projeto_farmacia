package br.app.bean;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.faces.view.facelets.FaceletContext;
import javax.inject.Inject;

import br.app.dao.FornecedoresDao;
import br.app.domain.Fornecedores;
import br.app.service.FornecedoresService;

@ManagedBean(name = "MBFornecedores")
@ViewScoped
public class FornecedoresBean {

	private ListDataModel<Fornecedores> itens;
	private Fornecedores fornecedores;

	@Inject
	private FornecedoresService fornecedorService;

	@PostConstruct
	public void prepararPesquisa() {

		try {
			FornecedoresDao dao = new FornecedoresDao();
			List<Fornecedores> f = dao.listarTodos();
			itens = new ListDataModel<>(f);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ListDataModel<Fornecedores> getItens() {
		return itens;
	}

	public void setItens(ListDataModel<Fornecedores> itens) {
		this.itens = itens;
	}

	public Fornecedores getFornecedores() {
		if (fornecedores == null) {
			fornecedores = new Fornecedores();

		}

		return fornecedores;
	}

	public void setFornecedores(Fornecedores fornecedores) {
		this.fornecedores = fornecedores;
	}

	public void novo() {

		fornecedores = new Fornecedores();

	}

	public void salvar() {
		
		try {
			FornecedoresDao dao = new FornecedoresDao();
			dao.salvar(fornecedores);
			FornecedoresDao dao2 = new FornecedoresDao();

			List<Fornecedores> f = dao2.listarTodos();
			itens = new ListDataModel<Fornecedores>(f);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Arquivo Salvo com Sucesso!", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi Possivel Executar a Operação!", ""));
			e.printStackTrace();
		}

	}

	public void filtro() {

		this.fornecedorService.filtro(getFornecedores());

	}

	public void excluir(Long id) {
					
		try {
			
			FornecedoresDao dao = new FornecedoresDao();
			dao.excluir(fornecedores.getCodigo());
			List<Fornecedores> f = dao.listarTodos();
			itens = new ListDataModel<Fornecedores>(f);

			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluido com Sucesso!",""));
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi Possivel Executar a Operação!", ""));
		}

	}

	public void prepararExcluir() {
		fornecedores = itens.getRowData();
		System.out.println("Pegue a descrição: "+fornecedores.getDescricao());
	}
	
	public void teste(){
		FacesContext.getCurrentInstance().addMessage("",
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluido com Sucesso!",""));
	}

}
