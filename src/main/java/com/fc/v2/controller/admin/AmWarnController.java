package com.fc.v2.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fc.v2.common.base.BaseController;
import com.fc.v2.common.domain.AjaxResult;
import com.fc.v2.common.domain.ResultTable;
import com.fc.v2.common.log.Log;
import com.fc.v2.model.auto.TAmWarn;
import com.fc.v2.service.ITAmWarnService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 隐患预警单 Controller
 *
 * @author fuce
 * @date 2026-09-12
 */
@Api(value = "隐患预警单")
@Controller
@RequestMapping("/AmWarnController")
public class AmWarnController extends BaseController {

    private final String prefix = "admin/amWarn";

    @Autowired
    private ITAmWarnService amWarnService;

    @ApiOperation(value = "分页跳转", notes = "分页跳转")
    @GetMapping("/view")
    @RequiresPermissions("amuse:amWarn:view")
    public String view(ModelMap model) {
        return prefix + "/list";
    }

    @Log(title = "隐患预警单集合查询", action = "list")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/list")
    @RequiresPermissions("amuse:amWarn:list")
    @ResponseBody
    public ResultTable list(TAmWarn record) {
        QueryWrapper<TAmWarn> queryWrapper = new QueryWrapper<TAmWarn>();
        startPage();
        com.github.pagehelper.PageInfo<TAmWarn> page =
                new com.github.pagehelper.PageInfo<TAmWarn>(amWarnService.selectTAmWarnList(queryWrapper));
        return pageTable(page.getList(), page.getTotal());
    }

    @Log(title = "隐患预警单新增", action = "add")
    @ApiOperation(value = "新增", notes = "新增")
    @PostMapping("/add")
    @RequiresPermissions("amuse:amWarn:add")
    @ResponseBody
    public AjaxResult add(TAmWarn record) {
        return toAjax(amWarnService.insertTAmWarn(record));
    }

    @Log(title = "隐患预警单修改", action = "edit")
    @ApiOperation(value = "修改保存", notes = "修改保存")
    @PostMapping("/edit")
    @RequiresPermissions("amuse:amWarn:edit")
    @ResponseBody
    public AjaxResult editSave(TAmWarn record) {
        return toAjax(amWarnService.updateTAmWarn(record));
    }

    @Log(title = "隐患预警单删除", action = "remove")
    @ApiOperation(value = "删除", notes = "删除")
    @DeleteMapping("/remove")
    @RequiresPermissions("amuse:amWarn:remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(amWarnService.deleteTAmWarnByIds(ids));
    }
}
