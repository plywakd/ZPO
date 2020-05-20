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
public class Bean {
    @Named(value="Name x")
    private String fBean;
    @Named(value="Value x")
    private Enum fEnum;
    @Named(value="Data x")
    private LocalDate fDate;

    public Bean() {
        this.fBean="x";
        this.fEnum=FirstBeanEnum.ONE;
        this.fDate=now();
    }

    public Bean(String fBean, Enum fEnum, LocalDate fDate) {
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
