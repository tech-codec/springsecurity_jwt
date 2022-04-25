package org.sid.jwtspringsec.service;

import org.sid.jwtspringsec.dao.AppRoleRepository;
import org.sid.jwtspringsec.dao.AppUserRepository;
import org.sid.jwtspringsec.entity.AppRole;
import org.sid.jwtspringsec.entity.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImp implements AccountService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private AppRoleRepository roleRepository;

    @Override
    public AppUser saveUser(AppUser user) {
        String hasHPW = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hasHPW);
        return userRepository.save(user);
    }

    @Override
    public AppRole saveRole(AppRole role) {

        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUse(String username, String roleName) {
        AppRole role = roleRepository.findByRoleName(roleName);
        AppUser user= userRepository.findByUsername(username);
        user.getRoles().add(role);
    }

    @Override
    public AppUser findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
