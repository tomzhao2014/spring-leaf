package com.tomweb.websocket

import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

/**
 *
 *
 * @author tom zhao
 *
 * @date 2015/12/24 0024 17:53
 *
 */
/**
 * Created with IntelliJ IDEA. 
 * Anthor: Administrator 
 * Date: 2015/12/24 0024 
 * Time: 17:53 
 */
class TomHandler extends TextWebSocketHandler {
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        // ...
    }
}
