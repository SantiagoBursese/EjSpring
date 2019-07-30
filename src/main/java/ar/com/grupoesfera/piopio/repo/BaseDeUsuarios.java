package ar.com.grupoesfera.piopio.repo;

import java.util.List;

import org.hibernate.Session;

import ar.com.grupoesfera.main.App;
import ar.com.grupoesfera.piopio.modelo.Pio;
import ar.com.grupoesfera.piopio.modelo.Usuario;

public class BaseDeUsuarios {

    @SuppressWarnings("unchecked")
	public List<Usuario> obtenerTodos(){
    	
    	Session session = App.instancia().obtenerSesion();
    	return session.createQuery("from Usuario").getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public List<Pio> obtenerPorUsuario(Usuario autor){
    	Session session = App.instancia().obtenerSesion();
    	return session.createQuery("from Pio p where p.autor = :autor") /** cuando pongo ":" aviso que 
    											voy a setear un parámetro. esto significa que puedo pasar 
    											datos al hacer la query y que no sea siempre igual, tal cual 
    											cómo la escribí. El formato es: en la query> ("query :miParametro")
    											en .setParameter("nombreDelParametroEnLaQuery(miParametroEnEsteCaso)",
    											parametroOMetodoParaObtenerValor);  */
    			.setParameter("autor", autor)
    			.getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public List<Pio> obtenerPorUsuario(String nombreAutor){
    	Session session = App.instancia().obtenerSesion();
    	return session.createQuery("from Pio p where p.autor.nombre = :nombre") 
    			.setParameter("nombre", nombreAutor)
    			.getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public List<Usuario> obtenerSeguidoresDe(Usuario usuario) {
    	return App.instancia().obtenerSesion().createQuery("from Usuario u where :usuario member of u.seguidos")
    	.setParameter("usuario", usuario)
    	.getResultList(); //devuelve todos los objetos Usuarios seguidos
    }
    
    @SuppressWarnings("unchecked")
	public List<String> obtenerNombreDeSeguidoresDe(Usuario usuario) {
    	return App.instancia().obtenerSesion().createQuery("select u.nombre from Usuario u where :usuario member of u.seguidos")
    	.setParameter("usuario", usuario)
    	.getResultList(); //devuelve todos los nombres Usuarios seguidos
    }

	@SuppressWarnings("unchecked")
	public List<Usuario> obtenerAislados() {
		return App.instancia().obtenerSesion().
		createQuery("from Usuario u where u.seguidos is empty and not exists "
				+ "(select u2 from Usuario u2 where u member of u2.seguidos)").getResultList();
	} /**hace la primer query y "not exists (subquery)" es true cuando la subquery no devuelve nada
		daria false si uso "exists" */
}
