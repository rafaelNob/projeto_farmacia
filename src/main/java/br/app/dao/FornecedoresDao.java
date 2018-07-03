package br.app.dao;

import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.app.connection.ConnectinFactory;
import br.app.domain.Fornecedores;

public class FornecedoresDao {

	private EntityManager em;

	public void salvar(Fornecedores fornecedores) {

		try {
			em = ConnectinFactory.getConnection();
			em.getTransaction().begin();
			em.persist(fornecedores);
			em.getTransaction().commit();

		} catch (Exception e) {
			System.out.println("Erro ao salvar Fornecedores: " + e.getMessage());
			em.getTransaction().rollback();
		} finally {
			em.close();
		}

	}

	public List<Fornecedores> listarTodos() throws SQLException {

		em = ConnectinFactory.getConnection();
		em.getTransaction().begin();
		Query query = em.createQuery("SELECT e FROM Fornecedores e order by e.codigo desc", Fornecedores.class);
		List<Fornecedores> fornecedores = query.getResultList();
		em.close();
		return fornecedores;

	}

//	public Object filtro(Fornecedores descricao) throws SQLException {
//
//		em = ConnectinFactory.getConnection();
//		em.getTransaction().begin();
//		Query query = em.createQuery("SELECT e FROM Fornecedores e Where e.descricao like% :descricao %",
//				Fornecedores.class);
//		query.setParameter("descricao", descricao.getDescricao());
//		em.close();
//
//		return query.getResultList();
//
//	}
	
	public void excluir(Long id) {
		
		try {
			em = ConnectinFactory.getConnection();
			em.getTransaction().begin();
			em.remove(em.getReference(Fornecedores.class, id));
			em.getTransaction().commit();
			

		} catch (Exception e) {
			System.out.println("Erro ao salvar Fornecedores: " + e.getMessage());
			em.getTransaction().rollback();
		} finally {
			em.close();
		}

	}

}
