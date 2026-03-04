import java.util.*;

public class UsernameAvailabilityChecker {

    private Map<String,Integer> users = new HashMap<>();
    private Map<String,Integer> attempts = new HashMap<>();

    public boolean checkAvailability(String username){
        attempts.put(username, attempts.getOrDefault(username,0)+1);
        return !users.containsKey(username);
    }

    public void registerUser(String username,int id){
        users.put(username,id);
    }

    public List<String> suggestAlternatives(String username){
        List<String> list=new ArrayList<>();
        list.add(username+"1");
        list.add(username+"2");
        list.add(username.replace("_","."));
        return list;
    }

    public String getMostAttempted(){
        String user="";
        int max=0;

        for(String u:attempts.keySet()){
            if(attempts.get(u)>max){
                max=attempts.get(u);
                user=u;
            }
        }
        return user+" ("+max+" attempts)";
    }

    public static void main(String[] args){

        UsernameAvailabilityChecker u=new UsernameAvailabilityChecker();

        u.registerUser("john_doe",1);

        System.out.println(u.checkAvailability("john_doe"));
        System.out.println(u.checkAvailability("jane_smith"));

        System.out.println(u.suggestAlternatives("john_doe"));

        u.checkAvailability("admin");
        u.checkAvailability("admin");

        System.out.println(u.getMostAttempted());
    }
}