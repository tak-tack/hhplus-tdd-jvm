package io.hhplus.tdd.point.repository;

import io.hhplus.tdd.point.UserPoint;

import java.util.List;

public interface PointRepository {

    //UserPointTable userPointTable = new UserPointTable();
    List<UserPoint> selectById(Long id);
}
