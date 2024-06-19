package io.hhplus.tdd.point.repository;

import io.hhplus.tdd.point.TransactionType;
import io.hhplus.tdd.point.domain.UserPointDomain;
import io.hhplus.tdd.point.entity.PointHistory;

import java.util.List;

public interface UserPointRepository {
    UserPointDomain selectById(Long id);

    UserPointDomain chargePoint(long userId, long amount);

    UserPointDomain usePoint(long userId, long amount);


}
