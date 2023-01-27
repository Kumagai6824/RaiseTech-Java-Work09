package com.raisetech.work09;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface NameMapper {
    @Select("SELECT * FROM names")
    List<Name> findAll();

    @Select("SELECT * FROM names where id = #{id}")
    Optional<Name> findById(int id);

    @Insert("INSERT INTO names (id, name) values (#{id}, #{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void createName(CreateForm form);

}
