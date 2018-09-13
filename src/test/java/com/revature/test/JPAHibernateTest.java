package com.revature.test;

import org.h2.tools.RunScript;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

@Transactional
public class JPAHibernateTest {

    private static SessionFactory sessionFactory;
    protected static Session s;

    @BeforeClass
    public static void init() throws Exception {
        AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        sessionFactory = applicationContext.getBean("mySessionFactory", SessionFactory.class);
        s = sessionFactory.openSession();
    }

    @Before
    public void setUp() throws Exception {
        s.doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                try {
                    File script = new File(getClass().getResource("/data.sql").getFile());
                    RunScript.execute(connection, new FileReader(script));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException("could not initialize with script");
                }
            }
        });
    }

    @After
    public void tearDown() throws Exception {
        s.close();
        sessionFactory.close();
    }
}
