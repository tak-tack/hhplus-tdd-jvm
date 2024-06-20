package io.hhplus.tdd.point.repository;

import io.hhplus.tdd.point.TransactionType;
import io.hhplus.tdd.point.domain.UserPointDomain;

public interface UserPointRepository {
    UserPointDomain selectById(Long id);

    UserPointDomain update(long Id, long amount, TransactionType type);



}
