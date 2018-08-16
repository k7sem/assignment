package com.k7.moment.mapper;

import com.k7.moment.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 *
 * @Author Kevin
 *  @Since 08/16/2018
 *
 */

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user")
    List<User> getAllUsers();

    @Insert("INSERT INTO user(username) VALUES(#{name})")
    int insertUser(@Param("name") String name);

    //@Insert("INSERT INTO T_USER(NAME, PASSWORD, PHONE) VALUES(#{name}, #{password}, #{phone})")
    //int insert(@Param("name") String name, @Param("password") String password, @Param("phone") String phone);

    @Delete("DELETE FROM user")
    void deleteAllUsers();

    @Update("UPDATE user SET username=#{name} WHERE id=#{id}")
    void updateUser(@Param("name") String name, @Param("id") int id);

    @Delete("DELETE FROM user WHERE id=#{id}")
    void deleteUserById(@Param("id") int id);
}
