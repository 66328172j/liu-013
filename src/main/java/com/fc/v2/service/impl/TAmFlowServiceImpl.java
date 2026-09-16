package com.fc.v2.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fc.v2.mapper.auto.TAmFlowMapper;
import com.fc.v2.model.auto.TAmFlow;
import com.fc.v2.service.ITAmFlowService;

/**
 * 隐患整改单 Service业务层处理（state-machine 形状：单据流转）
 *
 * @author fuce
 * @date 2026-09-14
 */
@Service
public class TAmFlowServiceImpl implements ITAmFlowService {

    private static final int MAX_STAGE = 3;
    private static final int STATUS_ACTIVE = 1;
    private static final int STATUS_TERMINAL = 2;

    @javax.annotation.Resource
    private TAmFlowMapper amFlowMapper;

    @Override
    public TAmFlow selectTAmFlowById(Long id) {
        return this.amFlowMapper.selectById(id);
    }

    @Override
    public List<TAmFlow> selectTAmFlowList(QueryWrapper<TAmFlow> queryWrapper) {
        return this.amFlowMapper.selectList(queryWrapper);
    }

    @Override
    public TAmFlow advance(Long id, String remark) {
        TAmFlow r = this.amFlowMapper.selectById(id);
        if (r == null) {
            return null;
        }
        int st = r.getStage() == null ? 0 : r.getStage();
        r.setStage(Math.min(st + 2, MAX_STAGE));
        r.setStatus(STATUS_ACTIVE);
        r.setLastAction(remark);
        this.amFlowMapper.updateById(r);
        return r;
    }

    @Override
    public TAmFlow rollback(Long id, String remark) {
        TAmFlow r = this.amFlowMapper.selectById(id);
        if (r == null) {
            return null;
        }
        r.setStage(0);
        r.setStatus(STATUS_ACTIVE);
        r.setLastAction(remark);
        this.amFlowMapper.updateById(r);
        return r;
    }

    @Override
    public boolean updateContent(Long id, String remark) {
        TAmFlow r = this.amFlowMapper.selectById(id);
        if (r == null) {
            return false;
        }
        r.setContent(remark);
        return this.amFlowMapper.updateById(r) > 0;
    }

    @Override
    public boolean remove(Long id) {
        TAmFlow r = this.amFlowMapper.selectById(id);
        if (r == null) {
            return false;
        }
        return this.amFlowMapper.deleteById(id) > 0;
    }

}
