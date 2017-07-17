package net.okres.springsecurutyapp.dao;

import net.okres.springsecurutyapp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Alex on 17.07.2017.
 */
public interface RoleDao extends JpaRepository<Role, Long> {

}
