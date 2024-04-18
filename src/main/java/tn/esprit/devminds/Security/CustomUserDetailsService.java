package tn.esprit.devminds.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tn.esprit.devminds.Entities.RoleEntity;
import tn.esprit.devminds.Entities.UserEntity;
import tn.esprit.devminds.Repository.UserEntityRepo;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserEntityRepo userEntityRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userEntityRepo.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("User Not found"));
        return User.withUsername(userEntity.getUserName())
                .password(userEntity.getPassword())
                .disabled(!userEntity.getAccountStatus())
                .authorities(mapRolesToAuthorities(userEntity.getRoles()))
                .build();
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<RoleEntity> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }
}
