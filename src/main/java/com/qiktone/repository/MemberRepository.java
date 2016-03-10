package com.qiktone.repository;

import com.qiktone.core.repository.EntityRepository;
import com.qiktone.entity.Member;
import org.springframework.stereotype.Repository;

/**
 * Created by tom on 2016/3/9.
 */
@Repository
public interface MemberRepository extends EntityRepository<Member> {
}
