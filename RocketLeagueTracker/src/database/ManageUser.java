package database;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import classes.User;

public class ManageUser {
	
// ------------ INSTANZVARIABLEN -----------
	
	protected final static StandardServiceRegistry register = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
	protected static SessionFactory factory = new MetadataSources(register).buildMetadata().buildSessionFactory();
		
// ----------- METHODEN -----------	
		

	//Methode zum Speichern eines User-Objekts
	public static void saveUser(User user) {
		Session session = factory.openSession(); //open Session
			
		//Session starten und Objekt in Datenbank ablegen
		try {
			session.beginTransaction(); //Session starten
			session.save(user);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if (session.getTransaction() != null) session.getTransaction().rollback();
				e.printStackTrace();
		} finally {
				session.close(); // Session schlieﬂen
		}
			
	}
	
	//User updaten
	public static <T> void update(classes.User user) {
		Session session = factory.openSession();
		
		try {
			session.beginTransaction();
			session.update(user);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if (session.getTransaction() != null) session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close(); // Session schlieﬂen
		}
	}
	
	
	//User mit PlayerID auslesen
		public static User getUserByPlayerID(String player_ID) {
			Session session = factory.openSession();
			
			User user = null;
			List<User> userList= null;
			
			try {
				session.beginTransaction();
				userList = session.createQuery("FROM User u WHERE u.player_ID = '" + player_ID + "'").getResultList();
				session.getTransaction().commit();
			} catch (HibernateException e) {
				if (session.getTransaction() != null) session.getTransaction().rollback();
				e.printStackTrace();
			} finally {
				session.close(); // Session schlieﬂen
			}
			
			if(userList != null) {
				for(User aUser : userList) {
					if(aUser.getPlayer_ID().equals(player_ID)) {
						user = aUser;
					}
				}
			}
			
			return user;
		}
		
	//User mit PlayerID auslesen
		public static User getUserByUserID(int userID) {
			Session session = factory.openSession();
			
			User user = null;
			List<User> userList= null;
			
			try {
				session.beginTransaction();
				userList = session.createQuery("FROM User u WHERE idUser = '" + userID + "'").getResultList();
				session.getTransaction().commit();
			} catch (HibernateException e) {
				if (session.getTransaction() != null) session.getTransaction().rollback();
				e.printStackTrace();
			} finally {
				session.close(); // Session schlieﬂen
			}
			
			if(userList != null) {
				for(User aUser : userList) {
					if(aUser.getPlayer_ID().equals(userID)) {
						user = aUser;
					}
				}
			}
			
			return user;
		}
		
		
		
		//*Liest eine List mit mˆglichen Teammates aus der DB aus ohne den angemeldeten Spieler auszugeben
				public static List<classes.User> getTeammateList(int currentUserID) {
					Session session = factory.openSession();
					
					User user = null;
					List<User> userList= null;
					
					try {
						session.beginTransaction();
						userList = session.createQuery("FROM User u WHERE u.idUser NOT LIKE '" + currentUserID + "' ORDER BY u.player_ID" ).getResultList();
						session.getTransaction().commit();
					} catch (HibernateException e) {
						if (session.getTransaction() != null) session.getTransaction().rollback();
						e.printStackTrace();
					} finally {
						session.close(); // Session schlieﬂen
					}
					
					return userList;
				}
				
		
	
		

}


