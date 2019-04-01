package com.wwg.servicedemo01.common.enums;

import lombok.Data;

public enum DataSourceEnum {
    write("write","主库"),
    read("read","从库");

    private String type;
    private String name;

    DataSourceEnum(String type,String name){
        this.type = type;
        this.name = name;
    }


    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;

    }
}
