package com.anle.listener;

import com.anle.event.TestEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author AnnLee
 * @version 1.0
 * @date 2021-10-12 16:42
 */
public class TestListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof TestEvent){
            TestEvent test = (TestEvent)event;
            test.print();
        }
    }
}
