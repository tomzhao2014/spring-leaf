package com.qiktone.repository;

import com.qiktone.core.repository.EntityRepository;
import com.qiktone.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2016/3/8 0008
 * Time: 13:59
 */
@Repository
public interface RoleRepository extends EntityRepository<Role> {
    public List<Role> getRoleByCompany(Long id);
}
