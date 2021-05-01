package business;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

@Stateless
public class MyTimerService {
	@Resource
	TimerService timerService;
	
	private static final Logger logger = Logger.getLogger("business.MyTimerService");
	
	public MyTimerService() {
		
	}
	
	public void setTimer(long interval) {
		timerService.createTimer(interval, "My Timer");
	}
	
	@Timeout
	public void programmicTimer(Timer timer) {
		logger.info("@Timeout in programmitic timer at " + new java.util.Date());
	}
	
//	@Schedule(second="*/10", minute="*", hour="*", persistent=true)
//	private void scheduleTimeout(final Timer t) {
//		logger.info("@Scheduled Timer triggered at " + new java.util.Date());
//	}
	
}
