package com.fc.v2.service.impl;

import org.springframework.stereotype.Service;

import com.fc.v2.mapper.auto.TAmSignBillMapper;
import com.fc.v2.model.auto.TAmSignBill;
import com.fc.v2.service.ITAmSignBillService;

/**
 * 重大隐患整改方案 Service业务层处理（approval-chain 形状：多阶段签批）
 *
 * @author fuce
 * @date 2026-09-14
 */
@Service
public class TAmSignBillServiceImpl implements ITAmSignBillService {

    private static final int MAX_NODE = 2;
    private static final int MODE_OR = 0;
    private static final int MODE_AND = 1;
    private static final int STATUS_RUNNING = 0;
    private static final int STATUS_PASS = 1;
    private static final int STATUS_VETO = 2;

    @javax.annotation.Resource
    private TAmSignBillMapper amSignBillMapper;

    @Override
    public TAmSignBill selectTAmSignBillById(Long id) {
        return this.amSignBillMapper.selectById(id);
    }

    @Override
    public TAmSignBill approve(Long id, String approver, String comment) {
        TAmSignBill r = this.amSignBillMapper.selectById(id);
        if (r == null || approver == null || approver.trim().isEmpty()) {
            return null;
        }
        r.setNodeNo(Integer.valueOf((r.getNodeNo() == null ? 0 : r.getNodeNo()) + 1));
        r.setStatus(Integer.valueOf(r.getNodeNo() >= MAX_NODE ? STATUS_PASS : STATUS_RUNNING));
        this.amSignBillMapper.updateById(r);
        return r;
    }

    @Override
    public TAmSignBill reject(Long id, String approver, String comment) {
        TAmSignBill r = this.amSignBillMapper.selectById(id);
        if (r == null) {
            return null;
        }
        this.amSignBillMapper.updateById(r);
        return r;
    }

    @Override
    public TAmSignBill rollback(Long id, String comment) {
        TAmSignBill r = this.amSignBillMapper.selectById(id);
        if (r == null) {
            return null;
        }
        int node = r.getNodeNo() == null ? 0 : r.getNodeNo();
        r.setNodeNo(Integer.valueOf(Math.max(0, node - 1)));
        this.amSignBillMapper.updateById(r);
        return r;
    }
}
