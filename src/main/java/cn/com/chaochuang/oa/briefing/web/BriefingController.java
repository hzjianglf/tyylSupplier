/*
 * FileName:    BriefingController.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2016
 * History:     2016年1月25日 (HM) 1.0 Create
 */

package cn.com.chaochuang.oa.briefing.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.chaochuang.common.bean.Page;
import cn.com.chaochuang.common.bean.ReturnInfo;
import cn.com.chaochuang.common.beancopy.BeanCopyBuilder;
import cn.com.chaochuang.common.data.persistence.SearchBuilder;
import cn.com.chaochuang.common.log.reference.LogStatus;
import cn.com.chaochuang.common.log.reference.SjType;
import cn.com.chaochuang.common.log.service.LogService;
import cn.com.chaochuang.common.reference.StatusFlag;
import cn.com.chaochuang.common.security.util.UserTool;
import cn.com.chaochuang.common.user.domain.SysUser;
import cn.com.chaochuang.common.user.service.SysUserService;
import cn.com.chaochuang.common.util.SearchListHelper;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.common.attach.service.SysAttachService;
import cn.com.chaochuang.oa.briefing.bean.BriefingEditBean;
import cn.com.chaochuang.oa.briefing.bean.BriefingShowBean;
import cn.com.chaochuang.oa.briefing.domain.OaBriefing;
import cn.com.chaochuang.oa.briefing.service.OaBriefingService;
import cn.com.chaochuang.oa.notice.domain.OaNotice;

/**
 * @author HM
 *
 */
@Controller
@RequestMapping("oa/briefing")
public class BriefingController {

    @Autowired
    private OaBriefingService    briefingService;

    @Autowired
    private SysAttachService     attachService;

    @Autowired
    protected ConversionService  conversionService;
    
    @Autowired
    private SysUserService       userService;
    
    @Autowired
    private LogService           logService;

