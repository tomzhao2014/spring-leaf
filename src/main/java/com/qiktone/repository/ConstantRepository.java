package com.qiktone.repository;

import com.qiktone.core.repository.EntityRepository;
import com.qiktone.entity.Constant;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tom on 2016/3/10.
 */
@Repository
public interface ConstantRepository extends EntityRepository<Constant>{
    List<Constant> findByType(String type);
    Constant getCompanyStateType(String code);
    Constant getCompanyType(String code);
}
