package com.raisetech.work09;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface NameMapper {
    @Select("SELECT * FROM names")
    List<Name> findAll();

    @Select("SELECT * FROM names where id = #{id}")
    Optional<Name> findById(int id);

    @Insert("INSERT INTO names (name) values (#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void createName(Name name);

    @Delete("DELETE FROM names where id = #{id}")
    void deleteById(int id);

}
