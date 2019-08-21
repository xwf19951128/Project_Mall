package com.cskaoyan.util.wx;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 维护用户token
 */
public class UserTokenManager {
    /**
     * 两个静态map存储用户登录的相关信息，一个用key是token，一个key是userId，value都是对应的UserToken
     * */
    private static Map<String, UserToken> tokenMap = new HashMap<>();
    private static Map<Integer, UserToken> idMap = new HashMap<>();

    /**
     *根据请求头中的token，获取对应的UserToken中的userId
     * */
    public static Integer getUserId(String token) {
        //在map中根据key获取value
        UserToken userToken = tokenMap.get(token);
        //如果没有登陆，就返回null
        if (userToken == null) {
            return null;
        }//如果token过期、超时，也返回null。有效期为一天
        if (userToken.getExpireTime().isBefore(LocalDateTime.now())) {
            tokenMap.remove(token);
            idMap.remove(userToken.getUserId());
            return null;
        }
        //一切正常的话就获取并返回userId
        return userToken.getUserId();
    }

    /**
     * 根据登陆用户的id，生成userToken
     * */
    public static UserToken generateToken(Integer id) {
        UserToken userToken = null;

//        userToken = idMap.get(id);
//        if(userToken != null) {
//            tokenMap.remove(userToken.getToken());
//            idMap.remove(id);
//        }

        String token = CharUtil.getRandomString(32);
        while (tokenMap.containsKey(token)) {
            token = CharUtil.getRandomString(32);
        }

        LocalDateTime update = LocalDateTime.now();
        LocalDateTime expire = update.plusDays(1);
        //新建一个userToken，存储当前登录用户的信息
        userToken = new UserToken();
        userToken.setToken(token);
        userToken.setUpdateTime(update);
        userToken.setExpireTime(expire);
        userToken.setUserId(id);
        //分别用token和id对应这个userToken
        tokenMap.put(token, userToken);

        idMap.put(id, userToken);

        return userToken;
    }

    public static String getSessionKey(Integer userId) {
        UserToken userToken = idMap.get(userId);
        if (userToken == null) {
            return null;
        }

        if (userToken.getExpireTime().isBefore(LocalDateTime.now())) {
            tokenMap.remove(userToken.getToken());
            idMap.remove(userId);
            return null;
        }

        return userToken.getSessionKey();
    }

    public static void removeToken(Integer userId) {
        UserToken userToken = idMap.get(userId);
        String token = userToken.getToken();
        idMap.remove(userId);
        tokenMap.remove(token);
    }
}
