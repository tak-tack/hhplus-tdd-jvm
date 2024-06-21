package io.hhplus.tdd.point.service.impl;

import io.hhplus.tdd.point.controller.PointController;
import io.hhplus.tdd.point.domain.PointHistoryDomain;
import io.hhplus.tdd.point.domain.UserPointDomain;
import io.hhplus.tdd.point.repository.PointHistoryRepository;
import io.hhplus.tdd.point.repository.UserPointRepository;
import io.hhplus.tdd.point.service.PointService;
import io.hhplus.tdd.point.TransactionType;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {
    private final UserPointRepository userPointRepository;
    private final PointHistoryRepository pointHistoryRepository;
    private static final Logger log = LoggerFactory.getLogger(PointController.class);

    //조회
    @Override
    public UserPointDomain selectById(Long Id) {
        return userPointRepository.selectById(Id);
    }

    //충전
    @Override
    public UserPointDomain chargePoint(long Id, long amount){
        if (amount == 0 || amount == -1)
        {
            throw new RuntimeException();
        }

        long totAmount = amount+userPointRepository.selectById(Id).getPoint();
        return userPointRepository.update(Id,totAmount, TransactionType.CHARGE); // 포인트 충전을 위한 연산
    }

    // 사용
    @Override
    public UserPointDomain usePoint(long Id, long amount) {
        if (amount == 0 || amount == -1) {
            throw new RuntimeException();
        }
        long totAmount = userPointRepository.selectById(Id).getPoint() - amount;
        return userPointRepository.update(Id, totAmount, TransactionType.USE); // 포인트 사용을 위한 연산
    }

    @Override
    public List<PointHistoryDomain> selectAllByUserId(Long Id){
        return pointHistoryRepository.selectAllByUserId(Id);
    }


}

