package com.qiktone.service;

import com.qiktone.core.repository.EntityRepository;
import com.qiktone.core.service.BaseEntityService;
import com.qiktone.entity.Member;
import com.qiktone.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tom on 2016/3/9.
 */
@Service
public class MemberServiceImpl extends BaseEntityService<Member>  implements MemberService{
    @Autowired
    private MemberRepository memberRepository;
    @Override
    public EntityRepository<Member> getEntityRepository() {
        return memberRepository;
    }
}
