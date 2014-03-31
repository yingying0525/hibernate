package org.hibernate.tutorial;

import org.hibernate.*;

import java.util.*;

import org.hibernate.tutorial.domain.Event;
import org.hibernate.tutorial.util.HibernateUtil;

public class EventManager {

    public static void main(String[] args) {
        EventManager mgr = new EventManager();

        if (args[0].equals("store")) {
        	System.out.println("create");
            mgr.createAndStoreEvent("My aaa", new Date());
        }
        else if (args[0].equals("list")) {
        	System.out.println("list");
        	List theEvent = mgr.listEvents();
			for(int i = 0; i < theEvent.size(); i++)
			{
				Event event = new Event();
				event = (Event)theEvent.get(i);
				System.out.println(event.getId());
			}           
        }
        
        HibernateUtil.getSessionFactory().close();      
    }

    private void createAndStoreEvent(String title, Date theDate) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Event theEvent = new Event();
        theEvent.setId(1);
        theEvent.setTitle(title);
        theEvent.setDate(theDate);
        session.save(theEvent);

        session.getTransaction().commit();
    }
    private List listEvents() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        
        List result = session.createSQLQuery("select * from Events").addEntity(Event.class).list();
        //List result = session.createSQLQuery("select * from Event where day >= 15765 and day <= 16130 and number > 50").addEntity(Event.class).list();
        session.getTransaction().commit();
        return result;
    }

}
