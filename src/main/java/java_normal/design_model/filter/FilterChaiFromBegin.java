package java_normal.design_model.filter;

import java.util.LinkedList;
import java.util.List;

/**
 * TODO
 *
 * @author fengtingting
 * @version 1.0
 * @date 2020/11/18 22:31
 */
public class FilterChaiFromBegin {

    public static interface Filter {
        public void doFilter(FilterChain chain);
    }
    public static class FilterChain {
        List<Filter> filters = new LinkedList<>();
        private int currentIndex = 0;

        private void doNext() {
            if(currentIndex > filters.size()-1){
                return;
            }
            Filter filter = filters.get(currentIndex);
            currentIndex ++;
            filter.doFilter(this);
        }

        public void addFilter(Filter filter) {
            this.filters.add(filter);
        }

    }

    public static class AFilter implements Filter {
        @Override
        public void doFilter(FilterChain chain) {
            System.out.println("A");
            chain.doNext();
        }
    }

    public static class BFilter implements Filter {
        @Override
        public void doFilter(FilterChain chain) {
            System.out.println("B");
            chain.doNext();
        }
    }

    public static class CFilter implements Filter {
        @Override
        public void doFilter(FilterChain chain) {
            System.out.println("C");
            chain.doNext();
        }
    }

    public static void main(String[] args) {
        FilterChain chain = new FilterChain();
        chain.addFilter(new AFilter());
        chain.addFilter(new BFilter());
        chain.addFilter(new CFilter());
        chain.doNext(); //执行责任链
    }
}
