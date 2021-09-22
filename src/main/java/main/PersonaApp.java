package main;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import entities.Autor;
import entities.Domicilio;
import entities.Libro;
import entities.Localidad;
import entities.Persona;
public class PersonaApp {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistencePU");
		EntityManager em =emf.createEntityManager();
		try {
			em.getTransaction().begin();
			Localidad loc = Localidad.builder().denominacion("Guaymallen").build();
			Domicilio dom1 = Domicilio.builder().calle("Moldes").numero(123).build();		
			Autor auto1 = Autor.builder().
					nombre("J R R ")
					.apellido(" Tolkien")
					.biografia("aaaaaaa")
					.build();
			Libro libro1 = Libro.builder()
					.titulo("El Silmarillion")
					.fecha(1970)
					.genero("Novela")
					.paginas(200)
					.build();
			Persona persona1 = Persona.builder()
					.nombre("Aldo")
					.apellido("Lopez")
					.dni(11111111)
					.build();
			libro1.getAutores().add(auto1);
			em.persist(auto1);
			em.persist(libro1);
			dom1.setLocalidad(loc);
			persona1.setDomicilio(dom1);
			em.persist(dom1);
			ArrayList<Libro> lib1 = new ArrayList<>();
			lib1.add(libro1);
			persona1.setLibro(lib1);
			em.persist(persona1);
			em.flush();
			em.getTransaction().commit();	
		}
		catch(Exception e) 
		{	
			em.getTransaction().rollback();	
		}
		em.close();
		emf.close();
	}
}
