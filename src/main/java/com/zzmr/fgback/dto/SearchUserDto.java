package com.zzmr.fgback.dto;

import lombok.Data;

/**
 * @author zzmr
 * @create 2024-04-08 9:56
 * 查询条件
 */
@Data
public class SearchUserDto {

    /**
     * 后续把用户昵称改为唯一，就可以当作唯一标识了
     */
    private String nickName;

    private Integer page;

    private Integer pageSize;

}
