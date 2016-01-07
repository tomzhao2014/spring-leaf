package com.tomweb.service;

import com.tomweb.core.repository.EntityRepository;
import com.tomweb.core.service.BaseEntityService;

import com.tomweb.entity.Member;
import com.tomweb.repository.MemberRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * Created by tom on 15/12/12.
 */
@Service("memberService")
public class MemberServiceImpl extends BaseEntityService<Member> implements MemberService{

    @Resource(name="memberRepository")
    MemberRepository memberRepository;

    @Override
    public EntityRepository<Member> getEntityRepository() {
        return memberRepository;
    }

}
