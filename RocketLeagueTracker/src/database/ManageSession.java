package database;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.TypedQuery;

import org.hibernate.SQLQuery;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import classes.User;

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
		
		// Session anhand von SessionID auslesen
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
		
		//*Session updaten
		public static <T> void update(classes.Session gameSession) {
			Session session = factory.openSession();
			
			try {
				session.beginTransaction();
				session.update(gameSession);
				session.getTransaction().commit();
			} catch (HibernateException e) {
				if (session.getTransaction() != null) session.getTransaction().rollback();
				e.printStackTrace();
			} finally {
				session.close(); // Session schlieﬂen
			}
		}
		
		
		
		
		
		// Aktuelle Session ausheben
				public static classes.Session getCurrentSession() {
					Session session = factory.openSession();
					List<classes.Session> sessionList= null;
					
					try {
						session.beginTransaction();
						sessionList = session.createQuery("FROM Session ORDER BY idSession DESC ").setMaxResults(1).getResultList();
						session.getTransaction().commit();
					} catch (HibernateException e) {
						if (session.getTransaction() != null) session.getTransaction().rollback();
						e.printStackTrace();
					} finally {
						session.close(); // Session schlieﬂen
					}
					
					return sessionList.get(0);
				}
				
		// SessionList 
				public static List<classes.Session> getSessionList(int userID) {
					Session session = factory.openSession();
					List<classes.Session> sessionList = new ArrayList<>();
					
					try {
						session.beginTransaction();
						SQLQuery query = session.createSQLQuery("SELECT * FROM Session WHERE player1 = " + userID + " ORDER BY idSession");
						query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
						sessionList = query.list();
						session.getTransaction().commit();
					} catch (HibernateException e) {
						if (session.getTransaction() != null) session.getTransaction().rollback();
						e.printStackTrace();
					} finally {
						session.close(); // Session schlieﬂen
					}
					
					
					return sessionList;
				}
		
		
		

}
