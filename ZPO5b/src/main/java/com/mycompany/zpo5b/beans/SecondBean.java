/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.zpo5b.beans;

import java.time.LocalDate;
import static java.time.LocalDate.now;

/**
 *
 * @author Plywak
 */
public class SecondBean extends Bean{
    @Named(value="Name 2")
    private String fBean;
    @Named(value="Value 2")
    private Enum fEnum;
    @Named(value="Data 2")
    private LocalDate fDate;

    public SecondBean() {
        this.fBean = "unknown";
        this.fEnum = SecondBeanEnum.SECOND;
        this.fDate = now();
    }
    
    public SecondBean(String fBean, Enum fEnum, LocalDate fDate) {
        this.fBean = fBean;
        this.fEnum = fEnum;
        this.fDate = fDate;
    }

    public String getfBean() {
        return fBean;
    }

    public Enum getfEnum() {
        return fEnum;
    }

    public LocalDate getfDate() {
        return fDate;
    }

    public void setfBean(String fBean) {
        this.fBean = fBean;
    }

    public void setfEnum(Enum fEnum) {
        this.fEnum = fEnum;
    }

    public void setfDate(LocalDate fDate) {
        this.fDate = fDate;
    }
    
}
