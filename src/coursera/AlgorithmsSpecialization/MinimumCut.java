package coursera.AlgorithmsSpecialization;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class MinimumCut {


    public static void main(String[] args) {

        List<Integer> v1_global = new ArrayList<Integer>();
        List<Integer> v2_global = new ArrayList<Integer>();
        try {

            Scanner in = new Scanner(new FileReader("data/KargeMinCut.txt"));

            while (in.hasNextLine()) {

                Scanner line = new Scanner(in.nextLine());
                Integer key = line.nextInt();

                while (line.hasNext()) {
                    v1_global.add(key);
                    v2_global.add(line.nextInt());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int noOfTimes = 0;

        int globalminimumCut = Integer.MAX_VALUE;
        do {

            List<Integer> v1 = new ArrayList<Integer>(v1_global);
            List<Integer> v2 = new ArrayList<Integer>(v2_global);

            Set<Integer> sv1 = new HashSet();
            Set<Integer> sv2 = new HashSet();

            do {


                if (noOfTimes > 100) {
                    System.out.print("");
                }

                if (noOfTimes > 900) {
                    System.out.print("");
                }

                if (noOfTimes > 10000) {
                    System.out.print("");
                }

                // Randomly pick an edge.
                // Return 0 to 1.
                int rkey = (int) Math.floor(Math.random() * v1.size());


                //Now we will replace v1[rkey] with v2[rkey] in v1.

                int node = v1.get(rkey);
                int replaceNode = v2.get(rkey);

//                System.out.print("Going to replace " + node + " with " + replaceNode);
                if (noOfTimes > 10000) {
                    System.out.print("");
                }

                for (int i = 0; i < v1.size(); i++) {
                    if (v1.get(i).equals(node)) {
                        v1.set(i, replaceNode);
                    }

                    if (v2.get(i).equals(node)) {
                        v2.set(i, replaceNode);
                    }
                }

                Iterator<Integer> v2_iterator = v2.iterator();
                for (Iterator<Integer> v1_iterator = v1.iterator(); v1_iterator.hasNext(); ) {
                    Integer v1_value = v1_iterator.next();
                    Integer v2_value = v2_iterator.next();

                    if (v1_value == replaceNode && v2_value == replaceNode) {
                        v1_iterator.remove();
                        v2_iterator.remove();
                    }
                }

                sv1.clear();
                sv2.clear();
                for (int i = 0; i < v1.size(); i++) {
                    sv1.add(v1.get(i));
                    sv2.add(v2.get(i));
                }
            } while (sv1.size() > 2 || sv2.size() > 2);

            noOfTimes++;

            Integer tmp = v1.get(0);
            int minimumCut = 0;
            for (int i = 0; i < v1.size(); i++) {
                if (v1.get(i).equals(tmp)) {
                    minimumCut++;
                }
            }
            if (minimumCut < globalminimumCut) {
                globalminimumCut = minimumCut;
            }

        } while (noOfTimes < 1000);

        System.out.println("global minimum Cut " + globalminimumCut);
    }
}
