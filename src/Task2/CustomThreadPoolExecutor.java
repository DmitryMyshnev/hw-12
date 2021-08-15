package Task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomThreadPoolExecutor extends ThreadPoolExecutor {

    public CustomThreadPoolExecutor(int nThread) {
        super(nThread, nThread, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    }

    @Override
    public void execute(Runnable command) {
        int repeat = command.getClass().getAnnotation(Repeat.class).value();
        for (int i = 0; i < repeat; i++) {
            super.execute(command);
        }
    }
}

