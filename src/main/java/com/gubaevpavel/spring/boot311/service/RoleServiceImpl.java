package com.gubaevpavel.spring.boot311.service;

import com.gubaevpavel.spring.boot311.repository.RoleRepository;
import com.gubaevpavel.spring.boot311.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional (readOnly = true)
    public List<Role> allRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void deleteRole(int id) {
        roleRepository.deleteById(id);
    }

//    @Override
//    public void editRole(Role role) {
//        roleDao.editRole(role);
//    }

//    @Override
//    public HashSet<Role> getRoleSet(String[] s) {
//        return roleDao.getRoleSet(s);
//    }

    @Override
    @Transactional (readOnly = true)
    public Role getRoleById(int id) {
        return roleRepository.findById(id).get();
    }

    @Override
    @Transactional (readOnly = true)
    public Role getRoleByName(String name) {
        return roleRepository.findRoleByName(name);
    }
}
