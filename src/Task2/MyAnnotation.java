package Task2;

import Task2.CustomThreadPoolExecutor;

public class MyAnnotation {
    public static void main(String[] args) {
        CustomThreadPoolExecutor customPool = new CustomThreadPoolExecutor(10);
        customPool.execute(new MyRunnable());
        customPool.shutdown();
    }
}
