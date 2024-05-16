package net.lab1024.smartadmin.module.business.report.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.constant.SystemConst;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysProjectTeamStageVO;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysProjectVO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepAgencyBondAddDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepPeerReportPurchaseDTO;
import net.lab1024.smartadmin.module.business.report.domain.vo.*;
import net.lab1024.smartadmin.module.business.report.service.PeerReportPurchaseRecordService;
import net.lab1024.smartadmin.module.business.basics.service.SysProjectTeamStageService;
import net.lab1024.smartadmin.module.system.department.DepartmentService;
import net.lab1024.smartadmin.module.system.department.domain.dto.DepartmentVO;
import net.lab1024.smartadmin.module.system.login.domain.LoginDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Api(tags = {"同业报告购买及记录"})
@RestController
@RequestMapping("/service")
public class PeerReportPurchaseRecordController extends BaseController {
    //@Autowired
    //private PeerReportService peerReportService;
    @Autowired
    private SystemConst systemConst;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private SysProjectTeamStageService sysProjectTeamStageService;
    @Autowired
    private PeerReportPurchaseRecordService peerReportPurchaseRecordService;
    @ApiOperation(value = "去购买或者查看同业报告记录",notes = "@author wxf")
    @GetMapping("/toBuyOrToSee")
    public ModelAndView toBuyOrToSee(ModelAndView model,HttpServletRequest request) {
        this.modelAndViewObjects(model,request);
        HttpSession session = request.getSession();
        //项目信息
        if(session!=null){
            SysProjectVO sysProjectVO = (SysProjectVO) session.getAttribute("system_project");
            if(sysProjectVO!=null){
                ResponseDTO<SysProjectTeamStageVO> responseDTO = sysProjectTeamStageService.selectCurrentStageBusiness(sysProjectVO.getProId());
                SysProjectTeamStageVO sysProjectTeamStageVO = responseDTO.getData();
                if(sysProjectVO!=null){
                    //如果项目已结束，直接返回5阶段
                    if(("1").equals(sysProjectVO.getStatus())){
                        sysProjectTeamStageVO = new SysProjectTeamStageVO();
                        sysProjectTeamStageVO.setStageId(6L);
                        sysProjectTeamStageVO.setProId(sysProjectVO.getProId());
                        sysProjectTeamStageVO.setBusinessName("存贷款,财务管理,投资业务,人事后勤,市场营销,中间业务");
                    }
                }
                model.addObject("projectStage",sysProjectTeamStageVO);
            }
        }
        model.setViewName("student/xy_gmjl");
        return model;
    }
    @ApiOperation(value = "学员首页同业报告", notes = "学员首页同业报告")
    @GetMapping("/teamsPeerReport")
    public ResponseDTO<Map<String, Object>> teamsPeerReport(@RequestParam Long projectId,HttpServletRequest request){
        //数据封装
        Map<String,Object> result = new HashMap<String,Object>();
        LoginDetailVO vo = (LoginDetailVO)request.getSession().getAttribute("app_user_login");
        result = peerReportPurchaseRecordService.queryAllDepartmentAndStage(projectId,vo.getId(),request);
        return ResponseDTO.succData(result);
    }
    @ApiOperation(value = "添加购买同业合同记录表",notes = "@author SMS")
    @PostMapping("/saveTeamsPeerReportRecord")
    public ResponseDTO<String> saveTeamsPeerReportRecord(@RequestBody Map<String,Object> map,HttpServletRequest request){
        String projectId = (String)map.get("projectId");
        String teamId = (String)map.get("teamId");
        List checkVal = (List)map.get("checkVal");
        return peerReportPurchaseRecordService.saveTeamsPeerReportRecord(projectId,teamId,checkVal,request);
    }
    @GetMapping("/bg_cdk")
    public ModelAndView bgCdk(ModelAndView model,HttpServletRequest request){
        this.modelAndViewObjects(model,request);
        String projectId = request.getParameter("bgProjectId");
        String stageId = request.getParameter("bgStageId");
        String teamId = request.getParameter("bgTeamId");
        SysProjectTeamStageVO sysProjectTeamStageVO = new SysProjectTeamStageVO();
        sysProjectTeamStageVO.setStageId(Long.parseLong(stageId));
        sysProjectTeamStageVO.setTeamId(Long.parseLong(teamId));
        sysProjectTeamStageVO.setProId(Long.parseLong(projectId));
        sysProjectTeamStageVO.setBusinessName("存贷款,财务管理,投资业务,人事后勤,市场营销,中间业务");
        HttpSession session = request.getSession();
        session.setAttribute("projectStage",sysProjectTeamStageVO);
//        model.addObject("projectStage",sysProjectTeamStageVO);
        model.setViewName("student/bg_cdk");
        return model;
    }
    @GetMapping("/selectBalanceResultBg")
    public ResponseDTO<List<Map<Long, RepBalanceSheetVO>>> selectBalanceResultBg(@RequestParam("bgProjectId") Long projectId,@RequestParam("bgTeamId") Long teamId, @RequestParam("bgStageId")Long stageId){
        return peerReportPurchaseRecordService.selectBalanceResultBg(projectId,teamId,stageId);
    }
    @GetMapping("/selectProfitBg")
    public ResponseDTO<List<Map<Long, RepProfitVO>>> selectProfitBg(@RequestParam("bgProjectId") Long projectId,@RequestParam("bgTeamId") Long teamId, @RequestParam("bgStageId")Long stageId){
        return peerReportPurchaseRecordService.selectProfitBg(projectId,teamId,stageId);
    }
    @GetMapping("/selectLiquidityCashBg")
    public ResponseDTO<RepLiquidityCashVO> selectLiquidityCashBg(@RequestParam("bgProjectId") Long projectId,@RequestParam("bgTeamId") Long teamId, @RequestParam("bgStageId")Long stageId){
        return peerReportPurchaseRecordService.selectLiquidityCashBg(projectId,teamId,stageId);
    }
    @GetMapping("/selectInvestbusOperatDataStockBg")
    public ResponseDTO<Map<String,Map<String, RepInvestbusOperatDataStockVO>>> selectInvestbusOperatDataStockBg(@RequestParam("bgProjectId") Long projectId,@RequestParam("bgTeamId") Long teamId, @RequestParam("bgStageId")Long stageId){
        return peerReportPurchaseRecordService.selectInvestbusOperatDataStockBg(projectId,teamId,stageId);
    }
    @GetMapping("/selectInvestbusOperatDataBondBg")
    public ResponseDTO<Map<String, Map<String, RepInvestbusOperatDataBondVO>>> selectInvestbusOperatDataBond(@RequestParam("bgProjectId") Long projectId,@RequestParam("bgTeamId") Long teamId, @RequestParam("bgStageId")Long stageId){
        return peerReportPurchaseRecordService.selectInvestbusOperatDataBondBg(projectId,teamId,stageId);
    }
    //查询所有代理债券
    @GetMapping("/selectAgencyBondAllBg")
    public ResponseDTO<List<RepAgencyBondVO>> selectAgencyBondAllBg(@RequestParam("bgProjectId") Long projectId,@RequestParam("bgTeamId") Long teamId, @RequestParam("bgStageId")Long stageId){
        return peerReportPurchaseRecordService.selectAgencyBondAllBg(projectId,teamId,stageId);
    }
    @ApiOperation(value = "自动化投资返回到页面", notes = "@author SMS")
    @GetMapping("/selectPerLogDataAutomationBg")
    public ResponseDTO<RepPersonnelLogisticsDataAutomationVO> selectPerLogDataAutomation(@RequestParam("bgProjectId") Long projectId,@RequestParam("bgTeamId") Long teamId, @RequestParam("bgStageId")Long stageId){
        return peerReportPurchaseRecordService.selectPerLogDataAutomationBg(projectId,teamId,stageId);
    }
    @ApiOperation(value ="人事后勤数据表",notes = "@author wz")
    @GetMapping("/selectPeopleLogisticsBg")
    public ResponseDTO<Map<String,RepPeopleLogisticsVO>> selectPeopleLogistics(@RequestParam("bgProjectId") Long projectId,@RequestParam("bgTeamId") Long teamId, @RequestParam("bgStageId")Long stageId){
        return peerReportPurchaseRecordService.selectPeopleLogisticsBg(projectId,teamId,stageId);
    }
    @GetMapping("/bg_tzyw")
    public ModelAndView bgTzyw(ModelAndView model,HttpServletRequest request) {
        this.modelAndViewObjects(model,request);
        HttpSession session = request.getSession();
        //项目信息
        SysProjectVO sysProjectVO = (SysProjectVO) session.getAttribute("system_project");
        ResponseDTO<SysProjectTeamStageVO> responseDTO = sysProjectTeamStageService.selectCurrentStageBusiness(sysProjectVO.getProId());
        SysProjectTeamStageVO sysProjectTeamStageVO = responseDTO.getData();
        if(sysProjectVO!=null){
            //如果项目已结束，直接返回5阶段
            if(("1").equals(sysProjectVO.getStatus())){
                sysProjectTeamStageVO = new SysProjectTeamStageVO();
                sysProjectTeamStageVO.setStageId(6L);
                sysProjectTeamStageVO.setProId(sysProjectVO.getProId());
                sysProjectTeamStageVO.setBusinessName("存贷款,财务管理,投资业务,人事后勤,市场营销,中间业务");
            }
        }
        model.addObject("projectStage",sysProjectTeamStageVO);
        model.setViewName("student/bg_tzyw");
        return model;
    }

