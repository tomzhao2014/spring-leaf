package com.qiktone.service;

import com.qiktone.core.repository.EntityRepository;
import com.qiktone.core.service.BaseEntityService;
import com.qiktone.entity.Department;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2016/3/8 0008
 * Time: 14:02
 */
@Service
public class DepartmentServiceImpl extends BaseEntityService<Department> implements DepartmentService {
    @Override
    public EntityRepository<Department> getEntityRepository() {
        return null;
    }
}
