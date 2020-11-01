package java_normal.collection;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO
 *
 * @author fengtingting
 * @version 1.0
 * @date 2020/10/31 17:59
 */
public class HashMap {

    public static void main(String[] args) {
        Map<String ,Object> map = new java.util.HashMap<>(10);

        map.put("a", 1);
        map.put("a", 2);
        int res = (int)map.get("a");
        System.out.println(res);

        ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap(10);
        concurrentHashMap.put("a",1);

        int conRes = (int)concurrentHashMap.get("a");
        System.out.println(conRes);

        Map<String, Object> hashTable = new Hashtable<>();
        hashTable.put("a",1);
        int tableTes = (int)hashTable.get("a");
        System.out.println(tableTes);
    }
}
