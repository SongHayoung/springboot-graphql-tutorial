package com.sumfi.graphql.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadUtil {
    public static void ThreadStop(int second) {
        try {
            log.info("Stop {} Thread {} seconds", Thread.currentThread().getName(), second);
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
    }
}
