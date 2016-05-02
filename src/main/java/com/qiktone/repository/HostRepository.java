package com.qiktone.repository;

import com.qiktone.core.repository.EntityRepository;
import com.qiktone.entity.Host;
import com.qiktone.entity.vo.ShoppingOrderQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * HostRepository
 *
 * @author Tom Zhao
 * @date 2016/3/22 0022
 */
@Repository
public interface HostRepository extends EntityRepository<Host> {
    List<Host> findAll();
    List<ShoppingOrderQuery> queryShippingOrder(@Param("number") String number,@Param("departmentId") Long departmentId);
}
