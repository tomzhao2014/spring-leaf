package com.qiktone.repository;

import com.qiktone.core.repository.EntityRepository;
import com.qiktone.entity.Account;
import com.qiktone.entity.Role;
import com.qiktone.entity.RolePrivilege;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AccountRepository
 *
 * @author Tom Zhao
 * @date 2016/3/8 0008
 */
@Repository
public interface RolePrivilegeRepository extends EntityRepository< RolePrivilege> {
    List<RolePrivilege> getModuleByRole(Long rid);
    List<RolePrivilege> getFunctionByModuleNameAndRole(String moduleName,Long rid);
    List<RolePrivilege> getOpByFunctionNameAndRole(String functionName,Long fid);
}
