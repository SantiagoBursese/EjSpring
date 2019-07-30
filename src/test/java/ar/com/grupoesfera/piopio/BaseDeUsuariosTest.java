package ar.com.grupoesfera.piopio;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ar.com.grupoesfera.main.Fixture;
import ar.com.grupoesfera.piopio.modelo.Usuario;
import ar.com.grupoesfera.piopio.repo.BaseDeUsuarios;

public class BaseDeUsuariosTest {
    
	private BaseDeUsuarios usuarios = new BaseDeUsuarios();
	
    @Before
    public void agregarDatos() {
        
        Fixture.initData();
    }
    
    @After
    public void eliminarDatos() {
        
        Fixture.dropData();
    }
    
    /*@Test
    public void deberiasEscribirPruebasUnitarias() {

        Assert.fail("Tus clases deberían estar probadas y con Cobertura completa");
    }*/
    
    @Test
    public void obtenerBaseDeUsuario() {
    	assertEquals(7, usuarios.obtenerTodos().size());
    }
    
    @Test
    public void obtenerPiosDeUsuario() {
    	Usuario marcelo = Usuario.nuevo().conId(1L);
    	assertEquals(2, usuarios.obtenerPorUsuario(marcelo).size());
    }
    
    @Test
    public void obtenerPiosDeUsuarioXNombre() {
    	assertEquals(2, usuarios.obtenerPorUsuario("Marcelo").size());
    }
    
    @Test
    public void obtenerSeguidoresDeMarcelo() {
    	Usuario marcelo = Usuario.nuevo().conId(1L);
    	assertEquals(3, usuarios.obtenerSeguidoresDe(marcelo).size());
    }
    
    @Test
    public void obtenerNombresDeSeguidoresDeMarce() {
    	Usuario marcelo = Usuario.nuevo().conId(1L);
    	List <String> lista = new ArrayList<>();
    	lista.add("Brenda");
    	lista.add("India");
    	lista.add("Sebastian");
    	assertArrayEquals(lista.toArray(), usuarios.obtenerNombreDeSeguidoresDe(marcelo).toArray());
    }
    
    @Test
    public void deberiaObtenerUsuariosAislados() {
    	List<Usuario> aislados = usuarios.obtenerAislados();
    	assertEquals(1, aislados.size());
    }
}
