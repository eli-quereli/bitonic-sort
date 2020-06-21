package Threading;

public class Main {

    public static void main(String[] args) {

        //int[] inputArr = {0,1,2,3,4,5,6,7};
        int[] inputArr = {0,0,0,0,1,1,1,1};

        MyThread[] myThreads = createThreads(inputArr);

        System.out.println("Printing input array: ");
        printArray(inputArr);


        for(int i = 0; i < myThreads.length; i++) {

            MyThread thread = myThreads[i];
            thread.start();
        }


        /*int cores = 2;
        int dist = inputArr.length / cores;

        MyThread t1 = new MyThread(inputArr, 0, dist);
        MyThread t2 = new MyThread(inputArr, dist, inputArr.length);

        System.out.println("starting threads");
        t1.start();
        t2.start();

        try {
          t1.join();
          t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        System.out.println("all threads have finished");

        System.out.println("Printing changed array: ");
        printArray(inputArr);
    }

    public static MyThread[] createThreads(int[] arr) {

        int cores = Runtime.getRuntime().availableProcessors();
        MyThread[] myThreads = new MyThread[cores];

        MyThread thread;
        System.out.println(cores);
        int distance = arr.length/cores;
        System.out.println(distance);
        int start = 0;
        int end = distance;

        for (int i = 0; i < cores; i++) {

            thread = new MyThread(arr, start, end);
            myThreads[i] = thread;
            System.out.println("start index of " + thread.getName() + " = " + start);
            System.out.println("end index of " + thread.getName() + " = " + end);
            start += distance;
            end += distance;
        }

        return myThreads;
    }


    /**
     * auxiliary method to print an array from start to end
     * @param arr array to be printed
     */
    public static void printArray(int[] arr) {

        for(int i = 0; i < arr.length; i++) {

            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }


}