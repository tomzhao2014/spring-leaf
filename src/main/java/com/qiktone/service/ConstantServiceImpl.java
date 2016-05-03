package com.qiktone.service;

import com.qiktone.core.repository.EntityRepository;
import com.qiktone.core.service.BaseEntityService;
import com.qiktone.entity.Constant;
import com.qiktone.repository.ConstantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tom on 2016/3/16.
 */
@Service
public class ConstantServiceImpl extends BaseEntityService<Constant> implements ConstantService {

    @Autowired
    private ConstantRepository constantRepository;
    @Override
    public EntityRepository<Constant> getEntityRepository() {
        return constantRepository;
    }

}
