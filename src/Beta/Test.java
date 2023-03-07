package Beta;

import java.util.ArrayList;
import java.util.Collections;

public class Test {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list, 1, 2, 3, 4, 5);
        list.stream().filter(i -> i % 2 == 0).forEach(System.out::println);
    }
}
