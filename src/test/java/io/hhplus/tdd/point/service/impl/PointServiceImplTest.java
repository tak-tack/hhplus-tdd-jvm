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
import static org.mockito.Mockito.lenient;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.ArrayList;
import java.util.List;
// Junit5 기능을 사용하고 Test 에서 가짜 객체를 사용
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class PointServiceImplTest {

    //Stub 처리
    @Mock // Repository 연결 해제, Mock 선언
    private UserPointRepository userPointRepository;

    @Mock
    private PointHistoryRepository pointHistoryRepository;

    @InjectMocks
    private PointServiceImpl pointService;

    // entity 객체생성
    final UserPoint userPoint = new UserPoint(1,10,System.currentTimeMillis());

    final Long id = 1L;
    final Long amount = 30L;
    final Long totPointForCharge = 10L+amount;
    final Long totPointForUse = 10L-amount;

    // 단위 테스트
    @Test
    @DisplayName("포인트 조회 테스트")
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
        // given
        List<PointHistoryDomain> list = new ArrayList<>();
        PointHistory pointHistory = new PointHistory(1,1,10,TransactionType.USE,System.currentTimeMillis());
        // when
        list.add(new PointHistoryDomain(1,1,10,TransactionType.CHARGE,System.currentTimeMillis()));
          lenient().when(pointHistoryRepository.selectAllByUserId(pointHistory.userId())).thenReturn(list);
        // then
        Assertions.assertEquals(list.size(),1 );
    }
}