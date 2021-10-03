package systemservice;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.time.DateUtils;

public class TaskManager implements ServletContextListener {

	//每天的毫秒数
	public static final long PERIOD_DAY = DateUtils.MILLIS_IN_DAY;
	//一周的毫秒数
	public static final long PERIOD_WEEK = PERIOD_DAY * 7;
	//无延迟
	public static final long NO_DELAY = 0;
	//定时器
	private Timer timer;

	/**
	 * 在Web应用结束时停止任务
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		timer.cancel();//定时器销毁

	}

	/**
	 * 在Web应用启动时初始化任务
	 */
	public void contextInitialized(ServletContextEvent sce) {
		//定义定时器
		timer = new Timer(true);
		//timer.schedule(new LocationTask(), NO_DELAY, PERIOD_WEEK * 4);
		
		timer.schedule(new AssignTask(), NO_DELAY, 50000);

	}

}
