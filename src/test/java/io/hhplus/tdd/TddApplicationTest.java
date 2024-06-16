package io.hhplus.tdd;

import io.hhplus.tdd.point.PointController;
import io.hhplus.tdd.point.UserPoint;
import org.springframework.boot.SpringApplication;

import static org.junit.jupiter.api.Assertions.*;

class TddApplicationTest {
    public static void main(String[] args) {
        PointController pc = new PointController();

        SpringApplication.run(TddApplication.class, args); //java 쓰레드시작


    }
}