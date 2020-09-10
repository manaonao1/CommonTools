package com.test.mylibrary.new_utils;

import android.support.annotation.NonNull;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2018/12/3
 * 线程池创建类
 *
 * @author zyf
 */
public class ThreadFactory {

    private static int threadCount = 0;

    public static class Builder {
        /**
         * 创建线程池默认值
         */
        private int corePoolSize = 1;
        private int maximumPoolSize = 1;
        private int keepAliceTime = 0;
        private int commanderQueueSize = 1;

        public Builder corePoolSize(int corePoolSize) {
            this.corePoolSize = corePoolSize;
            return this;
        }

        public Builder maximumPoolSize(int maximumPoolSize) {
            this.maximumPoolSize = maximumPoolSize;
            return this;
        }

        public Builder keepAliceTime(int keepAliceTime) {
            this.keepAliceTime = keepAliceTime;
            return this;
        }

        public Builder commanderQueueSize(int commanderQueueSize) {
            this.commanderQueueSize = commanderQueueSize;
            return this;
        }


        public ThreadPoolExecutor build() {
            return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliceTime, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(commanderQueueSize), new java.util.concurrent.ThreadFactory() {
                @Override
                public Thread newThread(@NonNull Runnable r) {
                    return new Thread(r, "ThreadFactory" + threadCount++);
                }
            });
        }
    }


}
