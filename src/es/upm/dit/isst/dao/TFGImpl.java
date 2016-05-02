package es.upm.dit.isst.dao;

import java.util.List;

import javax.persistence.*;

import es.upm.dit.isst.model.TFG;

public class TFGImpl implements TFGDAO {
	
	private static TFGImpl instance;
	private TFGImpl () {}

	public static TFGImpl getInstance () {
		if (instance == null) {
			instance = new TFGImpl();
		}
		return instance;
	}
	
	
	@Override
	public TFG create(String autor, String titulo, String resumen, String tutor, String secretario, String fichero,
			int estado) {
		TFG tfg = null; 
		EntityManager em = EMFService.get().createEntityManager(); 
		em.getTransaction().begin();
		tfg = new TFG (autor, titulo, resumen, tutor, secretario, fichero, estado); 
		em.persist(tfg); 
		em.getTransaction().commit();
		em.close(); 
		return tfg; 
	}

	@Override
	public TFG readByAutor(String autor) {
		EntityManager em = EMFService.get().createEntityManager(); 
		Query q = em.createQuery("select t from TFG t where t.autor = :autor"); 
		q.setParameter("autor", autor); 
		TFG res =  null;
		List<TFG> tfgs = q.getResultList(); 
		if (tfgs.size() > 0) 
			res = (TFG) (q.getResultList().get(0)); 
		em.close(); 
		return res;
	}

	@Override
	public List<TFG> readByTutor(String tutor) {
		EntityManager em = EMFService.get().createEntityManager(); 
		Query q = em.createQuery("select t from TFG t where t.tutor = :tutor"); 
		q.setParameter("tutor", tutor);
		List<TFG> tfgs = q.getResultList();
		em.close(); 
		return tfgs;
	}

	@Override
	public List<TFG> readBySecretario(String secretario) {
		EntityManager em = EMFService.get().createEntityManager(); 
		Query q = em.createQuery("select t from TFG t where t.secretario = :secretario"); 
		q.setParameter("secretario", secretario);
		List<TFG> tfgs = q.getResultList();
		em.close(); 
		return tfgs;
	}

	@Override
	public List<TFG> readByEstado(int estado) {
		EntityManager em = EMFService.get().createEntityManager(); 
		Query q = em.createQuery("select t from TFG t where t.estado = :estado"); 
		q.setParameter("estado", estado);
		List<TFG> tfgs = q.getResultList(); 
		em.close(); 
		return tfgs;
	}

	@Override
	public List<TFG> readAll() {
		EntityManager em = EMFService.
		get().createEntityManager(); 
		Query q = em.createQuery("select m from TFG m"); 
		List<TFG> res = q.getResultList(); 
		em.close(); 
		return res; 
	}

	@Override
	public boolean update(TFG tfg) {
		EntityManager em = EMFService.get().createEntityManager();
	    TFG res = em.merge(tfg);
	    em.close();
	    return (tfg.equals(res));

	}

	@Override
	public boolean delete(String id) {
		boolean done = true;
		EntityManager em = EMFService.
		get().createEntityManager(); 
		try { 
			TFG todo = em.find(TFG.class, id); 
			em.remove(todo); 
		} catch(Exception e) {
			done = false;
		} finally { 
			em.close(); 
		} 
		return done;
	}


}
