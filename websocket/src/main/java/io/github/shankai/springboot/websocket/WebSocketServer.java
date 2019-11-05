package io.github.shankai.springboot.websocket;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

/**
 * WebSocketServer
 */
@ServerEndpoint("/ws/sample")
@Component
@Log4j2
public class WebSocketServer {

    // 连接数
    private static final AtomicInteger onlineCount = new AtomicInteger(0);
    // concurrent包的线程安全Set，用来存放每个客户端对应的Session对象。
    private static CopyOnWriteArraySet<Session> sessionSet = new CopyOnWriteArraySet<Session>();

    @OnOpen
    public void onOpen(Session session) {
        sessionSet.add(session);
        int count = onlineCount.incrementAndGet();
        log.info("New Session Join, Count: {} ", count);
    }

    @OnClose
    public void onClose(Session session) {
        sessionSet.remove(session);
        int count = onlineCount.decrementAndGet();
        log.info("Session Quit, Count: {} ", count);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("Error: {}, session: {} ", error.getMessage(), session.getId());
        error.printStackTrace();
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("Client Message: {}",message);
        sendMessage(session, message);
    }

    private void sendMessage(Session session, String message) {
        try {
            session.getBasicRemote()
                    .sendText(String.format("%s (From Server，Session ID=%s)", message, session.getId()));
        } catch (IOException e) {
            log.error("Send Message Error: {}", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 发送消息到指定客户端
     * 
     * @param message
     * @param sessionId
     */
    public void sendMessage(String message, String sessionId) {
        Optional<Session> opt = sessionSet.stream().filter(s -> s.getId().equals(sessionId)).findFirst();
        if (opt.isPresent()) {
            sendMessage(opt.get(), message);
        } else {
            log.warn("Not Found Session: {}", sessionId);
        }
    }

    /**
     * 广播
     * 
     * @param message
     */
    public void broadCast(String message) {
        for (Session session : sessionSet) {
            if (session.isOpen()) {
                sendMessage(session, message);
            } else {
                sessionSet.remove(session);
                onlineCount.decrementAndGet();
            }
        }
    }

}