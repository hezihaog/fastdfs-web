package com.bjpowernode.fastdfs.model;

import java.math.BigDecimal;

/**
 * 债权人借款信息
 */
public class CreditorInfo {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 债权借款人姓名
     */
    private String realName;
    /**
     * 债权借款人身份证
     */
    private String idCard;
    /**
     * 债权借款人地址
     */
    private String address;
    /**
     * 性别，1男2女
     */
    private Integer sex;
    /**
     * 债权借款人电话
     */
    private String phone;
    /**
     * 债权借款人借款金额
     */
    private BigDecimal money;
    /**
     * 债权合同所在组
     */
    private String groupName;
    /**
     * 债权合同所在路径
     */
    private String remoteFilePath;
    /**
     * 文件上传前的名字，下载时命名回文件名字
     */
    private String oldFileName;
    /**
     * 文件大小，用于记录下载进度
     */
    private Long fileSize;
    /**
     * 文件类型，用于显示文件类型相同的图标
     */
    private String fileType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getRemoteFilePath() {
        return remoteFilePath;
    }

    public void setRemoteFilePath(String remoteFilePath) {
        this.remoteFilePath = remoteFilePath;
    }

    public String getOldFileName() {
        return oldFileName;
    }

    public void setOldFileName(String oldFileName) {
        this.oldFileName = oldFileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}