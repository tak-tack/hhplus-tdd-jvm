package io.hhplus.tdd.point.service.impl;

import io.hhplus.tdd.point.domain.UserPointDomain;
import io.hhplus.tdd.point.repository.impl.UserPointRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class PointServiceImplTest {

    @Autowired
    private UserPointRepositoryImpl userPointRepositoryImpl;
    @Autowired
    private PointServiceImpl pointServiceImpl;

    @Test
    public void selectById() {
        Long id = 1L;
        UserPointDomain userPointDomain = new UserPointDomain();

        when(userPointRepositoryImpl.selectById(id));
        UserPointDomain result = pointServiceImpl.selectById(id);

        assertNotNull(result);
        assertEquals(userPointDomain, result);
    }
}