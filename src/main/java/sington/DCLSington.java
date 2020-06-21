package sington;

/**
 * Created by fengtingting on 2020/6/17.
 */
public class DCLSington {

    private DCLSington(){

    }

    private static DCLSington instance;

    public static DCLSington getInstance(){
        if(instance == null){
            synchronized (DCLSington.class){
                instance = new DCLSington();
            }
        }
        return instance;
    }
}
