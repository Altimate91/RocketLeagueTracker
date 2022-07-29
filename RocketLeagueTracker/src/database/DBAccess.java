package database;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DBAccess {

// ------------ INSTANZVARIABLEN -----------
	
	protected final static StandardServiceRegistry register = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
	protected static SessionFactory factory = new MetadataSources(register).buildMetadata().buildSessionFactory();
	
// ----------- METHODEN -----------	
	
	//Object in Datenbank speichern
	public static void save(Object o) {
		Session session = factory.openSession(); //open Session
		
		//Session starten und Objekt in Datenbank ablegen
		try {
			session.beginTransaction(); //Session starten
			session.save(o);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if (session.getTransaction() != null) session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close(); // Session schließen
		}
		
	}
	
	//*Object auslesen anhand von id
	public static <T> Object load(int id, Class<T> objectClass) {
		Session session = factory.openSession();
		T data = null;
		
		try {
			session.beginTransaction();
			data = session.get(objectClass, id);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if (session.getTransaction() != null) session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close(); // Session schließen
		}
		
		return data;
	}
	
	//*Object updaten
	public static <T> void update(Object o, Class<T> objectClass) {
		Session session = factory.openSession();
		
		try {
			session.beginTransaction();
			session.update(o);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if (session.getTransaction() != null) session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close(); // Session schließen
		}
	}
	
	
	//*Object löschen
	public static void delete(Object o) {
		Session session = factory.openSession();
		
		try {
			session.beginTransaction();
			session.delete(o);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if (session.getTransaction() != null) session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close(); // Session schließen
		}
	}
	
	//*Object nach ID löschen
	public static <T> void delete(int id, Class<T> objectClass) {
		
		DBAccess.delete(DBAccess.load(id, objectClass));
		
	}
	

}
