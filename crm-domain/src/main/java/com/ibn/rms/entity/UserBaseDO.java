package com.ibn.rms.entity;

import com.ibn.entity.BaseDO;
import lombok.Data;

/**
 * @version 1.0
 * @description: 用户基本信息表 do
 * @projectName：ibn-rms
 * @see: com.ibn.rms.entity
 * @author： RenBin
 * @createTime：2020/8/11 21:45
 */
@Data
public class UserBaseDO extends BaseDO {

	private static final long serialVersionUID = 1L;

    /**
     * @description: 主键
     * @author：RenBin
     * @createTime：2020/8/11 21:45
     */
    private Long id;
    /**
     * @description: 用户ID
     * @author：RenBin
     * @createTime：2020/8/11 21:45
     */
    private Long userId;
    /**
     * @description: 用户登录名
     * @author：RenBin
     * @createTime：2020/8/11 21:45
     */
    private String username;
    /**
     * @description: 登录密码
     * @author：RenBin
     * @createTime：2020/8/11 21:45
     */
    private String password;
    /**
     * @description: 注册方式
     * @author：RenBin
     * @createTime：2020/8/11 21:45
     */
    private Integer regType;
    /**
     * @description: 注册时间
     * @author：RenBin
     * @createTime：2020/8/11 21:45
     */
    private Long regTime;
    /**
     * @description: 最后登入时间
     * @author：RenBin
     * @createTime：2020/8/11 21:45
     */
    private Long lastLoginTime;
}