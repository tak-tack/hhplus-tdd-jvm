package io.hhplus.tdd.point.controller;

import io.hhplus.tdd.point.domain.PointHistoryDomain;
import io.hhplus.tdd.point.domain.UserPointDomain;
import io.hhplus.tdd.point.service.PointService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/point")
@RequiredArgsConstructor
public class PointController {

    private static final Logger log = LoggerFactory.getLogger(PointController.class);

    private final PointService pointService;

    /**
     * TODO - 특정 유저의 포인트를 조회하는 기능을 작성해주세요.
     */
    // UserPointDomain 타입을 응답한다
    @GetMapping("/{id}")
    public UserPointDomain point(@PathVariable long id) {
        return this.pointService.selectById(id);
    }

    /**
     * TODO - 특정 유저의 포인트 충전/이용 내역을 조회하는 기능을 작성해주세요.
     */
    @GetMapping("{id}/histories") // getmapping : 받아오는 http 메서드
    public List<PointHistoryDomain> history(@PathVariable long id) {
        return this.pointService.selectAllByUserId(id);
    }

    /**
     * TODO - 특정 유저의 포인트를 충전하는 기능을 작성해주세요.
     */
    @PatchMapping("{id}/charge") // 변경하는 patchmapping : 변경하는것
    public UserPointDomain charge(@PathVariable long id, @RequestBody long amount)
    {
        return this.pointService.chargePoint(id,amount);
    }

    /**
     * TODO - 특정 유저의 포인트를 사용하는 기능을 작성해주세요.
     */
    @PatchMapping("{id}/use") // patchmapping : 변경하는것
    public UserPointDomain use(@PathVariable long id, @RequestBody long amount) {
        return this.pointService.usePoint(id,amount);
    }
}
