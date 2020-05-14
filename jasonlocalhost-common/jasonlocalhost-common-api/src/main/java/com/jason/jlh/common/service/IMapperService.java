package com.jason.jlh.common.service;

import com.jason.jlh.common.pojo.BaseDTO;

/**
 * @title: IMapperService
 * @package: com.jason.jlh.common.service
 * @description: CRUD服务接口
 * @author: huyongjun
 * @date: 2020/5/14
 * @version: v1.0
 */
public interface IMapperService<Dto extends BaseDTO> extends IBaseService {

    Dto selectById(String id);

    Dto insert(Dto dto);

    Dto updateById(Dto dto);

    boolean deleteById(String id);
}
