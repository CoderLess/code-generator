package com.ibn.rms.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.ibn.page.PageInfo;
import com.ibn.page.Pagination;
import com.ibn.rms.dao.UserBaseDao;
import com.ibn.rms.domain.UserBaseDTO;
import com.ibn.rms.entity.UserBaseDO;
import com.ibn.rms.service.UserBaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @version 1.0
 * @description: 用户基本信息表 service实现
 * @projectName：ibn-rms
 * @see: com.ibn.rms.service.impl
 * @author： RenBin
 * @createTime：2020/8/11 21:45
 */
@Service("userBaseService")
public class UserBaseServiceImpl implements UserBaseService {
    @Autowired
    private UserBaseDao userBaseDao;

    @Override
    public long save(UserBaseDTO userBaseDTO) {
        UserBaseDO userBaseDO = new UserBaseDO();
        BeanUtils.copyProperties(userBaseDTO, userBaseDO);
        userBaseDao.save(userBaseDO);
        return userBaseDO.getId();
    }

    @Override
    public long saveBatch(List<UserBaseDTO> userBaseDTOList) {
        List<UserBaseDO> userBaseDOList = userBaseDTOList.stream().map(userBaseDTO -> {
            UserBaseDO userBaseDO = new UserBaseDO();
            BeanUtils.copyProperties(userBaseDTO, userBaseDO);
            return userBaseDO;
        }).collect(Collectors.toList());
        return userBaseDao.saveBatch(userBaseDOList);
    }

    @Override
    public int remove(Long id) {
        return userBaseDao.remove(id);
    }

    @Override
    public int removeBatch(Set<Long> idSet) {
        return userBaseDao.removeBatch(idSet);
    }

    @Override
    public int modify(UserBaseDTO userBaseDTO) {
        UserBaseDO userBaseDO = new UserBaseDO();
        BeanUtils.copyProperties(userBaseDTO, userBaseDO);
        return userBaseDao.modify(userBaseDO);
    }

    @Override
    public UserBaseDTO query(Long id) {
        UserBaseDO userBaseResultDO = userBaseDao.query(id);
        if (null == userBaseResultDO) {
            return null;
        }
        UserBaseDTO userBaseDTO = new UserBaseDTO();
        BeanUtils.copyProperties(userBaseResultDO, userBaseDTO);
        return userBaseDTO;
    }

    @Override
    public List<UserBaseDTO> queryList(UserBaseDTO userBaseDTO) {
        UserBaseDO userBaseDO = new UserBaseDO();
        BeanUtils.copyProperties(userBaseDTO, userBaseDO);
        List<UserBaseDO> userBaseDOList = userBaseDao.queryList(userBaseDO);
        if (CollectionUtils.isEmpty(userBaseDOList)) {
            return Lists.newArrayList();
        }
        List<UserBaseDTO> userBaseDTOList = userBaseDOList.stream().map(curUserBaseDO -> {
            UserBaseDTO curUserBaseDTO = new UserBaseDTO();
            BeanUtils.copyProperties(curUserBaseDO, curUserBaseDTO);
            return curUserBaseDTO;
        }).collect(Collectors.toList());
        return userBaseDTOList;
    }

    @Override
    public Pagination<UserBaseDTO> queryPage(UserBaseDTO userBaseDTO, PageInfo pageInfo) {
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        UserBaseDO userBaseDO = new UserBaseDO();
        BeanUtils.copyProperties(userBaseDTO, userBaseDO);
        Page<UserBaseDO> userBaseDOPage = userBaseDao.queryPage(userBaseDO);
        if (CollectionUtils.isEmpty(userBaseDOPage)) {
            return new Pagination<>(0,0,0,0);
        }
        Pagination<UserBaseDTO> userBaseDTOPagination = new Pagination<>(userBaseDOPage.getPageNum(),
                userBaseDOPage.getPageSize(),userBaseDOPage.getTotal(),userBaseDOPage.getPages());

        List<UserBaseDTO> userBaseDTOList = userBaseDOPage.stream().map(curUserBaseDO -> {
            UserBaseDTO curUserBaseDTO = new UserBaseDTO();
            BeanUtils.copyProperties(curUserBaseDO, curUserBaseDTO);
            return curUserBaseDTO;
        }).collect(Collectors.toList());
        userBaseDTOPagination.setList(userBaseDTOList);
        return userBaseDTOPagination;
    }
}
