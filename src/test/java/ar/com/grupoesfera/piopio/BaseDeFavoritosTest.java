package ar.com.grupoesfera.piopio;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ar.com.grupoesfera.main.Fixture;
import ar.com.grupoesfera.piopio.modelo.Usuario;
import ar.com.grupoesfera.piopio.repo.BaseDeFavoritos;

public class BaseDeFavoritosTest {
    
	private BaseDeFavoritos favoritos = new BaseDeFavoritos();
	
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
    public void obtenerFavoritosDeMarcelo() {
    	Usuario marcelo = Usuario.nuevo().conId(1L);
    	assertEquals(1, favoritos.obtenerPiosFavoritosDe(marcelo).size());
    }
    
}
