package ch.wiss.pruefung_294_295.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ch.wiss.pruefung_294_295.model.Role;
import ch.wiss.pruefung_294_295.model.User;
import ch.wiss.pruefung_294_295.model.UserDetailsImpl;
import ch.wiss.pruefung_294_295.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        List < GrantedAuthority > authorities = new ArrayList < > ();

        for (Role r: user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(r.getName().toString()));
        }

        return UserDetailsImpl.build(user);
    }

}