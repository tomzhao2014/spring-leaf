package com.qiktone.repository;

import com.qiktone.core.repository.EntityRepository;
import com.qiktone.entity.Company;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CompanyRepository
 *
 * @author Tom Zhao
 * @date 2016/3/8 0008
 */
@Repository
public interface CompanyRepository extends EntityRepository<Company> {
    Company getById(@Param(value = "id") Long id);
    String getCompanyCode();
    List<Company> findAll();
}
