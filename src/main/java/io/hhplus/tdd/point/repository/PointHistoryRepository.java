package io.hhplus.tdd.point.repository;
import io.hhplus.tdd.point.TransactionType;
import io.hhplus.tdd.point.domain.PointHistoryDomain;
import io.hhplus.tdd.point.domain.UserPointDomain;

import java.util.List;

public interface PointHistoryRepository {
    List<PointHistoryDomain> selectAllByUserId(long userId);

    PointHistoryDomain save(long userId, long amount, TransactionType type, long updateMillis);
}