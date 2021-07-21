public class NEt { 

    public static void main(String[] args) {
        int [] alex = {1,2,3,4};

        for(int i = alex.length-1 ; i> (alex.length-1) /2 ; i--){
            int tmp = alex[i];
            alex[i] =  alex[(alex.length-1)- i];
            alex[alex.length-1 - i] = tmp;            

        }
        for (int i : alex) {
            System.out.println(i);
        }
    }
}