package io.hhplus.tdd;


import org.springframework.stereotype.Component;


import java.util.concurrent.ConcurrentHashMap;

// 동시성 제어 테이블
@Component
public class ConcurrencyController {
    // Map<userId, boolean>
    private final ConcurrentHashMap<Long, Boolean> hashMap = new ConcurrentHashMap<>();

    public boolean getController(long key){ // key = userId
        return hashMap.getOrDefault(key,false); // key가 존재할 경우 true, 존재하지않을 경우 false
    }

    public void startController(long key){
        hashMap.put(key,true);
    }

    public void endController(long key){
        hashMap.remove(key);
    }

    public boolean waitController(long key){
        for(int i = 0; i < 10; i++){
            if(getController(key)){ // userId 확인 true
                try {
                    Thread.sleep(100); // 대기
                }catch(InterruptedException e) { // 예외 처리 스레드 종료
                    throw new RuntimeException();
                }
            }else{ // userId 미확인 false
                return false; // 수행 미허용
            }
        }
        return true; // 수행 허용
    }

}
