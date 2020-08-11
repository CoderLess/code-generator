package com.ibn.rms.controller;

import com.ibn.config.common.ResultInfo;
import com.ibn.page.PageInfo;
import com.ibn.page.Pagination;
import com.ibn.rms.ao.UserBaseAO;
import com.ibn.rms.exception.IbnException;
import com.ibn.rms.vo.UserBaseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * @version 1.0
 * @description: 用户基本信息表 控制层
 * @projectName：ibn-rms
 * @see: com.ibn.rms.controller
 * @author： RenBin
 * @createTime：2020/8/11 21:45
 */
@RestController
@RequestMapping("userBase")
@Api(tags = "用户基本信息表操作接口")
public class UserBaseController {
    @Autowired
    private UserBaseAO userBaseAO;

    @ApiOperation(value = "保存用户基本信息表", notes = "保存用户基本信息表")
    @PostMapping("save")
    public ResultInfo<Long> save(UserBaseVO userBaseVO) throws IbnException {
        long id = userBaseAO.save(userBaseVO);
        return new ResultInfo<Long>().success(id);
    }

    @ApiOperation(value = "批量保存用户基本信息表", notes = "批量保存用户基本信息表")
    @PostMapping("saveBatch")
    public ResultInfo<String> saveBatch(List<UserBaseVO> userBaseVOList) throws IbnException {
        userBaseAO.saveBatch(userBaseVOList);
        return new ResultInfo<String>().success("ok");
    }

    @ApiOperation(value = "删除用户基本信息表", notes = "删除用户基本信息表")
    @PostMapping("remove")
    public ResultInfo<String> remove(Long id) throws IbnException {
        userBaseAO.remove(id);
        return new ResultInfo<String>().success("ok");
    }

    @ApiOperation(value = "批量删除用户基本信息表", notes = "批量删除用户基本信息表")
    @PostMapping("removeBatch")
    public ResultInfo<String> removeBatch(Set<Long> idSet) throws IbnException {
        userBaseAO.removeBatch(idSet);
        return new ResultInfo<String>().success("ok");
    }

    @ApiOperation(value = "修改用户基本信息表", notes = "修改用户基本信息表")
    @PostMapping("modify")
    public ResultInfo<String> modify(UserBaseVO userBaseVO) throws IbnException {
        userBaseAO.modify(userBaseVO);
        return new ResultInfo<String>().success("ok");
    }

    @ApiOperation(value = "查询用户基本信息表", notes = "查询用户基本信息表")
    @GetMapping("query")
    public ResultInfo<UserBaseVO> query(Long id) throws IbnException {
        UserBaseVO userBaseVO = userBaseAO.query(id);
        return new ResultInfo<UserBaseVO>().success(userBaseVO);
    }

    @ApiOperation(value = "查询多个用户基本信息表", notes = "查询多个用户基本信息表")
    @GetMapping("queryList")
    public ResultInfo<List<UserBaseVO>> queryList(UserBaseVO userBaseVO) throws IbnException {
        List<UserBaseVO> userBaseVOList = userBaseAO.queryList(userBaseVO);
        return new ResultInfo<List<UserBaseVO>>().success(userBaseVOList);
    }

    @ApiOperation(value = "分页查询用户基本信息表", notes = "分页查询用户基本信息表")
    @GetMapping("queryPage")
    public ResultInfo<Pagination<UserBaseVO>> queryPage(UserBaseVO userBaseVO, PageInfo pageInfo) throws IbnException {
        Pagination<UserBaseVO> userBaseVOPage = userBaseAO.queryPage(userBaseVO, pageInfo);
        return new ResultInfo<Pagination<UserBaseVO>>().success(userBaseVOPage);
    }
}
