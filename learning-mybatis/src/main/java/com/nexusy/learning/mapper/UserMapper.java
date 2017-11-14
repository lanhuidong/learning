package com.nexusy.learning.mapper;

import com.nexusy.learning.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author lanhuidong
 * @since 2017-08-21
 */
public interface UserMapper {

    /**
     * 批量插入
     *
     * @param users
     */
    @Insert({
            "<script>",
            "insert into users (id, username)",
            "values ",
            "<foreach  collection='users' item='user' separator=','>",
            "( #{user.id}, #{user.username})",
            "</foreach>",
            "</script>"
    })
    void save(@Param("users") List<User> users);

    /**
     * 以Map的形式返回查询结果，key由@MapKey注解决定，value为每一行的值
     */
    @Select("select id, username from users")
    @MapKey("id")
    Map<Long, User> getMapResult();

    @Select("select * from users where username like \"%\"#{key}\"%\"")
    List<User> queryUseLike(String key);
}
