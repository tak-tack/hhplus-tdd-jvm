package io.hhplus.tdd.point.service;

import io.hhplus.tdd.point.UserPoint;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PointService {
    List<UserPoint> selectById(Long Id);


}
