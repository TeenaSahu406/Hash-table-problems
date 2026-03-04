import java.util.*;

public class SearchAutocompleteSystem{

    Map<String,Integer> freq=new HashMap<>();

    void addQuery(String q){
        freq.put(q,freq.getOrDefault(q,0)+1);
    }

    List<String> search(String prefix){

        List<String> list=new ArrayList<>();

        for(String q:freq.keySet())
            if(q.startsWith(prefix))
                list.add(q);

        list.sort((a,b)->freq.get(b)-freq.get(a));

        return list.subList(0,Math.min(3,list.size()));
    }

    public static void main(String[] args){

        SearchAutocompleteSystem s=new SearchAutocompleteSystem();

        s.addQuery("java tutorial");
        s.addQuery("javascript");
        s.addQuery("java download");

        System.out.println(s.search("jav"));
    }
}