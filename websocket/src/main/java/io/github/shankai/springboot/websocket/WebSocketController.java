package io.github.shankai.springboot.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * WebSocketController
 */
@RestController
@RequestMapping("/api/ws")
public class WebSocketController {

    @Autowired
    WebSocketServer websocketServer;

    @RequestMapping(value = "/sendAll", method = RequestMethod.GET)
    public String sendAllMessage(@RequestParam(required = true) String message) {
        websocketServer.broadCast(message);
        return "Done";
    }

    @RequestMapping(value = "/sendOne", method = RequestMethod.GET)
    public String sendOneMessage(@RequestParam(required = true) String message,
            @RequestParam(required = true) String id) {
        websocketServer.sendMessage(message, id);
        return "Done";
    }
}