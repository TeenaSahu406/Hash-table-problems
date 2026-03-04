import java.util.*;

public class FinancialTransactionTwoSum{

    static void findTwoSum(int[] arr,int target){

        Map<Integer,Integer> map=new HashMap<>();

        for(int x:arr){

            int comp=target-x;

            if(map.containsKey(comp)){
                System.out.println(comp+" + "+x);
                return;
            }

            map.put(x,1);
        }
    }

    public static void main(String[] args){

        int[] a={500,300,200};

        findTwoSum(a,500);
    }
}