package com.qiktone.service;

import com.qiktone.core.repository.EntityRepository;
import com.qiktone.core.service.BaseEntityService;
import com.qiktone.entity.Company;
import com.qiktone.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2016/3/8 0008
 * Time: 14:02
 */
@Service
public class CompanyServiceImpl extends BaseEntityService<Company> implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public EntityRepository<Company> getEntityRepository() {
        return companyRepository;
    }

    @Override
    public void create(Company company) {
        //生成公司代码
        String companyCode = companyRepository.getCompanyCode();
        company.setCode(companyCode);
        super.create(company);
    }
}
