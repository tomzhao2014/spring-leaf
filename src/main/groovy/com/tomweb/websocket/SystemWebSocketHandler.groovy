package com.tomweb.websocket

import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.WebSocketMessage
import org.springframework.web.socket.WebSocketSession

/**
 *
 *
 * @author Jeff Huang
 *
 * @date 2015/12/28 0028 17:53
 *
 */
/**
 * Created with IntelliJ IDEA. 
 * Anthor: Administrator 
 * Date: 2015/12/28 0028 
 * Time: 17:53 
 */
class SystemWebSocketHandler implements WebSocketHandler {

    @Override
    void afterConnectionEstablished(WebSocketSession session) throws Exception {

    }

    @Override
    void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

    }

    @Override
    void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    @Override
    void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {

    }

    @Override
    boolean supportsPartialMessages() {
        return false
    }
}
