package org.zyyd.base.listener;


import org.zyyd.base.util.DateFormater;
import org.zyyd.base.util.SpringContextUtil;

import redis.RedisUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;



/**
 * Listener that keeps track of the online users of sessions that the Web
 * application is currently using and has ever used in its life cycle.
 */

public class OnlineListener implements HttpSessionListener,
		HttpSessionAttributeListener {
	private static int currentSessionCount = 0;

	private static int maxSessionCount = 0;
	
	private static String preKeyRedisList="sso.userList";

	// private static List<OnlineModel> logined = new ArrayList<OnlineModel>();
	// //members
	// private static Vector<OnlineModel> logined = new Vector<OnlineModel>();
	private static List<OnlineModel> logined = Collections
			.synchronizedList(new ArrayList<OnlineModel>());

	private final static Object lock = new Object();

	public OnlineListener() {
		System.out.println("OnlineListerner Constructor...");
	}

	/** The number of sessions currently in memory. */
	public static int getCurrentSessionCount() {
		return currentSessionCount;
	}

	/**
	 * The largest number of sessions ever in memory at any one time.
	 */
	public static int getMaxSessionCount() {
		return maxSessionCount;
	}

	public static List<OnlineModel> getLogined() {
		List<OnlineModel> list=new ArrayList<OnlineModel>();
		RedisUtil redisUtil =(RedisUtil) SpringContextUtil.getBean("redisUtil");
		if(redisUtil.hasKey(preKeyRedisList)){
			list=(List<OnlineModel>)redisUtil.get(preKeyRedisList);
		}
		return list;
	}
	
	public static List<OnlineModel> getLoginedPage(Integer pageNo, Integer pageSize){
		List<OnlineModel> page = new ArrayList<OnlineModel>();
		RedisUtil redisUtil =(RedisUtil) SpringContextUtil.getBean("redisUtil");
		List<OnlineModel> all = (List<OnlineModel>)redisUtil.get(preKeyRedisList);
		pageNo = pageNo==null||pageNo > 0 ? pageNo : 1;
		for(int i=0;i<pageSize;i++){
			int index = (pageNo-1)*pageSize + i;
			if(index < all.size()){
				page.add(all.get(index));
			}else{
				break;
			}
		}
		return page;
	}

	public static int getLoginedCount() {
		List<OnlineModel> list=new ArrayList<OnlineModel>();
		RedisUtil redisUtil =(RedisUtil) SpringContextUtil.getBean("redisUtil");
		if(redisUtil.hasKey(preKeyRedisList)){
			list=(List<OnlineModel>)redisUtil.get(preKeyRedisList);
		}
		return list.size();
	}

	public void sessionCreated(HttpSessionEvent event) {
		currentSessionCount++;
		if (currentSessionCount > maxSessionCount) {
			maxSessionCount = currentSessionCount;
		}
		// System.out.println("----sessionCreated----");
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		currentSessionCount--;
		// System.out.println("----sessionDestroyed----");
	}

	public void attributeAdded(HttpSessionBindingEvent se) {
		if ("logined".equals(se.getName())) {
			// synchronized(list)
			add((OnlineModel) (se.getValue()));
		}
	}

	public void attributeRemoved(HttpSessionBindingEvent se) {
		if ("logined".equals(se.getName())) {
			// synchronized(list)
			remove((OnlineModel) (se.getValue()));
		}
	}

	public void attributeReplaced(HttpSessionBindingEvent event) {
	}

	// synchronized(list)
	public static void remove(OnlineModel om) {
		if (om == null) {
			return;
		}
//		synchronized (lock) {
		RedisUtil redisUtil =(RedisUtil) SpringContextUtil.getBean("redisUtil");
//			logined.remove(om);
			List<OnlineModel> list=new ArrayList<OnlineModel>();
			if(redisUtil.hasKey(preKeyRedisList)){
				list=(List<OnlineModel>)redisUtil.get(preKeyRedisList);
			}
			list.remove(om);
			redisUtil.set(preKeyRedisList, list);
//		}
		System.out.println(om + " logout!");
	}

	// synchronized(list)
	public static void add(OnlineModel om) {
		if (om == null) {
			return;
		}
//		synchronized (lock) {
		RedisUtil redisUtil =(RedisUtil) SpringContextUtil.getBean("redisUtil");
//			logined.add(om);
			List<OnlineModel> list=new ArrayList<OnlineModel>();
			if(redisUtil.hasKey(preKeyRedisList)){
				list=(List<OnlineModel>)redisUtil.get(preKeyRedisList);
			}
			list.add(om);
			redisUtil.set(preKeyRedisList, list);
//		}
		System.out.println(om + " login!");
	}

	// synchronized(list)
	public static void updateLastAcessTime(String sessionId) {
		if (sessionId == null) {
			return;
		}
//		synchronized (lock) {
			List<OnlineModel> list=new ArrayList<OnlineModel>();
			RedisUtil redisUtil =(RedisUtil) SpringContextUtil.getBean("redisUtil");
			if(redisUtil.hasKey(preKeyRedisList)){
				list=(List<OnlineModel>)redisUtil.get(preKeyRedisList);
			}
			for (OnlineModel om : list) {
				if (sessionId.equals(om.getId())) {
					om.setLastAccessTime(DateFormater
							.DateTimeToString(new Date()));
					break;
				}
			}
			redisUtil.set(preKeyRedisList, list);
//		}
	}

	// synchronized(list)
	public static void killExpired(int timeout,HttpSession session) {
		Date activeTime;
//		synchronized (lock) {
			List<OnlineModel> rlist=new ArrayList<OnlineModel>();
			RedisUtil redisUtil =(RedisUtil) SpringContextUtil.getBean("redisUtil");
			if(redisUtil.hasKey(preKeyRedisList)){
				rlist=(List<OnlineModel>)redisUtil.get(preKeyRedisList);
			}
			for (OnlineModel om : rlist) {
				activeTime = DateFormater.StringToDateTime(om
						.getLastAccessTime());
				if (DateFormater.TimeDiff(new Date(), activeTime) > timeout) {
					rlist.remove(om);
					redisUtil.set(preKeyRedisList, rlist);
					if(session.getId().equals(om.getId())){
						session.invalidate();
					}
					System.out.println(om + " Expired!");
				}
			}
//		}
	}

	public static void main(String[] args) {
	}
}
