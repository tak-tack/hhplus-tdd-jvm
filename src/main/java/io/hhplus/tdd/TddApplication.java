package io.hhplus.tdd;

import io.hhplus.tdd.point.PointController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TddApplication {

    public static void main(String[] args) {
        PointController pc = new PointController();

        SpringApplication.run(TddApplication.class, args); //java 쓰레드시작
        pc.point(0);
        pc.use(0,0);
        pc.charge(0,0);
        pc.history(0);

    }
}