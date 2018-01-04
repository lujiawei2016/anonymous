package com.anonymous.start;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 启动dubbo
 * @author  lujiawei
 * @version V1.0
 * @date    2017年12月8日上午11:13:08
 */
public class StartDubbo {
	
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		ac.start();
		System.out.println("启动成功............");
		System.in.read();
	}
}
