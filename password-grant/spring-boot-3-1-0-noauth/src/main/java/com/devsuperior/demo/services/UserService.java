package com.devsuperior.demo.services;

import com.devsuperior.demo.entities.Role;
import com.devsuperior.demo.entities.User;
import com.devsuperior.demo.projections.UserDetailsProjection;
import com.devsuperior.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = null;
        try{

             user = userRepository.findByEmail(username);


        }catch(UsernameNotFoundException e){
            throw new UsernameNotFoundException("usuário não encontrado");
        }


        return user;
    }

    public UserDetails searchUserAndRolesByEmail(String username) throws UsernameNotFoundException {

        User user = new User();
        List<UserDetailsProjection>  lista = userRepository.searchUserAndRolesByEmail(username);

        user.setEmail(username);
        user.setPassword(lista.get(0).getPassword());

        for(UserDetailsProjection projection : lista){

            user.addRole(new Role(projection.getRoleId(),projection.getAuthority()));
        }

           return user;

    }


}
