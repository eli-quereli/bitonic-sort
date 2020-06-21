package Threading;

public class MyThread extends Thread {

    int[] arr;
    int start;
    int end;
    Dummy dummy = new Dummy();

    /**
     * constructor
     * @param arr the array the thread is supposed to work on
     * @param start the index of the array that the thread starts at
     * @param end the index of the array that the thread ends at
     */
    public MyThread(int[] arr, int start, int end) {

        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    public void run() {

        //dummy.increaseArray(arr, start, end);
        dummy.reverseArray(arr, start, end);
    }

}
