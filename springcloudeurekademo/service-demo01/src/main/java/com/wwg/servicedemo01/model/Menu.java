package com.wwg.servicedemo01.model;

import lombok.Data;

import java.util.List;

@Data
public class Menu {
    private Integer id;
    private Integer parentId;
    private String menuCode;
    private String menuName;
    //菜单类型，0：菜单  1`：业务按钮
    private Integer type;
    //序号
    private Integer sortNum;
    //菜单地址Url
    private String url;

    private List<Menu> childMenu;

}
