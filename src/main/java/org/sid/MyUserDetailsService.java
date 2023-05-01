package org.sid;
/*
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chiffrageTest.dao.RoleRepository;
import com.chiffrageTest.dao.UserRepository;
import com.chiffrageTest.entities.Action;
import com.chiffrageTest.entities.Role;
import com.chiffrageTest.entities.User;
import com.chiffrageTest.service.IchiffrageService;

@Service("userDetailsService")
@Transactional
public class MyUserDetailsService implements UserDetailsService  {

	@Autowired
    private UserRepository userRepository;
 
    @Autowired
    private IchiffrageService service;
 
   // @Autowired
   // private MessageSource messages;
 
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException {
 
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return new org.springframework.security.core.userdetails.User(
              " ", " ", true, true, true, true, 
              getAuthorities(Arrays.asList(
                roleRepository.findByRoleName("ROLE_USER"))));
        }

        return new org.springframework.security.core.userdetails.User(
          user.getUsername(), user.getPassword(), user.getActive(), true, true, 
          true, getAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(
      Collection<Role> roles) {
 
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(Collection<Role> roles) {
 
        List<String> privileges = new ArrayList<>();
        List<Action> collection = new ArrayList<>();
        for (Role role : roles) {
            privileges.add(role.getRoleName());
            collection.addAll(role.getActions());
        }
        for (Action item : collection) {
            privileges.add(item.getActionName());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
	
	
	

} */
