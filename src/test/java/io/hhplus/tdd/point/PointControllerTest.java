package io.hhplus.tdd.point;

import io.hhplus.tdd.point.repository.PointHistoryRepository;
import io.hhplus.tdd.point.repository.UserPointRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.context.annotation.Description;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest // Spring 통합테스트
@AutoConfigureMockMvc
class PointControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserPointRepository userPointRepository;

    @Autowired
    private PointHistoryRepository pointHistoryRepository;

    private final long id = 1;
    private final long userId = 1;
    private final long amount = 20;
    private final long point = 50;

    @BeforeEach
    @DisplayName("BeforeEach")
    void setUp() {
        userPointRepository.update(id, point,TransactionType.CHARGE);
        pointHistoryRepository.save(userId, 10, TransactionType.CHARGE, System.currentTimeMillis());
        pointHistoryRepository.save(userId, 30, TransactionType.USE, System.currentTimeMillis());
    }

    @Test
    @Description("User Id로 포인트 조회 API 테스트")
    @DisplayName("포인트 조회")
    void getPoint() throws Exception {
        mockMvc.perform(get("/point/{Id}",id)  // get :  conntroller http메서드
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.point").value(point))
                .andExpect(jsonPath("$.updateMillis").value(not(0)));
    }

    @Test
    @Description("User Id로 포인트 충전, 사용 내역 API 테스트")
    @DisplayName("포인트 내역 조회")
    void getPointHistory() throws Exception {
        mockMvc.perform(get("/point/{Id}/histories",userId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId").value(userId))
                .andDo(print());
                //.andExpect(jsonPath("$.size()").value()); List 사이즈
    }

    // id값 null 에 대한
    @Test
    @Description("User Id와 amount 로 충전, 입력 API 테스트")
    @DisplayName("포인트 충전")
    void pointCharge() throws Exception {
        mockMvc.perform(patch("/point/{Id}/charge",id)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(amount)))
                .andExpect(status().isOk()) //
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.point").value(point + amount));

    }

    @Test
    @Description("User Id와 amount 로 사용, 입력 API 테스트")
    @DisplayName("포인트 사용")
    void pointUse() throws Exception{
        mockMvc.perform(patch("/point/{Id}/use",id)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.point").value(point - amount));

    }


}