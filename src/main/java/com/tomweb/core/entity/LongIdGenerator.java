package com.tomweb.core.entity;

import java.net.InetAddress;
import java.util.Random;

import org.springframework.stereotype.Component;

/**
 * Long型的ID生成器
 * @author Tom
 */
@Component("idGenerator")
public class LongIdGenerator implements IdGenerator<Long>{

	private static int counter = 0;
	private static final int ipRandomInt;
	private static final int jvmRandomInt;
	private static final int lastRandomInt;

	static {
		int IP;
		long JVM = System.currentTimeMillis();
		try {
			IP = toInt(InetAddress.getLocalHost().getAddress());
		} catch (Exception e) {
			IP = 0;
		}
		Random ipRandom = new Random(IP);
		Random jvmRandom = new Random(JVM);
		Random lastRandom = new Random();
		ipRandomInt = ipRandom.nextInt(800) + 100;
		jvmRandomInt = jvmRandom.nextInt(90) + 10;
		lastRandomInt = lastRandom.nextInt(10000);
	}

	protected int getCount() {
		synchronized (LongIdGenerator.class) {
			if (counter < 0)
				counter = 0;
			return counter++;
		}
	}

	private static int toInt(byte[] bytes) {
		int result = 0;
		for (int i = 0; i < 4; i++) {
			result = (result << 8) - Byte.MIN_VALUE + bytes[i];
		}
		return result;
	}

	@Override
	public Long generate() {
		long millisecond = System.currentTimeMillis();
		int count = lastRandomInt + getCount();
		long id = ipRandomInt * 10000000000000000L + millisecond * 100000 + jvmRandomInt * 10000 + count;
		return id;
	}
}
