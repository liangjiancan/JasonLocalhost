package com.jason.jlh.common.service;

import com.jason.jlh.common.pojo.BaseDTO;

/**
 * @title: ICrudService
 * @package: com.jason.jlh.common.service
 * @description: CRUD服务接口
 * @author: huyongjun
 * @date: 2020/5/14
 * @version: v1.0
 */
public interface ICrudService<Dto extends BaseDTO> extends IBaseService {

    /**
     * 根据主键查询
     * 
     * @param: [id]
     * @return: Dto
     * @author: huyongjun
     * @date: 2020/5/14
     */
    Dto selectById(String id);

    /**
     * 新增
     * 
     * @param: [dto]
     * @return: Dto
     * @author: huyongjun
     * @date: 2020/5/14
     */
    Dto insert(Dto dto);

    /**
     * 根据主键更新
     * 
     * @param: [dto]
     * @return: Dto
     * @author: huyongjun
     * @date: 2020/5/14
     */
    Dto updateById(Dto dto);

    /**
     * 根据主键删除
     * 
     * @param: [id]
     * @return: boolean
     * @author: huyongjun
     * @date: 2020/5/14
     */
    boolean deleteById(String id);
}
