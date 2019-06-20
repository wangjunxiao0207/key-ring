package com.key.dao;

import com.key.pojo.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser checkUserName(@Param("name")String name, @Param("id")Integer id);

    SysUser checkTelephone(@Param("phone")String phone, @Param("id")Integer id);

    SysUser checkEmailExist(@Param("email")String email, @Param("id")Integer id);

    int batchDelete(@Param("idArr")List<Integer> idArr);

    List<SysUser> list(@Param("user")SysUser user);

    SysUser getUserByName(@Param("name")String name);

    SysUser getUserByPhone(@Param("phone")String phone);

    SysUser getUserByMail(@Param("mail")String mail);
}