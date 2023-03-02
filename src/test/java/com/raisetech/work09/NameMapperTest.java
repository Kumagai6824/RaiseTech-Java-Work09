package com.raisetech.work09;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//Replace.NONE：テスト用データベースの設定を手動で行う
class NameMapperTest {

    @Autowired
    NameMapper nameMapper;

    @Test
    @Sql(
            scripts = {"classpath:/delete-names.sql", "classpath:/insert-names.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Transactional
    void すべてのユーザーが取得できること() {
        List<Name> names = nameMapper.findAll();
        assertThat(names)
                .hasSize(3)
                .contains(
                        new Name(1, "Shimizu"),
                        new Name(2, "Koyama"),
                        new Name(3, "Tanaka")
                );
    }

}
