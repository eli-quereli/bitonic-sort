package Threading;

import java.lang.reflect.Array;

public class Dummy {

    public Dummy() {
    }

    public int[] increaseArray(int[] arr, int start, int end) {

        for(int i = start; i < end; i++) {

            arr[i] = (Array.getInt(arr, i) + 1 ) ;
        }
        return arr;
    }


    public int[] reverseArray(int[] arr, int start, int end) {

        for(int i = start; i < end; i++) {

            if(arr[i] == 1) {
                arr[i] = 0;
            }

            else /* arr[i] == 0*/ {
                arr[i] = 1;
            }

        }
        return arr;
    }


}
