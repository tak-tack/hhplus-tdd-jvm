package io.hhplus.tdd.point.domain;

import io.hhplus.tdd.point.TransactionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PointHistoryDomain {
    long id;
    long userId;
    long amount;
    TransactionType type;
    long updateMillis;
}
