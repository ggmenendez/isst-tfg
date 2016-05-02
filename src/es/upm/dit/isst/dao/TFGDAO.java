package es.upm.dit.isst.dao;

import java.util.List;

import es.upm.dit.isst.model.TFG;

public interface TFGDAO {
	
	public TFG create (String autor, String titulo, String resumen, String tutor, String secretario, String fichero, int estado);
	
	public TFG readByAutor (String autor);
	public List<TFG> readByTutor (String tutor);
	public List<TFG> readBySecretario (String secretario);
	public List<TFG> readByEstado (int estado);
	public List<TFG> readAll ();
	
	public boolean update (TFG tfg);
	
	public boolean delete (String id);

}
