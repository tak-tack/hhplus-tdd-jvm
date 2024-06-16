package io.hhplus.tdd.point.repository.impl;

import io.hhplus.tdd.database.UserPointTable;
import io.hhplus.tdd.point.UserPoint;
import io.hhplus.tdd.point.repository.PointRepository;

import java.util.ArrayList;
import java.util.List;

public class PointRepositoryImpl implements PointRepository{
    //UserPointTable userPointTable = new UserPointTable();

    private List<UserPoint> userPoint = new ArrayList<>();

    @Override
    public List<UserPoint> selectById(Long id) {
        return null;
    }


}
