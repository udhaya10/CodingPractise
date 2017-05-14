import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static List<Integer> data = new ArrayList();


    /**
     * https://www.youtube.com/watch?v=f_f9_GYcPSY
     * Watch all the 5 videos.
     * <p>
     * Running time O(n)
     * Space O(n) - Guessing
     *
     * @param arr
     * @param k
     * @return
     */
    public static int findKthLargestItem(List<Integer> arr, int k) {

        int x1 = (int) (Math.random() * arr.size());
        if (arr.size() == 1) return arr.get(x1);

        List<Integer> arrL = arr.stream().filter(e -> e < arr.get(x1)).collect(Collectors.toList());
        List<Integer> arrR = arr.stream().filter(e -> e > arr.get(x1)).collect(Collectors.toList());

        if (arrL.size() == k - 1) return arr.get(x1);
        if (arrL.size() > k - 1) return findKthLargestItem(arrL, k);
        if (arrL.size() < k - 1) return findKthLargestItem(arrR, k - arrL.size() - 1);
        return 0;
    }


    public static void main(String[] args) {

        data = new ArrayList<Integer>(Arrays.asList(2, 4, 5, 1, 9, 10, 11, 8));

        System.out.println(findKthLargestItem(data, (data.size() / 2)));
    }
}
