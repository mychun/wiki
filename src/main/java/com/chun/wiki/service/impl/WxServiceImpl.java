package com.chun.wiki.service.impl;

import com.chun.wiki.service.WxService;
import com.chun.wiki.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
//开启多线程（异步）
@EnableAsync
public class WxServiceImpl implements WxService {

    @Autowired
    private WebSocketServer webSocketServer;

    @Override
    //定义为异步方法（启动多线程执行）
    //注意多线程的方法的定义，和调用的方法不能在同一个类，否则不生效
    @Async
    public void sendInfo(String message) {
        webSocketServer.sendInfo(message);
    }
}
