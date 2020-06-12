package com.tradeshift.hornetqclient.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineAppRunner implements CommandLineRunner {
    @Autowired
    private HornetQBrowserQueue hornetQBrowserQueue;

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            hornetQBrowserQueue.iterateOverQueueMessages();
        }
    }
}
