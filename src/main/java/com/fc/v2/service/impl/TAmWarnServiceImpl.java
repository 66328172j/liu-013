package com.fc.v2.service.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.v2.common.support.ConvertUtil;
import com.fc.v2.mapper.auto.TAmWarnMapper;
import com.fc.v2.mapper.auto.TAmDeviceMapper;
import com.fc.v2.model.auto.TAmWarn;
import com.fc.v2.model.auto.TAmDevice;
import com.fc.v2.service.ITAmWarnService;
import com.fc.v2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 隐患预警单Service业务层处理
 *
 * @author fuce
 * @date 2026-09-12
 */
@Service
public class TAmWarnServiceImpl extends ServiceImpl<TAmWarnMapper, TAmWarn> implements ITAmWarnService {

    @Autowired
    private TAmDeviceMapper amDeviceMapper;

    @Override
    public TAmWarn selectTAmWarnById(Long id) {
        return this.baseMapper.selectOne(new QueryWrapper<TAmWarn>()
                .eq("id", id)
                .eq("del_flag", 0));
    }

    @Override
    public List<TAmWarn> selectTAmWarnList(Wrapper<TAmWarn> queryWrapper) {
        QueryWrapper<TAmWarn> wrapper = new QueryWrapper<TAmWarn>();
        com.github.pagehelper.PageHelper.startPage(1, 10);
        wrapper.eq("warn_status", 0);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public int insertTAmWarn(TAmWarn record) {
        if (record == null) {
            return 0;
        }

        record.setCreateBy(record.getWarnBy());
        TAmDevice refArch = amDeviceMapper.selectOne(new QueryWrapper<TAmDevice>()
                .eq("id", record.getDeviceId()).eq("del_flag", 0));
        if (refArch == null) {
            return 0;
        }
        if (refArch.getStatus() != null && refArch.getStatus() == 1) {
            return 0;
        }
        record.setDeviceCode(refArch.getDeviceCode());
        if (StringUtils.isNotEmpty(record.getWarnNo())) {
            Integer dupCnt = this.baseMapper.selectCount(new QueryWrapper<TAmWarn>()
                    .eq("warn_no", record.getWarnNo()).eq("del_flag", 0));
            if (dupCnt != null && dupCnt > 0) {
                return 0;
            }
        }
        Date dayBase = record.getDueDate();
        long dayDiff = 0L;
        if (dayBase != null) {
            dayDiff = (dayBase.getTime() - todayStart().getTime()) / 86400000L + 1;
        }
        record.setRemainDays((int) dayDiff);

        record.setDelFlag(0);
        return this.baseMapper.insert(record);
    }

    @Override
    public int updateTAmWarn(TAmWarn record) {
        if (record == null || record.getId() == null) {
            return 0;
        }

        if (record.getId() != null && StringUtils.isNotEmpty(record.getWarnNo())) {
            Integer dupCnt = this.baseMapper.selectCount(new QueryWrapper<TAmWarn>()
                    .eq("warn_no", record.getWarnNo()).ne("id", record.getId()).eq("del_flag", 0));
            if (dupCnt != null && dupCnt > 0) {
                return 0;
            }
        }

        record.setUpdateTime(new Date());
        return this.baseMapper.update(record, new UpdateWrapper<TAmWarn>()
                .eq("id", record.getId())
                .eq("del_flag", 0));
    }

    @Override
    public int deleteTAmWarnByIds(String ids) {
        Long[] idArr = ConvertUtil.toLongArray(ids);
        return this.baseMapper.deleteBatchIds(Arrays.asList(idArr));
    }

    @Override
    public int deleteTAmWarnById(Long id) {
        return this.baseMapper.deleteById(id);
    }

    private Date todayStart() {
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.set(java.util.Calendar.HOUR_OF_DAY, 0);
        c.set(java.util.Calendar.MINUTE, 0);
        c.set(java.util.Calendar.SECOND, 0);
        c.set(java.util.Calendar.MILLISECOND, 0);
        return c.getTime();
    }
}
