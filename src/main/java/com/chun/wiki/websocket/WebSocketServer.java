package com.chun.wiki.websocket;

import com.chun.wiki.resp.UserLoginResp;
import com.chun.wiki.util.LoginUserContext;
import com.chun.wiki.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;

@Component
//websocket请求的链接
@ServerEndpoint("/ws/{token}")
public class WebSocketServer {
    private static final Logger LOG = LoggerFactory.getLogger(WebSocketServer.class);

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 每个客户端一个token
     */
    private String token = "";

    private static HashMap<String, Session> map = new HashMap<>();

    /**
     * 连接成功
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) {

        map.put(token, session);
        this.token = token;
        LOG.info("有新连接：token：{}，session id：{}，当前连接数：{}", token, session.getId(), map.size());
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose(Session session) {
        map.remove(this.token);
        LOG.info("连接关闭，token：{}，session id：{}！当前连接数：{}", this.token, session.getId(), map.size());
    }

    /**
     * 收到消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        LOG.info("收到消息：{}，内容：{}", token, message);
    }

    /**
     * 连接错误
     */
    @OnError
    public void onError(Session session, Throwable error) {
        LOG.error("发生错误", error);
    }

    /**
     * 群发消息
     */
    public void sendInfo(String message, String currentToken) {
        for (String token : map.keySet()) {
            ////使用多线程时，其它线程获取不到ThreadLocal，所有 LoginUserContext.getUser(); 返回空
            //final UserLoginResp user = LoginUserContext.getUser();
            ////多线程获取不到，user为空 null
            //String localToken = user.getToken();

            if(currentToken != null && currentToken.equals(token)){
                Session session = map.get(token);
                try {
                    session.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    LOG.error("推送消息失败：{}，内容：{}", token, message);
                }
                LOG.info("推送消息：{}，内容：{}", token, message);
            }
        }
    }

}

