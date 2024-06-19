package io.hhplus.tdd.point.repository.impl;

import io.hhplus.tdd.database.UserPointTable;
import io.hhplus.tdd.point.TransactionType;
import io.hhplus.tdd.point.controller.PointController;
import io.hhplus.tdd.point.entity.UserPoint;
import io.hhplus.tdd.point.domain.UserPointDomain;
import io.hhplus.tdd.point.repository.UserPointRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class UserPointRepositoryImpl implements UserPointRepository {


    private final UserPointTable userPointTable;

    @Override
    public UserPointDomain selectById(Long id) {
        UserPoint userPoint =  userPointTable.selectById(id);


        return userPoint.toDomain();
    }

    @Override
    public UserPointDomain chargePoint(long userId, long amount){
        UserPoint userPoint = userPointTable.insertOrUpdate(userId,amount);
        return userPoint.toDomain();


    }

    @Override
    public UserPointDomain usePoint(long userId, long amount){
        UserPoint userPoint = userPointTable.insertOrUpdate(userId,amount);
        return userPoint.toDomain();


    }




}
