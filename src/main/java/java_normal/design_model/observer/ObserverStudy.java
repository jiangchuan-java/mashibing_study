package java_normal.design_model.observer;

import java.util.LinkedList;
import java.util.List;

/**
 * TODO
 *
 * @author fengtingting
 * @version 1.0
 * @date 2020/11/21 12:49
 */
public class ObserverStudy {

    public static class Hospital {
        List<Listener> listeners = new LinkedList<>();
        public void addListener(Listener listener) {
            this.listeners.add(listener);
        }

        public void callNext(int num) {
            CallNumEvent event = new CallNumEvent();
            event.callNum = num;
            event.source = this;
            listeners.forEach(listener -> listener.listen(event));
        }
    }

    public static class CallNumEvent { //叫号事件
        public int callNum; // 号码
        private Hospital source; //事件源
    }

    public static interface Listener { //监听器
        public void listen(CallNumEvent event);
    }

    public static class People1 implements Listener {
        int num = 1;
        @Override
        public void listen(CallNumEvent event) {
            if (event.callNum == 1)
                System.out.println("我在这呢，我是1号");
        }
    }
    public static class People2 implements Listener {
        int num = 2;
        @Override
        public void listen(CallNumEvent event) {
            if (event.callNum == 2) {
                System.out.println("我在这呢，我是2号");
                //针对事件源可以做些处理
                Hospital source = event.source;
            }
        }
    }

    public static void main(String[] args) {
        Hospital hospital = new Hospital();
        People1 people1 = new People1();
        People2 people2 = new People2();
        hospital.addListener(people1);
        hospital.addListener(people2);
        hospital.callNext(1);
    }
}
