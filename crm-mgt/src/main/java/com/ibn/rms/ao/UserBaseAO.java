package com.ibn.rms.ao;

import com.ibn.page.PageInfo;
import com.ibn.page.Pagination;
import com.ibn.rms.vo.UserBaseVO;

import java.util.List;
import java.util.Set;

/**
 * @version 1.0
 * @description: 用户基本信息表 ao层
 * @projectName：ibn-rms
 * @see: com.ibn.rms.ao
 * @author： RenBin
 * @createTime：2020/8/11 21:45
 */
public interface UserBaseAO {
    /**
     * @description: 添加信息
     * @author：RenBin
     * @createTime：2020/8/10 9:31
     */
    long save(UserBaseVO userBaseVO);

    /**
     * @description: 批量添加
     * @author：RenBin
     * @createTime：2020/8/10 9:31
     */
    long saveBatch(List<UserBaseVO> userBaseVOList);

    /**
     * @description: 根据ID删除
     * @author：RenBin
     * @createTime：2020/8/10 9:32
     */
    int remove(Long id);

    /**
     * @description: 根据ID批量删除
     * @author：RenBin
     * @createTime：2020/8/10 9:32
     */
    int removeBatch(Set<Long> idSet);

    /**
     * @description: 根据ID更新
     * @author：RenBin
     * @createTime：2020/8/10 9:33
     */
    int modify(UserBaseVO userBaseVO);

    /**
     * @description: 根据条件查询
     * @author：RenBin
     * @createTime：2020/8/10 9:35
     */
    UserBaseVO query(Long id);

    /**
     * @description: 查询多个
     * @author：RenBin
     * @createTime：2020/8/10 9:36
     */
    List<UserBaseVO> queryList(UserBaseVO userBaseVO);

    /**
     * @description: 分页查询
     * @author：RenBin
     * @createTime：2020/8/10 9:37
     */
    Pagination<UserBaseVO> queryPage(UserBaseVO userBaseVO, PageInfo pageInfo);
}