    @GetMapping("/bg_zjyw")
    public ModelAndView bgZjyw(ModelAndView model,HttpServletRequest request) {
        this.modelAndViewObjects(model,request);
        HttpSession session = request.getSession();
        //项目信息
        SysProjectVO sysProjectVO = (SysProjectVO) session.getAttribute("system_project");
        ResponseDTO<SysProjectTeamStageVO> responseDTO = sysProjectTeamStageService.selectCurrentStageBusiness(sysProjectVO.getProId());
        SysProjectTeamStageVO sysProjectTeamStageVO = responseDTO.getData();
        if(sysProjectVO!=null){
            //如果项目已结束，直接返回5阶段
            if(("1").equals(sysProjectVO.getStatus())){
                sysProjectTeamStageVO = new SysProjectTeamStageVO();
                sysProjectTeamStageVO.setStageId(6L);
                sysProjectTeamStageVO.setProId(sysProjectVO.getProId());
                sysProjectTeamStageVO.setBusinessName("存贷款,财务管理,投资业务,人事后勤,市场营销,中间业务");
            }
        }
        model.addObject("projectStage",sysProjectTeamStageVO);
        model.setViewName("student/bg_zjyw");
        return model;
    }

    @GetMapping("/bg_rshq")
    public ModelAndView bgRshq(ModelAndView model,HttpServletRequest request){
        this.modelAndViewObjects(model,request);
        HttpSession session = request.getSession();
        //项目信息
        SysProjectVO sysProjectVO = (SysProjectVO) session.getAttribute("system_project");
        ResponseDTO<SysProjectTeamStageVO> responseDTO = sysProjectTeamStageService.selectCurrentStageBusiness(sysProjectVO.getProId());
        SysProjectTeamStageVO sysProjectTeamStageVO = responseDTO.getData();
        if(sysProjectVO!=null){
            //如果项目已结束，直接返回5阶段
            if(("1").equals(sysProjectVO.getStatus())){
                sysProjectTeamStageVO = new SysProjectTeamStageVO();
                sysProjectTeamStageVO.setStageId(6L);
                sysProjectTeamStageVO.setProId(sysProjectVO.getProId());
                sysProjectTeamStageVO.setBusinessName("存贷款,财务管理,投资业务,人事后勤,市场营销,中间业务");
            }
        }
        model.addObject("projectStage",sysProjectTeamStageVO);
        model.setViewName("student/bg_rshq");
        return model;
    }

