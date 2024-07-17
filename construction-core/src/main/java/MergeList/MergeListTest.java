package MergeList;

import java.util.*;
import java.util.stream.Collectors;

public class MergeListTest {
    public static void main(String[] args) {
        List<Map<String, Integer>> list = new ArrayList<>();
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("a", 1);
        map1.put("b", 2);

        Map<String, Integer> map2 = new HashMap<>();
        map2.put("a", 3);
        map2.put("c", 4);

        list.add(map1);
        list.add(map2);

        Map<String, Integer> mergedMap = list.stream()
                .flatMap(map -> map.entrySet().stream())
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (v1, v2) -> v1
                        ));
        System.out.println(mergedMap);
    }
}
