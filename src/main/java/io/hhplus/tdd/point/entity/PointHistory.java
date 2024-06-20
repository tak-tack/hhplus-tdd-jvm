package io.hhplus.tdd.point.entity;

import io.hhplus.tdd.point.TransactionType;
import io.hhplus.tdd.point.domain.PointHistoryDomain;
import org.springframework.beans.BeanUtils;

// entity
public record PointHistory(
        long id,
        long userId,
        long amount,
        TransactionType type,
        long updateMillis
) {
    public PointHistoryDomain toDomain(){
        PointHistoryDomain pointHistoryDomain = new PointHistoryDomain();
        BeanUtils.copyProperties(this, pointHistoryDomain);
        return pointHistoryDomain;
    }



}
