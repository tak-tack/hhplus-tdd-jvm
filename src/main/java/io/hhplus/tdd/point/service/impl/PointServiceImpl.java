package io.hhplus.tdd.point.service.impl;

import io.hhplus.tdd.point.PointController;
import io.hhplus.tdd.point.UserPoint;
import io.hhplus.tdd.point.service.PointService;
import org.springframework.stereotype.Service;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("PointService")
public class PointServiceImpl implements PointService {

    private static final Logger log = LoggerFactory.getLogger(PointController.class);

    @Override
    public java.util.List<UserPoint> selectById(Long Id) {
        log.info("PointController-point-service");
        return java.util.List.of();
    }
}
