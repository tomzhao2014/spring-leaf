package com.tomweb.repository;

import com.tomweb.Entity.Member;
import com.tomweb.core.repository.EntityRepository;
import com.tomweb.entity.Member2;
import org.springframework.stereotype.Repository;

/**
 * Created by tom on 15/12/12.
 */
@Repository("memberRepository")
public interface MemberRepository extends EntityRepository<Member>{

}
