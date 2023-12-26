package com.bezkoder.springjwt;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.security.services.UserDetailsImpl;

public class HelpUtil {

	public static String ErrorFromServerToClint(String info) {
		String errorStr = "{\"ErrorFromService\":\"" + info + "\"}";
		return errorStr;
	}
	
	public static void ErrorServerLog(String info) {
		System.out.println(info);
	}
	
//	public static User getCurrentAuthenticatedUser() {
//	    // Get the UserDetails from the security context
//	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//	    if (principal instanceof UserDetailsImpl) {
//	        // If using CustomUserDetails
//	    	UserDetailsImpl customUserDetails = (UserDetailsImpl) principal;
//	        return customUserDetails.getUser();
//	    } else {
//	        // Handle other UserDetails implementations or return null
//	        return null;
//	    }
//	} 
}
