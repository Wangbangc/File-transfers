package com.example.demo.entity;

import java.util.Date;

public class Filereceptions extends FilereceptionsKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column filereceptions.ReceptionTime
     *
     * @mbggenerated
     */
    private Date receptiontime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column filereceptions.ReceptionTime
     *
     * @return the value of filereceptions.ReceptionTime
     *
     * @mbggenerated
     */
    public Date getReceptiontime() {
        return receptiontime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column filereceptions.ReceptionTime
     *
     * @param receptiontime the value for filereceptions.ReceptionTime
     *
     * @mbggenerated
     */
    public void setReceptiontime(Date receptiontime) {
        this.receptiontime = receptiontime;
    }
}