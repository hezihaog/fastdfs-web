package com.bjpowernode.fastdfs.mapper;

import com.bjpowernode.fastdfs.model.CreditorInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CreditorInfoMapper {
    /**
     * 查询所有
     */
    List<CreditorInfo> selectAll();

    int deleteByPrimaryKey(Integer id);

    int insert(CreditorInfo record);

    int insertSelective(CreditorInfo record);

    CreditorInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CreditorInfo record);

    int updateByPrimaryKey(CreditorInfo record);
}