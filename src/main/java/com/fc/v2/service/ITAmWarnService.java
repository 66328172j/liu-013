package com.fc.v2.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.fc.v2.model.auto.TAmWarn;

import java.util.List;

/**
 * 隐患预警单 Service接口
 *
 * @author fuce
 * @date 2026-09-12
 */
public interface ITAmWarnService {

    /** 按主键查询 */
    TAmWarn selectTAmWarnById(Long id);

    /** 按条件查询列表（分页由调用方统一处理） */
    List<TAmWarn> selectTAmWarnList(Wrapper<TAmWarn> queryWrapper);

    /** 新增 */
    int insertTAmWarn(TAmWarn record);

    /** 修改 */
    int updateTAmWarn(TAmWarn record);

    /** 批量删除 */
    int deleteTAmWarnByIds(String ids);

    /** 按主键删除 */
    int deleteTAmWarnById(Long id);
}
