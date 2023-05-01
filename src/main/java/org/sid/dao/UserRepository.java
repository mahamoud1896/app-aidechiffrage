package org.sid.dao;




import javax.transaction.Transactional;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import org.sid.entities.User;

//import com.example.demo.utils.EncrytedPasswordUtils;



@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
	
	
	//public static final EntityManager entityManager = null;
	
	//public static final RoleRepository roleRepository = null;
	
	User findByUsername(String username);
	
	//@Override
   // void delete(User user);

	
	
	//User findByEmail(String email);
	//User registerNewUserAccount(AppUserForm appUserForm, List<String> roleNames);

	/*
	 public default User registerNewUserAccount(AppUserForm appUserForm, List<String> roleNames) {
	        User appUser = new User();
	       // User.setUsername(appUserForm.getUserName());
	        appUser.setEmail(appUserForm.getEmail());
	        //appUser.setFirstName(appUserForm.getFirstName());
	       // appUser.setLastName(appUserForm.getLastName());
	        appUser.setActive(true);
	        String encrytedPassword = EncrytedPasswordUtils.encrytePassword(appUserForm.getPassword());
	        appUser.setPassword("{bcrypt}"+encrytedPassword);
	        UserRepository.entityManager.persist(appUser);
	        UserRepository.entityManager.flush();
	  
	        UserRepository.roleRepository.createRoleFor(appUser, roleNames);
	  
	        return appUser;
	    }
	
	 default String findAvailableUserName(String userName_prefix) {
	        User account = this.findByUsername(userName_prefix);
	        if (account == null) {
	            return userName_prefix;
	        }
	        int i = 0;
	        while (true) {
	            String userName = userName_prefix + "_" + i++;
	            account = this.findByUsername(userName);
	            if (account == null) {
	                return userName;
	            }
	        }
	    }
	 
	 
	 public default User createUser(Connection<?> connection) {
	        ConnectionKey key = connection.getKey();
	        System.out.println("key= (" + key.getProviderId() + "," + key.getProviderUserId() + ")");
	        UserProfile userProfile = connection.fetchUserProfile();
	        String email = userProfile.getEmail();
	        User appUser = this.findByEmail(email);
	        if (appUser != null) {
	            return appUser;
	        }
	        String userName_prefix = userProfile.getFirstName().trim().toLowerCase()
	                + "_" + userProfile.getLastName().trim().toLowerCase();
	  
	        String userName = this.findAvailableUserName(userName_prefix);
	        String randomPassword = UUID.randomUUID().toString().substring(0, 5);
	        String encrytedPassword = EncrytedPasswordUtils.encrytePassword(randomPassword);
	        appUser = new User();
	        appUser.setActive(true);
	        appUser.setPassword("{bcrypt}"+encrytedPassword);
	        appUser.setUsername(userName);
	        appUser.setEmail(email);
	        //appUser.setFirstName(userProfile.getFirstName());
	       // appUser.setLastName(userProfile.getLastName());
	        UserRepository.entityManager.persist(appUser);
	        // Create default Role
	        List<String> roleNames = new ArrayList<String>();
	        roleNames.add(Role.ROLE_USER);
	        UserRepository.roleRepository.createRoleFor(appUser, roleNames);
	  
	        return appUser;
	    }

	*/

	
	
}
