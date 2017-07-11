/**
 * Project Name:analy-open
 * File Name:SystemWebSocketHandler.java
 * Package Name:com.mob.analy.open.websocket
 * Date: 2017年7月11日
 * Time: 下午12:16:35
 *
*/

package com.jimas.dbconn.websocket;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * ClassName:SystemWebSocketHandler <br/>
 * Date: 2017年7月11日
 * Time: 下午12:16:35
 * @author   liuqj 	 
 */
public class SystemWebSocketHandler implements WebSocketHandler {
	private static final ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		System. out.println("ConnectionEstablished");
        users.add(session);
		
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message)
			throws Exception {
		System. out.println( "session id:" + session.getId());
	     sendMessageToUsers(session.getId(),(TextMessage) message);

	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception)
			throws Exception {
		if(session.isOpen()){
            session.close();
        }
        users.remove(session);

	}
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus)
			throws Exception {
		
		users.remove(session);
	}

	@Override
	public boolean supportsPartialMessages() {

		return false;
	}
	/**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(String excep, TextMessage message) {
        for (WebSocketSession user : users) {
            try {
                if (user.isOpen()&& !user.getId().equals(excep)) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

