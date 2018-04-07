package com.nexusy.java.v8;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author lanhuidong
 * @since 2018-04-02
 */
public class OptionalDemo {

    private static Map<String, String> map = new HashMap<>();

    static {
        map.put("1", "aaa");
        map.put("2", "bbb");
        map.put("3", "ccc");
        map.put("4", "ddd");
        map.put("5", "eee");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 6; i++) {
            getUser(String.valueOf(i)).ifPresent(System.out::println);
        }
    }

    //Optional可以用在方法的返回值中，用来明确的表示是否有结果，而不是像以前一样用null来表示没有结果
    public static Optional<String> getUser(String userId) {
        return Optional.ofNullable(map.get(userId));
    }

    public static Optional<String> getUser() {
        return Optional.ofNullable(map.get(""));
    }

    //不推荐在方法参数中使用Optional, 可以用上面两个方法的组合来代替本方法
    public static Optional<String> getUser(Optional<String> userId) {
        return Optional.ofNullable(map.get(userId.orElse("")));
    }

}
