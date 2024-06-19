package io.hhplus.tdd.point.entity;

import io.hhplus.tdd.point.domain.UserPointDomain;
import org.springframework.beans.BeanUtils;

// entity
public record UserPoint(
        long id, //
        long point,
        long updateMillis
) {


    public static UserPoint empty(long id) {
        return new UserPoint(id, 0, System.currentTimeMillis());
    }

    public UserPointDomain toDomain() {
        UserPointDomain userPointDomain = new UserPointDomain();
        BeanUtils.copyProperties(this, userPointDomain);
        return userPointDomain;
    }
}
