package top.mnilsy.cup.netty;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.ChannelId;

/**
 * @Description: user_Name和channelID的关联关系处理
 */
public class UserChannelRel {

    private static ConcurrentHashMap<String, ChannelId> manager = new ConcurrentHashMap<>();

    public static void put(String user_Name, ChannelId channelId) {
        manager.put(user_Name, channelId);
    }

    public static ChannelId get(String user_Name) {
        return manager.get(user_Name);
    }

    public static String get(ChannelId channelId) {
        for (Map.Entry<String, ChannelId> entry : manager.entrySet()) {
            if (channelId.equals(entry.getValue()))
                return entry.getKey();
        }
        return null;
    }

    public static void remove(String user_Name) {
        if (get(user_Name) == null) return;
        manager.remove(user_Name);
    }

    public static void remove(ChannelId channelId) {
        String user_Name = get(channelId);
        if (user_Name == null) return;
        manager.remove(user_Name);
    }

    public static void output() {
        for (ConcurrentHashMap.Entry<String, ChannelId> entry : manager.entrySet()) {
            System.out.println("user_Name: " + entry.getKey()
                    + ", ChannelId: " + entry.getValue().asLongText());
        }
    }
}