    @RequestMapping("/list")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("/oa/briefing/list");
        return mav;
    }

    @RequestMapping("/query")
    public ModelAndView query() {
        ModelAndView mav = new ModelAndView("/oa/briefing/queryList");
        return mav;
    }

    @RequestMapping("/list.json")
    @ResponseBody
    public Page listjson(Integer page, Integer rows, HttpServletRequest request) {
        SearchBuilder<OaBriefing, Long> searchBuilder = new SearchBuilder<OaBriefing, Long>(conversionService);
        searchBuilder.clearSearchBuilder().findSearchParam(request);
        searchBuilder.getFilterBuilder().notEqual("status", StatusFlag.已删除);
         searchBuilder.getFilterBuilder().equal("creatorId", UserTool.getInstance().getCurrentUserId());
         searchBuilder.appendSort(Direction.DESC, "displayType");
         searchBuilder.appendSort(Direction.DESC, "createDate");
        SearchListHelper<OaBriefing> listhelper = new SearchListHelper<OaBriefing>();
        listhelper.execute(searchBuilder, briefingService.getRepository(), page, rows);
        Page p = new Page();
        p.setRows(BeanCopyBuilder.buildList(listhelper.getList(), BriefingShowBean.class));
        p.setTotal(listhelper.getCount());

        return p;
    }

    // 导出EXCEL
    @RequestMapping("/export.json")
    public ModelAndView exportBriefing(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("oa/briefing/excel/briefingListExport");
        mav.addObject("page", this.listjson(1, 1000000000, request));
        mav.addObject("fileName", "简报" + Tools.DATE_FORMAT5.format(new Date()));
        return mav;
    }

    @RequestMapping("/querylist.json")
    @ResponseBody
    public Page querylistjson(Integer page, Integer rows, HttpServletRequest request) {
        String title = request.getParameter("title");
        String department = request.getParameter("department");
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        String briefType = request.getParameter("briefType");
        String currentDeptId = UserTool.getInstance().getCurrentUserDepartmentId();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date dt1 = null, dt2 = null;
        try {
            if (StringUtils.isNotBlank(fromDate)) {
                dt1 = df.parse(fromDate + " 00:00");
            }
            if (StringUtils.isNotBlank(toDate)) {
                dt2 = df.parse(toDate + " 23:59");
            }
        } catch (ParseException e) {

        }
        return briefingService.selectAllForDeptShow(title, department, dt1, dt2, briefType, currentDeptId, page, rows);
    }
    
    // 导出EXCEL
    @RequestMapping("/exportQuery.json")
    public ModelAndView exportBriefingQuery(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("oa/briefing/excel/briefingQueryExport");
        mav.addObject("page", this.querylistjson(1, 1000000000, request));
        return mav;
    }

    @RequestMapping("/new")
    public ModelAndView newPage() {
        SysUser user = (SysUser) UserTool.getInstance().getCurrentUser();
        ModelAndView mav = new ModelAndView("/oa/briefing/edit");
        mav.addObject("currUser", user);
        mav.addObject("createTime", new Date());
        return mav;
    }

    @RequestMapping("/edit")
    public ModelAndView editPage(String id) {
        ModelAndView mav = new ModelAndView("/oa/briefing/edit");
        OaBriefing briefing = this.briefingService.findOne(id);
        mav.addObject("briefing", briefing);
        mav.addObject("attachMap", this.attachService.getAttachMap(briefing.getId().toString(), OaBriefing.class.getSimpleName()));
        if (briefing.getCreatorId() != null) {
            mav.addObject("createName", userService.findOne(briefing.getCreatorId()).getUserName());
        }
        return mav;
    }

    // 发布
    @RequestMapping("/save.json")
    @ResponseBody
    public ReturnInfo save(BriefingEditBean bean, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (bean != null) {
                bean.setStatus(StatusFlag.已发布);
            }
            SysUser user = (SysUser) UserTool.getInstance().getCurrentUser();
            if (user != null) {
                if (Tools.isEmptyString(bean.getId())) {
                    logService.saveLog(SjType.普通操作, "新增简报信息",LogStatus.成功, request);
                } else {
                    logService.saveLog(SjType.普通操作, "修改简报信息，修改id为'" + bean.getId() + "'的记录",LogStatus.成功, request);
                }
            }
            String briefingId = this.briefingService.saveBriefing(bean);

            return new ReturnInfo(briefingId, null, "发布成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnInfo(null, "服务器连接不上！", null);
        }
    }

    // 暂存
    @RequestMapping("/saveTemp.json")
    @ResponseBody
    public ReturnInfo saveTemp(BriefingEditBean bean, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (bean != null) {
                bean.setStatus(StatusFlag.暂存);
            }
            SysUser user = (SysUser) UserTool.getInstance().getCurrentUser();
            String briefingId = this.briefingService.saveBriefing(bean);
            logService.saveLog(SjType.普通操作, "暂存简报信息",LogStatus.成功, request);
            return new ReturnInfo(briefingId, null, "暂存成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnInfo(null, "服务器连接不上！", null);
        }
    }

    // 删除
    @RequestMapping("/delete.json")
    @ResponseBody
    public ReturnInfo del(String[] ids, HttpServletRequest request) {
        try {
            if (ids != null && ids.length > 0) {
                for (String noticeId : ids) {
                    this.briefingService.delBriefing(noticeId);
                    logService.saveLog(SjType.普通操作, "删除简报信息：,删除id为'" + noticeId + "'的记录",LogStatus.成功, request);
                }
            }

            return new ReturnInfo("1", null, "删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnInfo(null, "服务器连接不上！", null);
        }
    }

    @RequestMapping("/detail")
    @ResponseBody
    public ModelAndView detail(String id) {
        ModelAndView mav = new ModelAndView("/oa/briefing/detail");
        OaBriefing briefing = null;
        if (id != null) {
        	briefing = this.briefingService.findOne(id);
            if (briefing != null) {
                mav.addObject("briefing", briefing);
                mav.addObject("attachMap",
                                this.attachService.getAttachMap(String.valueOf(briefing.getId()),
                                		OaBriefing.class.getSimpleName()));
                if (briefing.getCreatorId() != null) {
                    mav.addObject("createName", this.userService.findOne(briefing.getCreatorId()).getUserName());
                }
            }
        }
        return mav;
    }
}