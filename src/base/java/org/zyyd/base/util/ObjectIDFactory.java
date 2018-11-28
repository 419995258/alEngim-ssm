package org.zyyd.base.util;

public class ObjectIDFactory {
	public ObjectIDFactory() {
	}

	public static String getGuid() {
		RandomGuid guid = new RandomGuid();
		return guid.toString();
	}

	public static void main(String[] args) {
		System.out.println(getGuid());
	}
}