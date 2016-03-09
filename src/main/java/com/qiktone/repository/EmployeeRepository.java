package com.qiktone.repository;

import com.qiktone.core.repository.EntityRepository;
import com.qiktone.entity.Employee;
import org.springframework.stereotype.Repository;

/**
 * EmployeeRepository
 *
 * @author Tom Zhao
 * @date 2016/3/8 0008
 */
@Repository
public interface EmployeeRepository extends EntityRepository<Employee> {
}
