import java.util.*;

public class MultiLevelCacheSystem{

    LinkedHashMap<String,String> L1=
        new LinkedHashMap<>(100,0.75f,true);

    Map<String,String> L2=new HashMap<>();

    String getVideo(String id){

        if(L1.containsKey(id)){
            System.out.println("L1 HIT");
            return L1.get(id);
        }

        if(L2.containsKey(id)){
            System.out.println("L2 HIT");

            L1.put(id,L2.get(id));

            return L2.get(id);
        }

        System.out.println("DB HIT");

        String data="VideoData";

        L2.put(id,data);

        return data;
    }

    public static void main(String[] args){

        MultiLevelCacheSystem c=new MultiLevelCacheSystem();

        c.getVideo("v1");
        c.getVideo("v1");
    }
}