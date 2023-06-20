package com.cy.douyin.thread;

import java.util.concurrent.*;

/**
 * @author 86147
 * create  20/6/2023 下午7:55
 */
public class CustomThreadPool {
    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void execute(Runnable r) {
        executorService.execute(r);
    }

    public static <T> T submit(Callable<T> c) {
        Future<T> submit = executorService.submit(c);
        T t =null;
        try {
            t = submit.get();
        }catch (RuntimeException | InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
        return t;
    }





}
