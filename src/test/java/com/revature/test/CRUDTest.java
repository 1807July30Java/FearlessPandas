package com.revature.test;

import com.revature.domain.User;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class CRUDTest extends JPAHibernateTest {

    @Test
    public void testGetUserByID_success() {
        User user = (User) s.get(User.class, 200);
        assertNotNull(user);
    }

    @Test
    public void testGetAuction_success() {

    }
}
