package com.naresh.PlayerScore_Service.config;

import com.naresh.PlayerScore_Service.kafka.LeaderBoard;
import com.naresh.PlayerScore_Service.kafka.ScorePayload;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private final List<WebSocketSession> sessions = new ArrayList<>();
    private final Map<UUID ,Integer> map= new LinkedHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Ignore incoming messages for now
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
        sessions.remove(session);
    }
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        return map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue()) // Sort by value
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, // Merge function in case of duplicate keys
                        LinkedHashMap::new // Preserve order
                ));
    }

    public void sendScoreToWebSocket(LeaderBoard leaderBoard) throws Exception {

            map.put(leaderBoard.playerId(),leaderBoard.score());
             sortByValue(map);


        for (WebSocketSession session : sessions) {
            session.sendMessage(new TextMessage(map.toString()));
        }
    }
}
