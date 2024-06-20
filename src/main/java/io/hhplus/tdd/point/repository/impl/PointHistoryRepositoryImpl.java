package io.hhplus.tdd.point.repository.impl;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.point.TransactionType;
import io.hhplus.tdd.point.domain.PointHistoryDomain;
import io.hhplus.tdd.point.entity.PointHistory;
import io.hhplus.tdd.point.repository.PointHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class PointHistoryRepositoryImpl implements PointHistoryRepository {

    private final PointHistoryTable pointHistoryTable;

    @Override
    public List<PointHistoryDomain> selectAllByUserId(long id){
        List<PointHistory> pointHistory = pointHistoryTable.selectAllByUserId(id);
        return pointHistory.stream().map(PointHistory::toDomain).toList();
    }

    @Override
    public PointHistoryDomain save(long userId, long amount, TransactionType type, long updateMillis){
        PointHistory pointHistory = pointHistoryTable.insert(userId, amount, type, updateMillis);
        return pointHistory.toDomain();
    }



}
