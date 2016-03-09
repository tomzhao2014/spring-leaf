package com.qiktone.repository;

import com.qiktone.core.repository.EntityRepository;
import com.qiktone.entity.Department;
import org.springframework.stereotype.Repository;

/**
 * DepartmentRepository
 *
 * @author Tom Zhao
 * @date 2016/3/8 0008
 */
@Repository
public interface DepartmentRepository extends EntityRepository<Department> {
}
