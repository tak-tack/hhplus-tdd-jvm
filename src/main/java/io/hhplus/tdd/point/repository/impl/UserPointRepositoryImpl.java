package io.hhplus.tdd.point.repository.impl;

import io.hhplus.tdd.database.UserPointTable;
import io.hhplus.tdd.point.TransactionType;
import io.hhplus.tdd.point.entity.UserPoint;
import io.hhplus.tdd.point.domain.UserPointDomain;
import io.hhplus.tdd.point.repository.PointHistoryRepository;
import io.hhplus.tdd.point.repository.UserPointRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserPointRepositoryImpl implements UserPointRepository {

    private final UserPointTable userPointTable;
    private final PointHistoryRepository pointHistoryRepository;

    @Override
    public UserPointDomain selectById(Long id) {
        UserPoint userPoint =  userPointTable.selectById(id);
        return userPoint.toDomain();
    }

    @Override
    // history insert 만들기
    public UserPointDomain update(long Id, long amount, TransactionType type){
        UserPoint userPoint = userPointTable.insertOrUpdate(Id,amount);
        pointHistoryRepository.save(Id,amount,type,System.currentTimeMillis());
        return userPoint.toDomain();
    }
}
