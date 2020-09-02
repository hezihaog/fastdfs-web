package com.bjpowernode.fastdfs.service;

import com.bjpowernode.fastdfs.model.CreditorInfo;

import java.util.List;

/**
 * @author hezihao
 * @version 1.0
 * <p>
 * @date 2020/9/2 4:27 下午
 * <p>
 */
public interface CreditorService {
    /**
     * 查询所有
     */
    List<CreditorInfo> selectAl();

    /**
     * 按Id查询
     */
    CreditorInfo selectById(Integer id);

    /**
     * 更新文件信息
     */
    boolean updateFileInfo(CreditorInfo creditorInfo);

    /**
     * 删除文件信息
     */
    boolean deleteById(Integer id);
}