package com.fc.v2.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fc.v2.mapper.auto.TAmCheckItemMapper;
import com.fc.v2.model.auto.TAmCheckItem;
import com.fc.v2.service.ITAmCheckItemService;

/**
 * 定期检验记录明细 Service业务层处理（batch-process 形状：整批提交）
 *
 * @author fuce
 * @date 2026-09-14
 */
@Service
public class TAmCheckItemServiceImpl implements ITAmCheckItemService {

    private static final int MAX_ROWS = 500;
    private static final int STATUS_OK = 1;
    private static final int STATUS_FAIL = 2;

    @javax.annotation.Resource
    private TAmCheckItemMapper amCheckItemMapper;

    @Override
    public TAmCheckItem selectTAmCheckItemById(Long id) {
        return this.amCheckItemMapper.selectById(id);
    }

    @Override
    public int submitBatch(String batchNo, List<TAmCheckItem> rows) {
        String no = rows.get(0).getBatchNo();
        java.util.List<TAmCheckItem> errors = new java.util.ArrayList<TAmCheckItem>();
        int seq = 0;
        for (TAmCheckItem r : rows) {
            if (r.getItemCode() == null || r.getItemCode().trim().isEmpty()
                    || r.getQty() == null
                    || r.getQty().compareTo(java.math.BigDecimal.ZERO) <= 0) {
                seq++;
                r.setRowNo(Integer.valueOf(seq));
                r.setBatchNo(no);
                r.setStatus(STATUS_FAIL);
                this.amCheckItemMapper.insert(r);
                errors.add(r);
            }
        }
        if (!errors.isEmpty()) {
            return 0;
        }
        int ok = 0;
        for (TAmCheckItem r : rows) {
            r.setBatchNo(no);
            r.setStatus(STATUS_OK);
            this.amCheckItemMapper.insert(r);
            ok++;
        }
        return ok;
    }

    @Override
    public List<TAmCheckItem> listErrors(String batchNo) {
        return this.amCheckItemMapper.selectList(new QueryWrapper<TAmCheckItem>()
                .eq("batch_no", batchNo).eq("status", STATUS_FAIL));
    }
}
