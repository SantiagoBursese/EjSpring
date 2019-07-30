package ar.com.grupoesfera.piopio.repo;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import ar.com.grupoesfera.main.App;
import ar.com.grupoesfera.piopio.modelo.Pio;
import ar.com.grupoesfera.piopio.modelo.Pio_;
import ar.com.grupoesfera.piopio.modelo.Usuario;

public class BaseDePios {
    
	@SuppressWarnings("unchecked")
	public List<Pio> obtenerLista() {
		return App.instancia()
				.obtenerSesion()
				.createQuery("FROM Pio")
				.getResultList();
	}
	
	public Pio obtenerPioPorId(Long id) {
		return (Pio) App.instancia()
				.obtenerSesion()
				.createQuery("from Pio p where p.Id = :Id")
				.setParameter("Id", "id")
				.getSingleResult();
	}
	
    public synchronized Pio guardarCon(Usuario autor, String mensaje) {
        
        Pio nuevoPio = null;
        
        if (autor != null && mensaje != null) {
            
            nuevoPio = Pio.nuevo().conId(proximoId()).conAutor(autor).conMensaje(mensaje).conFechaCreacion(new Date());
            App.instancia().obtenerSesion().persist(nuevoPio);
        }
        
        return nuevoPio;
    }
    
    private Long proximoId() {
        
        Long maxId = null;
        
        maxId = (Long) App.instancia().obtenerSesion().createQuery("select max(p.id) from Pio p").getSingleResult();
        
        if (maxId != null) {
            
            maxId += 1L;
            
        } else {
            
            maxId = 1L;
        }
        
        return maxId;
    }
    
    //ejemplo de uso de Criteria
    public List<Pio> obtenerConTexto(String texto){
    	Session session = App.instancia().obtenerSesion();
    	
    	CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
    	CriteriaQuery<Pio> query = criteriaBuilder.createQuery(Pio.class);
    	Root<Pio> pios = query.from(Pio.class);
    	
    	query.select(pios)
    		.where(criteriaBuilder.like(pios.get(Pio_.mensaje), "%" + texto + "%"));
    	return session.createQuery(query).getResultList();
    }
}
