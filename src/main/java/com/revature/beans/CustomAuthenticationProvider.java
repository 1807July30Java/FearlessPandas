package com.revature.beans;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.revature.domain.User;

@Component
public class CustomAuthenticationProvider
  implements AuthenticationProvider {
	@Autowired
	SessionFactory sessionFactory;
    @Override
    public Authentication authenticate(Authentication authentication) 
      throws AuthenticationException {
  
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
         
        if (true) {
            Session s = sessionFactory.openSession();
            Query q = s.getNamedQuery("getUserByLogin");
            q.setString("username",name);
            q.setInteger("password", password.hashCode());
            if(q.uniqueResult() != null) {
            	s.close();
            return new UsernamePasswordAuthenticationToken(
              name, password, new ArrayList<>());
            }else {
            	s.close();
            	return null;
            }
        } else {
            return null;
        }
    }
 
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
          UsernamePasswordAuthenticationToken.class);
    }
}