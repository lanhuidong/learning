package com.nexusy.learning.mapper;

import com.nexusy.learning.model.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @author lanhuidong
 * @since 2017-08-21
 */
public interface UserMapper {

    /**
     * 以Map的形式返回查询结果，key由@MapKey注解决定，value为每一行的值
     *
     * @return
     */
    @Select("select id, username from users")
    @MapKey("id")
    Map<Long, User> getMapResult();
}
