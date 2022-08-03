package database;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import classes.PlayerStatistic;

public class ManagePlayerStatistic {
	
	
	// ------------ INSTANZVARIABLEN -----------
		
		protected final static StandardServiceRegistry register = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		protected static SessionFactory factory = new MetadataSources(register).buildMetadata().buildSessionFactory();
			
	// ----------- METHODEN -----------	
			


		public static void saveStatistic(PlayerStatistic statistic) {
			Session session = factory.openSession(); //open Session
				
			//Session starten und Objekt in Datenbank ablegen
			try {
				session.beginTransaction(); //Session starten
				session.save(statistic);
				session.getTransaction().commit();
			} catch (HibernateException e) {
				if (session.getTransaction() != null) session.getTransaction().rollback();
					e.printStackTrace();
			} finally {
					session.close(); // Session schlieﬂen
			}
		}
		

			

}
