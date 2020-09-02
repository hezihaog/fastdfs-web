package com.bjpowernode.fastdfs.service.impl;

import com.bjpowernode.fastdfs.mapper.CreditorInfoMapper;
import com.bjpowernode.fastdfs.model.CreditorInfo;
import com.bjpowernode.fastdfs.service.CreditorService;
import com.bjpowernode.fastdfs.util.FastDFSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hezihao
 * @version 1.0
 * <p>
 * @date 2020/9/2 4:28 下午
 * <p>
 */
@Service
public class CreditorServiceImpl implements CreditorService {
    @Autowired
    private CreditorInfoMapper creditorInfoMapper;

    @Override
    public List<CreditorInfo> selectAl() {
        return creditorInfoMapper.selectAll();
    }

    @Override
    public CreditorInfo selectById(Integer id) {
        return creditorInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateFileInfo(CreditorInfo creditorInfo) {
        int count = creditorInfoMapper.updateByPrimaryKeySelective(creditorInfo);
        return count > 0;
    }

    @Override
    public boolean deleteById(Integer id) {
        //先查询出数据
        CreditorInfo creditorInfo = creditorInfoMapper.selectByPrimaryKey(id);
        //删除文件
        boolean isSuccess = FastDFSUtil.delete(creditorInfo.getGroupName(), creditorInfo.getRemoteFilePath());
        if (isSuccess) {
            creditorInfo.setGroupName("");
            creditorInfo.setRemoteFilePath("");
            creditorInfo.setOldFileName("");
            creditorInfo.setFileType("");
            creditorInfo.setFileType("");
            creditorInfo.setFileSize(0L);
            //清空掉文件信息
            creditorInfoMapper.updateByPrimaryKeySelective(creditorInfo);
        }
        return isSuccess;
    }
}