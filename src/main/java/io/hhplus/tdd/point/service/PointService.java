package io.hhplus.tdd.point.service;

import io.hhplus.tdd.point.domain.PointHistoryDomain;
import io.hhplus.tdd.point.domain.UserPointDomain;

import java.util.List;

public interface PointService {
    UserPointDomain selectById(Long Id);
    UserPointDomain chargePoint(long Id, long amount);
    UserPointDomain usePoint(long Id, long amount);
    List<PointHistoryDomain> selectAllByUserId(Long Id);


}
