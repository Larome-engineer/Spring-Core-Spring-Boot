package SpringBootJDBCTemplate.aop;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class LoggerMap {
    private final Map<String, Long> executionMap = new HashMap<>();

    public void add(String className, Long time) {
        executionMap.put(className, time);
    }

    public void get() {
        System.out.println(sortByValue(executionMap));
    }

    private static <K, V extends Comparable<? super V>> Map<K, V> sortByValue (Map<K, V> map) {
        return map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
}
