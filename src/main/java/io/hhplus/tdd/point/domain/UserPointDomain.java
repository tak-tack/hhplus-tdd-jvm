package io.hhplus.tdd.point.domain;
import com.fasterxml.jackson.core.JsonToken;
import lombok.Getter;
import lombok.Setter;

//Domain
@Getter
@Setter
public class UserPointDomain {
    private long id;
    private long point;
    private long updateMillis;

}

