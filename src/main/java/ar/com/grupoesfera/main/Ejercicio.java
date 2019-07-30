package ar.com.grupoesfera.main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ar.com.grupoesfera.piopio.modelo.Pio;

public class Ejercicio {

	public static void main(String[] args) {
		
		Session session = App.instancia().obtenerSesion();
		
		Transaction transaction = session.beginTransaction();

		Pio pio = Pio.nuevo()
				.conId(1L)
				.conMensaje("Nuevo pio con id y mensaje.");
		
		Pio pio2 = Pio.nuevo()
				.conId(2L)
				.conMensaje("2do pio con id y mensaje.");
		
		Pio pio3 = Pio.nuevo()
				.conId(3L)
				.conMensaje("3er pio con id y mensaje.");
		
		Pio pio4 = Pio.nuevo()
				.conId(4L)
				.conMensaje("4to pio con id y mensaje.");
		
		
		session.save(pio);
		session.save(pio2);
		session.save(pio3);
		session.save(pio4);
		transaction.commit();

		@SuppressWarnings("unchecked")
		List<Pio> pios = session.createQuery("from Pio p where p.mensaje= :mensaje")
				.setParameter("mensaje", "4to pio con id y mensaje.").getResultList();
		System.out.println(pios);
		System.exit(0);
	}
}
