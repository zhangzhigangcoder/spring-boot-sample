package org.spring.boot.service;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;
 
@Service
public class PushService {
    //在PushService里产生DeferredResult给控制器使用
    private DeferredResult<String> deferredResult; 
 
    public DeferredResult<String> getAsyncUpdate() {
        deferredResult = new DeferredResult<String>();
        return deferredResult;
    }
 
    //通过Scheduled注解的方法定时更新DeferredResult
    @Scheduled(fixedDelay = 5000)
    public void refresh() {
        if (deferredResult != null) {
        	String result = new Long(System.currentTimeMillis()).toString();
        	System.out.println("resp: " + result);
            deferredResult.setResult(result);
        }
    }
 
 
}
