package com.anle.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author AnnLee
 * @version 1.0
 * @date 2021-10-12 16:39
 */
public class TestEvent extends ApplicationEvent {


	private static final long serialVersionUID = -5647287363924596854L;
	public String msg;

    public TestEvent (Object source) {
        super(source);
    }

    public TestEvent (Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public void print() {
        System.out.println(msg);
    }
}
