/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.zpo5b.beans;

import java.time.LocalDate;
import static java.time.LocalDate.now;


public class FirstBean extends Bean{
    @Named(value="Name 1")
    private String fBean;
    @Named(value="Value 1")
    private Enum fEnum;
    @Named(value="Data 1")
    private LocalDate fDate;

    public FirstBean() {
        this.fBean = "unknown";
        this.fEnum = FirstBeanEnum.FIRST;
        this.fDate = now();
    }

    public FirstBean(String fBean, Enum fEnum, LocalDate fDate) {
        this.fBean = fBean;
        this.fEnum = fEnum;
        this.fDate = fDate;
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

    public String getfBean() {
        return fBean;
    }

    public Enum getfEnum() {
        return fEnum;
    }

    public LocalDate getfDate() {
        return fDate;
    }
    
}
