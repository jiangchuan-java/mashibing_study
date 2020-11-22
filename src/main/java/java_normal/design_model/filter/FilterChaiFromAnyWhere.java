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
public class FilterChaiFromAnyWhere {

    public static interface Filter {
        public void doFilter(FilterChain chain);
    }

    public static class FilterChain {
        List<Filter> filters = new LinkedList<>();
        private int currentIndex = 0;

        private void complete(Filter filter) {
            int nextIndex = filters.indexOf(filter) + 1;
            if(nextIndex > filters.size()-1){
                return;
            }
            Filter nextFilter = filters.get(nextIndex);
            nextFilter.doFilter(this);
        }

        public void addFilter(Filter filter) {
            this.filters.add(filter);
        }

    }
    public static class AFilter implements Filter {
        @Override
        public void doFilter(FilterChain chain) {
            System.out.println("A");
            chain.complete(this);;
        }
    }
    public static class BFilter implements Filter {
        @Override
        public void doFilter(FilterChain chain) {
            System.out.println("B");
            chain.complete(this);
        }
    }
    public static class CFilter implements Filter {
        @Override
        public void doFilter(FilterChain chain) {
            System.out.println("C");
            chain.complete(this);
        }
    }

    public static void main(String[] args) {
        FilterChain chain = new FilterChain();
        AFilter aFilter = new AFilter();
        BFilter bFilter = new BFilter();
        CFilter cFilter = new CFilter();
        chain.addFilter(aFilter);
        chain.addFilter(bFilter);
        chain.addFilter(cFilter);

        bFilter.doFilter(chain);
    }
}
