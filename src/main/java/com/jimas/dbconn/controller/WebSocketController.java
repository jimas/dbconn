/**
 * Project Name:dbconn
 * File Name:WebSocketController.java
 * Package Name:com.jimas.dbconn.controller
 * Date: 2017年7月11日
 * Time: 下午2:02:30
 *
*/

package com.jimas.dbconn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName:WebSocketController <br/>
 * TODO ADD DESCRIPTION
 * Date: 2017年7月11日
 * Time: 下午2:02:30
 * @author   liuqj 	 
 */
@Controller
public class WebSocketController {
	@RequestMapping("/webSockect.html")
    public  String webSocket() {
        return "webSocket";
    }
}

