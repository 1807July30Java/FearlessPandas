package main;

import com.revature.beans.Address;
import com.revature.beans.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import java.util.HashSet;
import java.util.Set;

public class Driver {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("root-context.xml");

        SessionFactory sf = (SessionFactory) ac.getBean("mySessionFactory");

        Session s = sf.openSession();
        Transaction tx = s.beginTransaction();

        try {
            int id = (int) s.save(new User("Timmy", "isCool"));
            User temp = (User) s.get(User.class, id);
            Set<Address> tempAddress = new HashSet();
            tempAddress.add(new Address("123 Street", "4E", "Queens", "NY", 11404));
            temp.setAddresses(tempAddress);
            s.saveOrUpdate(temp);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }

        s.close();
        sf.close();
//        Session s = sf.openSession();
//        Trans
//
//        int id = (int) s.save(new User("Timmy", "isCool"));
//
//        System.out.println(id);
//
//        s.close();
//        sf.close();
    }
}