    @GetMapping("/bg_scyx")
    public ModelAndView bgScyx(ModelAndView model,HttpServletRequest request){
        this.modelAndViewObjects(model,request);
        HttpSession session = request.getSession();
        //项目信息
        SysProjectVO sysProjectVO = (SysProjectVO) session.getAttribute("system_project");
        ResponseDTO<SysProjectTeamStageVO> responseDTO = sysProjectTeamStageService.selectCurrentStageBusiness(sysProjectVO.getProId());
        SysProjectTeamStageVO sysProjectTeamStageVO = responseDTO.getData();
        if(sysProjectVO!=null){
            //如果项目已结束，直接返回5阶段
            if(("1").equals(sysProjectVO.getStatus())){
                sysProjectTeamStageVO = new SysProjectTeamStageVO();
                sysProjectTeamStageVO.setStageId(6L);
                sysProjectTeamStageVO.setProId(sysProjectVO.getProId());
                sysProjectTeamStageVO.setBusinessName("存贷款,财务管理,投资业务,人事后勤,市场营销,中间业务");
            }
        }
        model.addObject("projectStage",sysProjectTeamStageVO);
        model.setViewName("student/bg_scyx");
        return model;
    }

    @GetMapping("/bg_cwgl")
    public ModelAndView bgCwgl(ModelAndView model,HttpServletRequest request){
        this.modelAndViewObjects(model,request);
        HttpSession session = request.getSession();
        //项目信息
        SysProjectVO sysProjectVO = (SysProjectVO) session.getAttribute("system_project");
        ResponseDTO<SysProjectTeamStageVO> responseDTO = sysProjectTeamStageService.selectCurrentStageBusiness(sysProjectVO.getProId());
        SysProjectTeamStageVO sysProjectTeamStageVO = responseDTO.getData();
        if(sysProjectVO!=null){
            //如果项目已结束，直接返回5阶段
            if(("1").equals(sysProjectVO.getStatus())){
                sysProjectTeamStageVO = new SysProjectTeamStageVO();
                sysProjectTeamStageVO.setStageId(6L);
                sysProjectTeamStageVO.setProId(sysProjectVO.getProId());
                sysProjectTeamStageVO.setBusinessName("存贷款,财务管理,投资业务,人事后勤,市场营销,中间业务");
            }
        }
        model.addObject("projectStage",sysProjectTeamStageVO);
        model.setViewName("student/bg_cwgl");
        return model;
    }

    @GetMapping("/bgerror")
    public ModelAndView bgerrorView(ModelAndView model,HttpServletRequest request){
        model.setViewName("error");
        return model;
    }
    private void modelAndViewObjects(ModelAndView model, HttpServletRequest request){
        model.addObject("title", systemConst.getTitle());
        model.addObject("copyright", systemConst.getCopyright());
        model.addObject("icpsecurity", systemConst.getIcpsecurity());
        model.addObject("plcecurity", systemConst.getPlcecurity());
        model.addObject("hotmail", systemConst.getHotmail());
        model.addObject("hotphone", systemConst.getHotphone());
    }
}
