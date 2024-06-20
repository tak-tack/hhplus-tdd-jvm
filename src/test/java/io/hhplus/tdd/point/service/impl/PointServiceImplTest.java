package io.hhplus.tdd.point.service.impl;

import io.hhplus.tdd.point.TransactionType;
import io.hhplus.tdd.point.domain.UserPointDomain;
import io.hhplus.tdd.point.entity.UserPoint;
import io.hhplus.tdd.point.repository.UserPointRepository;
import io.hhplus.tdd.point.domain.PointHistoryDomain;
import io.hhplus.tdd.point.entity.PointHistory;
import io.hhplus.tdd.point.repository.PointHistoryRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;
import static org.mockito.BDDMockito.given;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

@ExtendWith(MockitoExtension.class) // Junit5 기능을 사용하고 Test 에서 가짜 객체를 사용
@AutoConfigureMockMvc
class PointServiceImplTest {

    private static final Logger log = LoggerFactory.getLogger(PointServiceImplTest.class);
    //Stub 처리
    @Mock // Repository 연결 해제, Mock 선언
    private UserPointRepository userPointRepository;

    @Mock
    private PointHistoryRepository pointHistoryRepository;

    @InjectMocks
    private PointServiceImpl pointService;

    // entity 객체생성
    final UserPoint userPoint = new UserPoint(1,10,System.currentTimeMillis());
    private long cursor = 1;

    final Long id = 1L;
    final Long amount = 30L;
    final Long totPointForCharge = 10L+amount;
    final Long totPointForUse = 10L-amount;



    @DisplayName("포인트 조회 테스트")
    // 단위 테스트
    @Test
    public void selectByIdTest() {
        // given
        given(userPointRepository.selectById(userPoint.id())).willReturn(userPoint.toDomain());
        //when
        UserPointDomain result = pointService.selectById(id);
        //then
        Assertions.assertEquals(userPoint.toDomain().getId(), result.getId());
    }

    @Test
    @DisplayName("포인트 충전 테스트")
    public void chargePointTest() {
        // given
        given(userPointRepository.update(userPoint.id(), totPointForCharge, TransactionType.CHARGE)).willReturn(userPoint.toDomain());
        given(userPointRepository.selectById(userPoint.id())).willReturn(userPoint.toDomain());
        // when
        Long pointOfUser = pointService.chargePoint(id,amount).getPoint();
        // then
        Assertions.assertEquals(userPoint.toDomain().getPoint(),pointOfUser);
    }

    @Test
    void usePointTest() {
        // given
        given(userPointRepository.update(userPoint.id(), totPointForUse, TransactionType.USE)).willReturn(userPoint.toDomain());
        given(userPointRepository.selectById(userPoint.id())).willReturn(userPoint.toDomain());
        // when
        Long pointOfUser = pointService.usePoint(id,amount).getPoint();
        // then
        Assertions.assertEquals(userPoint.toDomain().getPoint(),pointOfUser);
    }

    @Test
    void selectAllByUserIdTest() {
        PointHistory pointHistory = new PointHistory(cursor++,1,10,TransactionType.CHARGE,System.currentTimeMillis());
        PointHistoryDomain pointHistoryDomain = new PointHistoryDomain();
        pointHistoryDomain.setUserId(1);
        pointHistoryDomain.setAmount(10);
        pointHistoryDomain.setType(TransactionType.CHARGE);
        pointHistoryDomain.setUpdateMillis(System.currentTimeMillis());
        // given
        //given(pointHistoryRepository.selectAllByUserId(pointHistory.userId())).willReturn(pointHistoryDomain.getUserId());
        // when
        // the
    }
}