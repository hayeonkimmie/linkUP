package util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SingleTonSession {
    private static SqlSessionFactory sqlSessionFactory;

    private SingleTonSession() {}

    public static SqlSessionFactory getInstance() {
        if (sqlSessionFactory == null) {
            synchronized (MybatisSqlSessionFactory.class) {
                if (sqlSessionFactory == null) {
                    try {
                        String resource = "config.xml";
                        InputStream inputStream = Resources.getResourceAsStream(resource);
                        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
                    } catch (IOException e) {
                        throw new RuntimeException("SqlSessionFactory 국성중 오류", e);
                    }
                }
            }
        }
        return sqlSessionFactory;
    }
}

