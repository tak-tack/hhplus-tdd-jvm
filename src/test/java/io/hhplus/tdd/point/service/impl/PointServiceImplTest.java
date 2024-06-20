package io.hhplus.tdd.point.service.impl;

import io.hhplus.tdd.point.domain.UserPointDomain;
import io.hhplus.tdd.point.entity.UserPoint;
import io.hhplus.tdd.point.repository.UserPointRepository;


import java.util.Optional; //

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.any;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

@ExtendWith(MockitoExtension.class) // Junit5 기능을 사용하고, Test에서 가짜 객체를 사용
@AutoConfigureMockMvc
class PointServiceImplTest {

    //Stub 처리

    @Mock // Repository 연결 해제
    private UserPointRepository userPointRepository;
    @InjectMocks // interface 타입은 사용불가
    private PointServiceImpl pointService;
    final Long id = 10L;

    @Test
    public void selectById() {
        // given
        // entity 객체생성
        UserPoint userPoint = new UserPoint(10,10,System.currentTimeMillis());
        given(userPointRepository.selectById(userPoint.id())).willReturn(userPoint.toDomain());
        //when
        UserPointDomain result = pointService.selectById(id);
        //then
        Assertions.assertNull(result);
        Assertions.assertEquals(userPoint.toDomain().getId(), result.getId());
    }

    @Test
    void chargePoint() {
        // given
        //given(user)
        // when
        // then
    }

    @Test
    void usePoint() {
        // given
        // when
        // then
    }

    @Test
    void selectAllByUserId() {
        // given
        // when
        // the
    }
}