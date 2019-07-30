package ar.com.grupoesfera.piopio;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ar.com.grupoesfera.main.Fixture;
import ar.com.grupoesfera.piopio.modelo.Pio;
import ar.com.grupoesfera.piopio.repo.BaseDePios;

public class BaseDePiosTest {
	
	private BaseDePios pios = new BaseDePios();
	
    @Before
    public void agregarDatos() {

        Fixture.initData();
    }

    @After
    public void eliminarDatos() {

        Fixture.dropData();
    }
   	
    @Test
    public void deberiaObtenerTodosLosPios() {
    	List<Pio> listaDePios = pios.obtenerLista();
    	assertEquals(5, listaDePios.size());
    }
}
