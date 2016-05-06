package com.qiktone.service;

import com.qiktone.entity.Account;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/5/3 0003.
 */
@Service
public class DemoService {
    @Cacheable({"test","arg"})
    public int testCache(int arg){
        System.out.print("====================================111=============================");
        return 1;
    }

    @Cacheable(cacheNames = "account",key = "#company")
    public int findAccount0(Company company, boolean isAdult, boolean isMan){
        System.out.println("key=#company");
        return 0;
    }
    @Cacheable(cacheNames = "account",key = "#company.id")
    public int findAccount1(Company company, boolean isAdult, boolean isMan){
        System.out.println("key=#company.id");
        return 1;
    }
    @Cacheable(cacheNames = "account", key="T(Company).hash(#company)")
    public int findAccount2(Company company,boolean isAdult,boolean isMan){
        System.out.println("key=T(Company).hash(#company)");
        return 2;
    }

    @Cacheable(cacheNames = "account", keyGenerator="myKeyGenerator")
    public int findAccount3(Company company,boolean isAdult,boolean isMan){
        System.out.println("myKeyGenerator");
        return 3;
    }

    @Cacheable(cacheNames = "account", keyGenerator="myKeyGenerator",cacheResolver = "myCacheResolver")
    public int findAccount4(Company company,boolean isAdult,boolean isMan){
        System.out.println("myKeyGenerator && cacheManager");
        return 4;
    }


}
