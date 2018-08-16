package com.k7.moment.mapper;

import com.k7.moment.pojo.Url;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 *
 * @Author Kevin
 * @Since 08/16/2018
 *
 */


@Mapper
public interface UrlMapper {

    @Delete("DELETE FROM url WHERE userid=#{uid}")
    void deleteAllUrlsByUserID(@Param("uid") int uid);

    @Insert("INSERT INTO url(imgurl, userid) VALUES(#{iurl},#{uid})")
    void insertImgUrlByUserID(@Param("iurl") String iurl, @Param("uid") int uid);

    @Select("SELECT * FROM url WHERE userid=#{uid}")
    List<Url> getAllUrlByUserID(@Param("uid") int uid);

    @Delete("DELETE FROM url")
    void deleteAll();

}
