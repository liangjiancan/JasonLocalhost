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
public interface ICrudService<DTO extends BaseDTO> extends IBaseService {

    /**
     * 根据主键查询
     *
     * @param: [id]
     * @return: DTO
     * @author: huyongjun
     * @date: 2020/5/14
     */
    DTO selectById(String id);

    /**
     * 新增
     *
     * @param: [dto]
     * @return: DTO
     * @author: huyongjun
     * @date: 2020/5/14
     */
    DTO insert(DTO dto);

    /**
     * 根据主键更新
     *
     * @param: [dto]
     * @return: DTO
     * @author: huyongjun
     * @date: 2020/5/14
     */
    DTO updateById(DTO dto);

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
