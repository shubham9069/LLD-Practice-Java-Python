import java.util.*;

public class DsaPractice {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String n = sc.next(); 





        char[] arr = n.toCharArray(); 
        for(int i = 0; i < arr.length; i++) {
            int val = arr[i] - '0';
            int invert = 9-val;

            if (i == 0 && invert == 0) {
                continue;
            }
            if (invert < val) {
                arr[i] = (char)(invert+'0');
            }
        }
        System.out.println(new String(arr));
        
    }
    
}
