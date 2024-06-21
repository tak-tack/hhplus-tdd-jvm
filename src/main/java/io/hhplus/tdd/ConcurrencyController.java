package io.hhplus.tdd;


import org.springframework.stereotype.Component;


import java.util.concurrent.ConcurrentHashMap;

// 동시성 제어 테이블
@Component
public class ConcurrencyController {
    // Map<userId, boolean>
    private final ConcurrentHashMap<Long, Boolean> hashMap = new ConcurrentHashMap<>();

    public boolean getController(long key){
        return hashMap.getOrDefault(key,false);
    }

    public void startController(long key){
        hashMap.put(key,true);
    }

    public void endController(long key){
        hashMap.remove(key);
    }

    public boolean waitController(long key){
        for(int i = 0; i < 10; i++){
            if(getController(key)){
                try {
                    Thread.sleep(100);
                }catch(InterruptedException e) {
                    throw new RuntimeException();
                }
            }else{
                return false;
            }
        }
        return true;
    }

}
