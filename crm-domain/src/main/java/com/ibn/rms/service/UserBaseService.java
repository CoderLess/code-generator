package com.ibn.rms.service;

import com.ibn.page.PageInfo;
import com.ibn.page.Pagination;
import com.ibn.rms.domain.UserBaseDTO;

import java.util.List;
import java.util.Set;
/**
 * @version 1.0
 * @description: 用户基本信息表 service
 * @projectName：ibn-rms
 * @see: com.ibn.rms.service
 * @author： RenBin
 * @createTime：2020/8/11 21:45
 */
public interface UserBaseService{
    /**
     * @description: 添加信息
     * @author：RenBin
     * @createTime：2020/8/11 21:45
     */
    long save(UserBaseDTO userBaseDTO);

    /**
     * @description: 批量添加
     * @author：RenBin
     * @createTime：2020/8/11 21:45
     */
    long saveBatch(List<UserBaseDTO> userBaseDTOList);

    /**
     * @description: 根据ID删除
     * @author：RenBin
     * @createTime：2020/8/11 21:45
     */
    int remove(Long id);

    /**
     * @description: 根据ID批量删除
     * @author：RenBin
     * @createTime：2020/8/11 21:45
     */
    int removeBatch(Set<Long> idSet);

    /**
     * @description: 根据ID更新
     * @author：RenBin
     * @createTime：2020/8/11 21:45
     */
    int modify(UserBaseDTO userBaseDTO);

    /**
     * @description: 根据条件查询
     * @author：RenBin
     * @createTime：2020/8/11 21:45
     */
    UserBaseDTO query(Long id);

    /**
     * @description: 查询多个
     * @author：RenBin
     * @createTime：2020/8/11 21:45
     */
    List<UserBaseDTO> queryList(UserBaseDTO userBaseDTO);

    /**
     * @description: 分页查询
     * @author：RenBin
     * @createTime：2020/8/11 21:45
     */
    Pagination<UserBaseDTO> queryPage(UserBaseDTO userBaseDTO, PageInfo pageInfo);
}
