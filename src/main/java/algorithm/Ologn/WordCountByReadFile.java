package algorithm.Ologn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO
 *
 * @author fengtingting
 * @version 1.0
 * @date 2020/10/11 10:10
 */
public class WordCountByReadFile {

    //线程池用于多线程处理任务
    private static ThreadPoolExecutor threadPool =
            new ThreadPoolExecutor(10, 10, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<>(200)/*避免过大的队列*/);

    public void count(File[] files) {
        List< Future<Map<String, Integer>>> allFutureList = new ArrayList<>();
        for (File file : files) { //提交全部任务
            Future<Map<String, Integer>> future = threadPool.submit(buildTask(file));
            allFutureList.add(future);
        }
        //等待任务全部执行完，单线程汇总，避免竞态，直接使用tree排序，相比小顶堆的速度不见得要慢，因为二分比较，次数也少
        TreeMap<String, Integer> totalWordCountMap = new TreeMap<>(Comparator.reverseOrder()/*倒序*/); //直接使用treemap排序,主要借用二分，使用红黑树有点大材小用
        for( Future<Map<String, Integer>> future : allFutureList){
            try {
                Map<String, Integer> singleWordCountMap = future.get();
                for(String key : singleWordCountMap.keySet()){
                    Integer totalCount = totalWordCountMap.get(key);
                    if(totalCount == null){
                        totalWordCountMap.put(key, singleWordCountMap.get(key));
                    } else {
                        totalCount = totalCount +singleWordCountMap.get(key);
                        totalWordCountMap.put(key, totalCount);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int i = 0;
        Iterator<Map.Entry<String, Integer>> iterator = totalWordCountMap.entrySet().iterator();
        while (i<100 && iterator.hasNext()) {
            Map.Entry<String, Integer> next = iterator.next();
            System.out.println(next.getKey() + " : "+ next.getValue());
            i++;
        }

    }

    private Callable<Map<String, Integer>> buildTask(File file) { //每个文件单独一个计数器，避免竞态
        Callable<Map<String, Integer>> callable = new Callable<Map<String, Integer>>() {
            @Override
            public Map<String, Integer> call() throws Exception {

                Map<String, Integer> singleWordCountMap = new HashMap<>();
                FileInputStream inputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = null;
                while ((line = reader.readLine()) != null) { //先默认一行是一个完整的单词
                    Integer count = singleWordCountMap.get(line);
                    if(Objects.isNull(count)){
                        count = 1;
                    } else {
                        count++;
                    }
                    singleWordCountMap.put(line,count);
                }
                return singleWordCountMap;
            }
        };
        return callable;
    }
}
