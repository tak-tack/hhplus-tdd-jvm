package io.hhplus.tdd.point.domain;

import io.hhplus.tdd.point.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Domain
@Getter
@Setter
@AllArgsConstructor // 모든인자만들어주는 생성자 주입, 생성자를 만들떄 받을 인자값에대해서 모든값을 받는다
@NoArgsConstructor  //인자값에 아무것도 안받는 생성자 생성
public class PointHistoryDomain {
    long id;
    long userId;
    long amount;
    TransactionType type;
    long updateMillis;

}
