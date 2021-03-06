package com.nexusy.learning;

import com.nexusy.learning.mapper.UserMapper;
import com.nexusy.learning.model.User;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lanhuidong
 * @since 2017-08-21
 */
public class AppMain {

    public static void main(String[] args) {
        LogFactory.useLog4J2Logging();
        HikariConfig config = new HikariConfig();
        config.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        config.setUsername("root");
        config.setPassword("472897576");
        config.addDataSourceProperty("url", "jdbc:mysql://localhost/test");
        config.setMaximumPoolSize(5);
        DataSource dataSource = new HikariDataSource(config);

        TransactionFactory transactionFactory =
                new JdbcTransactionFactory();
        Environment environment =
                new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(UserMapper.class);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(configuration);

        SqlSession session = sqlSessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);

        List<User> users = new ArrayList<>();
        User u1 = new User(1L, "xx");
        users.add(u1);
        User u2 = new User(2L, "oo");
        users.add(u2);
        mapper.save(users);

        Map<Long, User> result = mapper.getMapResult();
        System.out.println(result);

        List<User> dbuser = mapper.queryUseLike("x");
        System.out.println(dbuser.size());

        dbuser = mapper.queryUserByIds(new Long[]{1L, 2L});
        System.out.println(dbuser.size());
        session.commit();
    }
}
