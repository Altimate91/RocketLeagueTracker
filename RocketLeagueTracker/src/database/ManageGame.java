package database;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import classes.Game;


public class ManageGame {

	
	// ------------ INSTANZVARIABLEN -----------
		
		protected final static StandardServiceRegistry register = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		protected static SessionFactory factory = new MetadataSources(register).buildMetadata().buildSessionFactory();
			
	// ----------- METHODEN -----------	
			

		//Methode zur Speichern eines Game-Objekts in der Datenbank
		public static void saveGame(Game game) {
			Session session = factory.openSession(); //open Session
				
			//Session starten und Objekt in Datenbank ablegen
			try {
				session.beginTransaction(); //Session starten
				session.save(game);
				session.getTransaction().commit();
			} catch (HibernateException e) {
				if (session.getTransaction() != null) session.getTransaction().rollback();
					e.printStackTrace();
			} finally {
					session.close(); // Session schlieﬂen
			}
		}
		

			
		
			


}
