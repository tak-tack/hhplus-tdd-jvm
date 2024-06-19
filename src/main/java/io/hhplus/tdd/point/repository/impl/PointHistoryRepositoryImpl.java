package io.hhplus.tdd.point.repository.impl;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.point.TransactionType;
import io.hhplus.tdd.point.domain.PointHistoryDomain;
import io.hhplus.tdd.point.entity.PointHistory;
import io.hhplus.tdd.point.repository.PointHistoryRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PointHistoryRepositoryImpl implements PointHistoryRepository {

    private final PointHistoryTable pointHistoryTable;

    public PointHistoryRepositoryImpl(PointHistoryTable pointHistoryTable) {
        this.pointHistoryTable = pointHistoryTable;
    }

    @Override
    public List<PointHistoryDomain> selectAllByUserId(long userId){
        List<PointHistory> pointHistory = pointHistoryTable.selectAllByUserId(userId);
        return pointHistory.stream().map(PointHistory::toDomain).toList();
        // 현구조는 포인트히스토리에 toDomian 하지만 List에서 담아버린다
        // toDomain은 도메인으로 보내는것
        // 1. 반복문 돌려서 List 하나하나를 LIST<Domain>d으로 변경
        // 2. stream 위 1번을 대체하는 간편 형식
        //return pointHistory.stream().map(pointHistory1 -> return pointHistory1.).toList();

        // steam().map(): 리스트안에 있는값 각각에 적용하는 함수실행시키고 반환값을 List에 주는것
        // map안에 함수를 넣음 map() 안에 람다식..
        // java. util. function. Function<? super T, ? extends R> mapper 를 위에 껄로 대체됨
        // 왼쪽이 파람, 오른쪽 반환값 정의
        // pointHistory1 인이유 pointHistory가 이미 있어서
    }

    @Override
    public PointHistoryDomain save(long userId, long amount, TransactionType type, long updateMillis){
        PointHistory pointHistory = pointHistoryTable.insert(userId, amount, type, updateMillis);
        return pointHistory.toDomain();
    }



}
