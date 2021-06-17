

package com.rfx.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rfx.api.model.TableUserAccount;

public interface UserAccountRepository extends JpaRepository<TableUserAccount, Integer> {

    TableUserAccount findByEmail(String email);
    
  
    @Query(value = "SELECT user_id FROM user_accounts WHERE name=?1", nativeQuery = true)
    String getUserIdByUserName(String name);
	
	 @Query(value = "SELECT user_id FROM user_accounts WHERE BINARY email=?1 and BINARY password=?2", nativeQuery = true)
	 String getUserIdByAuthentication(String email, String password);
	
	 @Query(value = "SELECT role_id FROM user_accounts WHERE email=?1", nativeQuery = true)
	 int getRoleByUserEmail(String email);
	 
	 
     @Query(value = "SELECT user_id FROM user_accounts WHERE email=?1",nativeQuery = true)
     String getUserIdByEmail(String email);
     
     @Query(value = "SELECT email FROM user_accounts WHERE user_id=?1",nativeQuery = true)
     String getEmailByUserId(String user_id);
     
    
    

}
