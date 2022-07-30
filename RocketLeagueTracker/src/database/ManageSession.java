package database;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class ManageSession {
	
	// ------------ INSTANZVARIABLEN -----------
	
		protected final static StandardServiceRegistry register = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		protected static SessionFactory factory = new MetadataSources(register).buildMetadata().buildSessionFactory();
			
	// ----------- METHODEN -----------	
		
		public static void saveSession(classes.Session gameSession) {
			Session session = factory.openSession(); //open Session
				
			//Session starten und Objekt in Datenbank ablegen
			try {
				session.beginTransaction(); //Session starten
				session.save(gameSession);
				session.getTransaction().commit();
			} catch (HibernateException e) {
				if (session.getTransaction() != null) session.getTransaction().rollback();
					e.printStackTrace();
			} finally {
					session.close(); // Session schlieﬂen
			}
				
		}
		
		public static classes.Session loadSession(int id) {
			Session session = factory.openSession();
			classes.Session gameSession = null;
			
			try {
				session.beginTransaction();
				gameSession = (classes.Session) session.get(Session.class, id);
				session.getTransaction().commit();
			} catch (HibernateException e) {
				if (session.getTransaction() != null) session.getTransaction().rollback();
				e.printStackTrace();
			} finally {
				session.close(); // Session schlieﬂen
			}
			
			return gameSession;
		}
		
		
		

}
