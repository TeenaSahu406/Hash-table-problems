
public class ParkingLotHashTableSystem{

    String[] table=new String[10];

    int hash(String plate){
        return Math.abs(plate.hashCode())%table.length;
    }

    void park(String plate){

        int i=hash(plate);

        while(table[i]!=null)
            i=(i+1)%table.length;

        table[i]=plate;

        System.out.println("Parked at spot "+i);
    }

    void exit(String plate){

        for(int i=0;i<table.length;i++){

            if(plate.equals(table[i])){
                table[i]=null;
                System.out.println("Exited spot "+i);
                return;
            }
        }
    }

    public static void main(String[] args){

        ParkingLotHashTableSystem p=new ParkingLotHashTableSystem();

        p.park("ABC123");
        p.park("XYZ999");

        p.exit("ABC123");
    }
}