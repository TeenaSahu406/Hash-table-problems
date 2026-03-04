import java.util.*;

class TokenBucket{

    int tokens;
    int max;
    long lastRefill;

    TokenBucket(int max){
        this.max=max;
        tokens=max;
        lastRefill=System.currentTimeMillis();
    }

    boolean allow(){

        long now=System.currentTimeMillis();

        if(now-lastRefill>3600000){
            tokens=max;
            lastRefill=now;
        }

        if(tokens>0){
            tokens--;
            return true;
        }

        return false;
    }
}

public class APIRateLimiter{

    Map<String,TokenBucket> clients=new HashMap<>();

    boolean check(String id){

        clients.putIfAbsent(id,new TokenBucket(5));

        return clients.get(id).allow();
    }

    public static void main(String[] args){

        APIRateLimiter r=new APIRateLimiter();

        for(int i=0;i<7;i++)
            System.out.println(r.check("client1"));
    }
}