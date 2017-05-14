/**
 * Created by udhay on 13/5/17.
 */

import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.*;

public class CountComparison {

    public Integer size;

    public int[] data;

    public CountComparison() {
        this.size = 0;
    }

    public Integer getPivotPrimitive(int[] data, int l, int r) {
        return data[l];
    }

    public int partition(int l, int r) {
        int ele = getPivotPrimitive(data, l, r);
        int i = l + 1;

        for (int j = l + 1; j <= r; j++) {
            if (data[j] < ele) {
                int t = data[j];
                data[j] = data[i];
                data[i] = t;
                i += 1;
            }
        }

        int t = data[l];
        data[l] = data[i - 1];
        data[i - 1] = t;


        if (size > 0) {
            size += r - l;
        } else {
            size = r - l;
        }

//        System.out.println();
//        System.out.println(ele + " is the pivot");
        //       System.out.println(size + " is the count");
//        for (int k : data) {
//            System.out.print(k + ", ");
//        }
//        System.out.println();
        return i - 1;
    }

    public void quickSort(int l, int r) {

        if (r - l >= 1) {
            Integer pivot = partition(l, r);
            quickSort(l, pivot - 1);
            quickSort(pivot + 1, r);
        }
    }

    public void quickSortRight(int l, int r) {

        if (r - l >= 1) {

            int t = data[l];
            data[l] = data[r];
            data[r] = t;

            Integer pivot = partition(l, r);
            quickSortRight(l, pivot - 1);
            quickSortRight(pivot + 1, r);
        }
    }

    public Integer getMedianPos(int l, int r) {

        if (r - l < 1) return -1;

        if (r - l == 1) return 0;

        if (r - l >= 2) {
            int data1[] = new int[3];
            data1[0] = data[l];
            data1[1] = data[r];
            data1[2] = data[l + (r - l) / 2];

            Arrays.sort(data1);

            System.out.println("All the elements");
            for (int k : data1) {
                System.out.print(k + " ,");
            }

            if (data1[1] == data[l])
                return l;
            if (data1[1] == data[r])
                return r;
            if (data1[1] == data[l + (r - l) / 2])
                return l + (r - l) / 2;
        }
        return -1;
    }

    public void quickSortMedian(int l, int r) {

        if (r - l >= 1) {

            int pos = getMedianPos(l, r);
            int t = data[l];
            data[l] = data[pos];
            data[pos] = t;

            Integer pivot = partition(l, r);
            quickSortMedian(l, pivot - 1);
            quickSortMedian(pivot + 1, r);
        }
    }

    public static void main(String[] args) {

        CountComparison cc = new CountComparison();
        //ArrayList<Integer> data = new ArrayList<Integer>(Arrays.asList(2, 4, 5, 1, 9, 10, 11, 8));
        ArrayList<Integer> data = new ArrayList<Integer>();

        int k = 0;
        try {
            Scanner in = new Scanner(new FileReader("/home/udhay/Downloads/QuickSort.txt"));

            while (in.hasNext()) {
                data.add(in.nextInt());
                k++;
//                if(k==1000) break;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int[] data1 = data.stream().mapToInt(i -> i).toArray();


        cc.size = 0;
        cc.data = data1;
        cc.quickSort(0, data1.length - 1);
        System.out.println(cc.size);

        cc.size = 0;
        cc.data = data1;
        cc.quickSortRight(0, data1.length - 1);
        System.out.println(cc.size);

        cc.size = 0;
        cc.data = data1;
        cc.quickSortMedian(0, data1.length - 1);
        System.out.println(cc.size);

        //Left : 162085
        //Right : 164123
        //median : 138382

    }
}
