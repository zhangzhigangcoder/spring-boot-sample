package org.spring.boot.controller;

import org.spring.boot.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * 实现服务端向客户端推送消息
 * @author zhangzhigang
 *
 */
@RestController
public class AysncController {
	
    @Autowired
    PushService pushService; //定时任务，定时更新DeferredResult
 
    @GetMapping("/defer")
    //返回给客户端DeferredResult
    public DeferredResult<String> deferredCall() { 
        //异步任务的实现是通过控制器从另外一个线程返回一个DeferredResult，这里的DeferredResult
        //是从pushService中获得的。
        return pushService.getAsyncUpdate();
    }
}

