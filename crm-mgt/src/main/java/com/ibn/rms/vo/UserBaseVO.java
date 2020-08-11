package com.ibn.rms.vo;

import com.ibn.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @version 1.0
 * @description: 用户基本信息表 vo
 * @projectName：ibn-rms
 * @see: com.ibn.rms.vo
 * @author： RenBin
 * @createTime：2020/8/11 21:45
 */
@Data
@ApiModel(description="用户基本信息表 vo")
public class UserBaseVO extends BaseVO {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="主键")
    private Long id;
    @ApiModelProperty(value="用户ID")
    private Long userId;
    @ApiModelProperty(value="用户登录名")
    private String username;
    @ApiModelProperty(value="登录密码")
    private String password;
    @ApiModelProperty(value="注册方式")
    private Integer regType;
    @ApiModelProperty(value="注册时间")
    private Long regTime;
    @ApiModelProperty(value="最后登入时间")
    private Long lastLoginTime;
}