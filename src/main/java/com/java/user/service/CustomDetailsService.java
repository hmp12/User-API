package com.java.user.service;

import com.java.user.entity.CustomUser;
import com.java.user.entity.UserEntity;
import com.java.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public CustomUser loadUserByUsername(final String username) throws UsernameNotFoundException {
        UserEntity user = null;
        try {
            user = userRepository.getUserDetails(username);
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("admin");
            List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
            grantedAuthorityList.add(grantedAuthority);
            CustomUser customUser = new CustomUser(user, grantedAuthorityList);
            return customUser;
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
    }
}
