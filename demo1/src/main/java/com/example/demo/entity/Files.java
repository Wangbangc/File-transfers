package com.example.demo.entity;

import java.util.Date;

public class Files {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column files.FileID
     *
     * @mbggenerated
     */
    private Integer fileid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column files.FileName
     *
     * @mbggenerated
     */
    private String filename;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column files.FileType
     *
     * @mbggenerated
     */
    private String filetype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column files.PublisherID
     *
     * @mbggenerated
     */
    private Integer publisherid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column files.PublishTime
     *
     * @mbggenerated
     */
    private Date publishtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column files.FileContent
     *
     * @mbggenerated
     */
    private byte[] filecontent;

    private String SerialNumber;

    public String getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        SerialNumber = serialNumber;
    }
    public Integer getFileid() {
        return fileid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column files.FileID
     *
     * @param fileid the value for files.FileID
     *
     * @mbggenerated
     */
    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column files.FileName
     *
     * @return the value of files.FileName
     *
     * @mbggenerated
     */
    public String getFilename() {
        return filename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column files.FileName
     *
     * @param filename the value for files.FileName
     *
     * @mbggenerated
     */
    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column files.FileType
     *
     * @return the value of files.FileType
     *
     * @mbggenerated
     */
    public String getFiletype() {
        return filetype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column files.FileType
     *
     * @param filetype the value for files.FileType
     *
     * @mbggenerated
     */
    public void setFiletype(String filetype) {
        this.filetype = filetype == null ? null : filetype.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column files.PublisherID
     *
     * @return the value of files.PublisherID
     *
     * @mbggenerated
     */
    public Integer getPublisherid() {
        return publisherid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column files.PublisherID
     *
     * @param publisherid the value for files.PublisherID
     *
     * @mbggenerated
     */
    public void setPublisherid(Integer publisherid) {
        this.publisherid = publisherid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column files.PublishTime
     *
     * @return the value of files.PublishTime
     *
     * @mbggenerated
     */
    public Date getPublishtime() {
        return publishtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column files.PublishTime
     *
     * @param publishtime the value for files.PublishTime
     *
     * @mbggenerated
     */
    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column files.FileContent
     *
     * @return the value of files.FileContent
     *
     * @mbggenerated
     */
    public byte[] getFilecontent() {
        return filecontent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column files.FileContent
     *
     * @param filecontent the value for files.FileContent
     *
     * @mbggenerated
     */
    public void setFilecontent(byte[] filecontent) {
        this.filecontent = filecontent;
    }
}