package net.lab1024.smartadmin.thymeleaf.login;

import io.swagger.annotations.ApiOperation;
import net.lab1024.smartadmin.common.anno.NoNeedLogin;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.constant.SystemConst;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysProjectTeamStageVO;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysProjectVO;
import net.lab1024.smartadmin.module.business.basics.service.SysProjectService;
import net.lab1024.smartadmin.module.business.basics.service.SysProjectTeamStageService;
import net.lab1024.smartadmin.module.business.report.service.PreOperationService;
import net.lab1024.smartadmin.module.business.teacher.dao.TMessageDao;
import net.lab1024.smartadmin.module.system.employee.constant.EmployeeResponseCodeConst;
import net.lab1024.smartadmin.module.system.employee.domain.dto.EmployeeLoginFormDTO;
import net.lab1024.smartadmin.module.system.login.LoginService;
import net.lab1024.smartadmin.module.system.login.domain.LoginDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by ${} on ${}.
 * 所有学员、教师端请求路径统一以service开头
 */
@Controller
@RequestMapping("/service")
public class LoginThymeleafController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private SystemConst systemConst;
    @Autowired
    private SysProjectTeamStageService sysProjectTeamStageService;
    @Autowired
    private SysProjectService sysProjectService;
    @Autowired
    private PreOperationService preOperationService;
    @Autowired
    private TMessageDao tMessageDao;
    /**
     * 跳转登录页
     * @param model
     * @return
     */
    @GetMapping("/login")
    @NoNeedLogin
    public ModelAndView loginView(ModelAndView model){
        model.addObject("title", systemConst.getTitle());
        model.addObject("copyright", systemConst.getCopyright());
        model.addObject("icpsecurity", systemConst.getIcpsecurity());
        model.addObject("plcecurity", systemConst.getPlcecurity());
        model.setViewName("login");
        return model;
    }

    /**
     * 学员、教师登录
     * @param name
     * @param password
     * @param request
     * @param model
     * @param response
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/userLogin")
    @NoNeedLogin
    @ApiOperation(value = "用户登录", notes = "用户登录")
    public ModelAndView loginAction(@RequestParam(value = "name",required = true)String name, @RequestParam(value = "password",required = true)String password,
                                    HttpServletRequest request, ModelAndView model, HttpServletResponse response, RedirectAttributes redirectAttributes) {

        try{
            EmployeeLoginFormDTO loginForm = new EmployeeLoginFormDTO();
            loginForm.setLoginName(name);
            loginForm.setLoginPwd(password);
            EmployeeResponseCodeConst info = loginService.userLogin(loginForm, request);
            //登录成功
            if(info == null){
                model.setViewName("redirect:/service/home");
            }else{
                //返回错误代码
                redirectAttributes.addAttribute("error",info.getCode());
//                model.addObject("error",info);
                model.setViewName("redirect:/service/login");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return model;
    }

    /**
     * 学员教师退出
     * @param model
     * @return
     */
    @GetMapping("/logout")
    @NoNeedLogin
    public ModelAndView logout(ModelAndView model,HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("app_user_login");
        model.setViewName("redirect:/service/login");
        return model;
    }
    /**
     * 学员/教师登录成功后系统首页
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/home")
    public ModelAndView userHome(ModelAndView model,HttpServletRequest request){
        //跳转页面时统一封装数据
        this.modelAndViewObjects(model,request);
        //封装当前项目的阶段信息
        HttpSession session = request.getSession();
        //封装项目正在进行中阶段信息
        model.setViewName("teacher/js_index");
        //判断角色跳转不同视图
        LoginDetailVO loginDetailVO = (LoginDetailVO)session.getAttribute("app_user_login");
        if(loginDetailVO!=null){
            if("学员".equals(loginDetailVO.getUserPosition())){
                //当前项目信息
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
                model.setViewName("student/xy_index");
            }else{
                model.setViewName("teacher/js_index");
            }
        }
        return model;
    }

    @GetMapping("/xy_cdk")
    public ModelAndView XyCdk(ModelAndView model,HttpServletRequest request){
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
        model.setViewName("student/xy_cdk");
        return model;
    }

    @GetMapping("/yjs")
    public ModelAndView yjsCdk(ModelAndView model, HttpServletRequest request,@RequestParam("currentProjectId") Long currentProjectId,@RequestParam("currentTeamId") Long currentTeamId, @RequestParam("currentStageId")Long currentStageId){
//        public ModelAndView yjsCdk(ModelAndView model, HttpServletRequest request){
        this.modelAndViewObjects(model,request);
        HttpSession session = request.getSession();
        LoginDetailVO loginDetailVO = (LoginDetailVO)session.getAttribute("app_user_login");
        //计算
        Map<String,Object> map = preOperationService.preOperation(currentProjectId,currentTeamId,currentStageId);
        model.addObject("map",map);
//        model.addObject("loginDetailVO",loginDetailVO);
        model.setViewName("student/yjs");
        return model;
    }

    @GetMapping("/xy_tzyw")
    public ModelAndView xyTzyw(ModelAndView model,HttpServletRequest request) {
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
        model.setViewName("student/xy_tzyw");
        return model;
    }

    @GetMapping("/xy_zjyw")
    public ModelAndView xyZjyw(ModelAndView model,HttpServletRequest request) {
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
        model.setViewName("student/xy_zjyw");
        return model;
    }

    @GetMapping("/xy_rshq")
    public ModelAndView xyRshq(ModelAndView model,HttpServletRequest request){
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
        model.setViewName("student/xy_rshq");
        return model;
    }

    @GetMapping("/xy_scyx")
    public ModelAndView xyScyx(ModelAndView model,HttpServletRequest request){
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
        model.setViewName("student/xy_scyx");
        return model;
    }

    @GetMapping("/xy_cwgl")
    public ModelAndView xyCwgl(ModelAndView model,HttpServletRequest request){
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
        model.setViewName("student/xy_cwgl");
        return model;
    }

    @GetMapping("/xy_zsk")
    public ModelAndView xyZsk(ModelAndView model,HttpServletRequest request){
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
        model.setViewName("student/xy_zsk");
        return model;
    }


    @GetMapping("/xy_yhxx")
    public ModelAndView xyYhxx(ModelAndView model,HttpServletRequest request){
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
        model.setViewName("student/xy_yhxx");
        return model;
    }

    @GetMapping("/xy_xx")
    public ModelAndView xyXx(ModelAndView model,HttpServletRequest request){
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
        model.setViewName("student/xy_xx");
        return model;
    }
    //团队管理
    @GetMapping("/js_tdgl")
    public ModelAndView jsTdgl(ModelAndView model, HttpServletRequest request){
        this.modelAndViewObjects(model,request);
        model.setViewName("teacher/js_tdgl");
        return model;
    }
    //添加团队成员
    @GetMapping("/js_tjtdcy")
    public ModelAndView jsTjtdcy(ModelAndView model, HttpServletRequest request,@RequestParam("proId") Long proId){
        this.modelAndViewObjects(model,request);
        model.addObject("proId",proId);
        model.setViewName("teacher/js_tdgl_tdcy");
        return model;
    }
    //修改团队成员
    @GetMapping("/js_xgtdcy")
    public ModelAndView jsXgtdcy(ModelAndView model, HttpServletRequest request,@RequestParam("empId") Long empId){
        this.modelAndViewObjects(model,request);
        model.addObject("empId",empId);
        model.setViewName("teacher/js_tdgl_tdcy");
        return model;
    }

    //添加团队成员角色
    @GetMapping("/js_tjtdjs")
    public ModelAndView jsTjtdjs(ModelAndView model, HttpServletRequest request){
        this.modelAndViewObjects(model,request);
        model.setViewName("teacher/js_tdgl_js");
        return model;
    }
    //修改团队信息
    @GetMapping("/js_xgtd")
    public ModelAndView jsXgtd(ModelAndView model, HttpServletRequest request,@RequestParam("teamId") Long teamId){
        this.modelAndViewObjects(model,request);
        model.addObject("teamId",teamId);
        model.setViewName("teacher/js_tdgl_tdxx");
        return model;
    }

    /**
     * edit personel-cost for one project
     * @param model
     * @param request
     * @param sysProId
     * @param releaseType
     * @return
     */
    @GetMapping("/hr_init_param")
    public ModelAndView hrInitParams(ModelAndView model, HttpServletRequest request,
                                     @RequestParam("sysProId") Long sysProId,
                                     @RequestParam("releaseType") Integer releaseType){
        String sysProName = sysProjectService.queryData(sysProId).getProName();
        this.modelAndViewObjects(model,request);
        HttpSession session = request.getSession();
        LoginDetailVO loginDetailVO = (LoginDetailVO)session.getAttribute("app_user_login");
        if(releaseType==1){
            model.addObject("releaseTypeName","人力成本维护");
            model.addObject("releaseType",1);
        }
        model.addObject("sysProName",sysProName);
        model.addObject("sysProId",sysProId);
        model.addObject("loginDetailVO",loginDetailVO);
        model.setViewName("teacher/parser_init_hr_data");
        return model;
    }
    //新增市场环境
    @GetMapping("/js_xzschj")
    public ModelAndView jsXzschj(ModelAndView model, HttpServletRequest request, @RequestParam("releaseType")Integer releaseType){
        this.modelAndViewObjects(model,request);
        model.setViewName("teacher/js_cjgl_tj_schj");
        return model;
    }

    //新增场景
    @GetMapping("/js_xzcj")
    public ModelAndView jsXzcj(ModelAndView model, HttpServletRequest request, @RequestParam("releaseType")Integer releaseType){
        this.modelAndViewObjects(model,request);
        HttpSession session = request.getSession();
        LoginDetailVO loginDetailVO = (LoginDetailVO)session.getAttribute("app_user_login");
        if(releaseType==1){
            model.addObject("releaseTypeName","市场环境");
            model.addObject("releaseType","1");
        }else if(releaseType==2){
            model.addObject("releaseTypeName","突发事件");
            model.addObject("releaseType","2");
        }else if(releaseType==3){
            model.addObject("releaseTypeName","副本事件");
            model.addObject("releaseType","3");
        }else if(releaseType==4){
            model.addObject("releaseTypeName","风险预警");
            model.addObject("releaseType","4");
        }
        model.addObject("loginDetailVO",loginDetailVO);
        model.setViewName("teacher/js_cjgl_tj");
        return model;
    }

    @GetMapping("/add_params")
    public ModelAndView addParams(ModelAndView model,
                                  HttpServletRequest request,
                                  @RequestParam("sysProName") String sysProName,
                                  @RequestParam("releaseType") Integer releaseType
                                  ){
        this.modelAndViewObjects(model,request);
        HttpSession session = request.getSession();
        LoginDetailVO loginDetailVO = (LoginDetailVO)session.getAttribute("app_user_login");
        if(releaseType==1){
            model.addObject("releaseTypeName","人力成本");
            model.addObject("releaseType","1");
        }
        model.addObject("sysProName",sysProName);
        model.addObject("loginDetailVO",loginDetailVO);
        model.setViewName("teacher/parser_init_hr_data");
        return model;
    }
    @GetMapping("/new_params")
    public ModelAndView newParams(ModelAndView model,HttpServletRequest request,@RequestParam("releaseType") Integer releaseType){
        this.modelAndViewObjects(model,request);
        HttpSession session = request.getSession();
        LoginDetailVO loginDetailVO = (LoginDetailVO)session.getAttribute("app_user_login");
        if(releaseType==1){
            model.addObject("releaseTypeName","人力成本");
            model.addObject("releaseType","1");
        }
        model.addObject("loginDetailVO",loginDetailVO);
        model.setViewName("teacher/add_init_hr_data");
        return model;
    }

    //修改场景
    @GetMapping("/js_xgcj")
    public ModelAndView jsXgcj(ModelAndView model, HttpServletRequest request,@RequestParam("id")Long id, @RequestParam("releaseType")Integer releaseType){
        this.modelAndViewObjects(model,request);
//        TMessageEntity tMessageEntity = tMessageDao.selectById(id);
        HttpSession session = request.getSession();
        LoginDetailVO loginDetailVO = (LoginDetailVO)session.getAttribute("app_user_login");
        if(releaseType==1){
            model.addObject("releaseTypeName","市场环境");
            model.addObject("releaseType","1");
        }else if(releaseType==2){
            model.addObject("releaseTypeName","突发事件");
            model.addObject("releaseType","2");
        }else if(releaseType==3){
            model.addObject("releaseTypeName","副本事件");
            model.addObject("releaseType","3");
        }else if(releaseType==4){
            model.addObject("releaseTypeName","风险预警");
            model.addObject("releaseType","4");
        }
        model.addObject("loginDetailVO",loginDetailVO);
//        model.addObject("tMessageEntity",tMessageEntity);
        model.addObject("id",id);
        model.addObject("releaseType",releaseType);
        model.setViewName("teacher/js_cjgl_tj");
        return model;
    }

    //首页新增场景
    @GetMapping("/js_xzcj_sy")
    public ModelAndView jsXzcjSy(ModelAndView model, HttpServletRequest request, @RequestParam("releaseType")Integer releaseType){
        this.modelAndViewObjects(model,request);
        HttpSession session = request.getSession();
        LoginDetailVO loginDetailVO = (LoginDetailVO)session.getAttribute("app_user_login");
        if(releaseType==1){
            model.addObject("releaseTypeName","市场环境");
            model.addObject("releaseType","1");
        }else if(releaseType==2){
            model.addObject("releaseTypeName","突发事件");
            model.addObject("releaseType","2");
        }else if(releaseType==3){
            model.addObject("releaseTypeName","副本事件");
            model.addObject("releaseType","3");
        }else if(releaseType==4){
            model.addObject("releaseTypeName","风险预警");
            model.addObject("releaseType","4");
        }
        model.addObject("loginDetailVO",loginDetailVO);
        model.setViewName("teacher/js_cjgl_tj_sy");
        return model;
    }

    //新增突发事件
    @GetMapping("/js_xztfsj")
    public ModelAndView jsXztfsj(ModelAndView model, HttpServletRequest request, @RequestParam("releaseType")Integer releaseType){
        this.modelAndViewObjects(model,request);
        model.setViewName("teacher/js_cjgl_tj_tfsj");
        return model;
    }
    //新增副本事件
    @GetMapping("/js_xzfbsj")
    public ModelAndView jsXzfbsj(ModelAndView model, HttpServletRequest request, @RequestParam("releaseType")Integer releaseType){
        this.modelAndViewObjects(model,request);
        model.setViewName("teacher/js_cjgl_tj_fbsj");
        return model;
    }
    //新增风险预警
    @GetMapping("/js_xzfxyj")
    public ModelAndView jsXzfxyj(ModelAndView model, HttpServletRequest request, @RequestParam("releaseType")Integer releaseType){
        this.modelAndViewObjects(model,request);
        model.setViewName("teacher/js_cjgl_tj_fxyj");
        return model;
    }
    //场景管理
    @GetMapping("/js_cjgl")
    public ModelAndView jsCjgl(ModelAndView model, HttpServletRequest request){
        this.modelAndViewObjects(model,request);
        model.setViewName("teacher/js_cjgl");
        return model;
    }

    //新增消息
    @GetMapping("/js_xzxx")
    public ModelAndView jsXzxx(ModelAndView model, HttpServletRequest request, @RequestParam("messageType")Integer messageType){
        this.modelAndViewObjects(model,request);
        HttpSession session = request.getSession();
        LoginDetailVO loginDetailVO = (LoginDetailVO)session.getAttribute("app_user_login");
        if(messageType==2){
            model.addObject("messageTypeName","消息通知");
            model.addObject("messageType","2");
        }else{
            model.addObject("messageTypeName","竞赛信息");
            model.addObject("messageType","3");
        }
        model.addObject("loginDetailVO",loginDetailVO);
        model.setViewName("teacher/js_xxgl_tjxx");
        return model;
    }

    //新增消息
    @GetMapping("/js_xzxx_sy")
    public ModelAndView jsXzxxSy(ModelAndView model, HttpServletRequest request, @RequestParam("messageType")Integer messageType){
        this.modelAndViewObjects(model,request);
        HttpSession session = request.getSession();
        LoginDetailVO loginDetailVO = (LoginDetailVO)session.getAttribute("app_user_login");
        if(messageType==2){
            model.addObject("messageTypeName","消息通知");
            model.addObject("messageType","2");
        }else{
            model.addObject("messageTypeName","竞赛信息");
            model.addObject("messageType","3");
        }
        model.addObject("loginDetailVO",loginDetailVO);
        model.setViewName("teacher/js_xxgl_tjxx_sy");
        return model;
    }

    //修改消息
    @GetMapping("/js_xgxx")
    public ModelAndView jgXzxx(ModelAndView model, HttpServletRequest request,@RequestParam("id")Long id, @RequestParam("messageType")Integer messageType){
        this.modelAndViewObjects(model,request);
//        TMessageEntity tMessageEntity = tMessageDao.selectById(id);
        HttpSession session = request.getSession();
        LoginDetailVO loginDetailVO = (LoginDetailVO)session.getAttribute("app_user_login");
        if(messageType==2){
            model.addObject("messageTypeName","消息通知");
            model.addObject("messageType","2");
        }else{
            model.addObject("messageTypeName","竞赛信息");
            model.addObject("messageType","3");
        }
        model.addObject("loginDetailVO",loginDetailVO);
//        model.addObject("tMessageEntity",tMessageEntity);
        model.addObject("id",id);
        model.addObject("messageType",messageType);
        model.setViewName("teacher/js_xxgl_tjxx");
        return model;
    }

    //消息管理
    @GetMapping("/js_xxgl")
    public ModelAndView jsXxgl(ModelAndView model, HttpServletRequest request){
        this.modelAndViewObjects(model,request);
        model.setViewName("teacher/js_xxgl");
        return model;
    }

    @GetMapping("/params_parser")
    public ModelAndView paramParser(ModelAndView model, HttpServletRequest request){
        this.modelAndViewObjects(model,request);
        model.setViewName("teacher/params_parser");
        return model;
    }

    //决策数据
    @GetMapping("/js_jsgl")
    public ModelAndView jsJsgl(ModelAndView model, HttpServletRequest request){
        this.modelAndViewObjects(model,request);
        model.setViewName("teacher/js_jsgl");
        return model;
    }

    //竞争数据
    @GetMapping("/js_jsgl2")
    public ModelAndView jsJsgl2(ModelAndView model, HttpServletRequest request){
        this.modelAndViewObjects(model,request);
        model.setViewName("teacher/js_jsgl2");
        return model;
    }
//    @GetMapping("/js_upload")
//    public ModelAndView jsUpload(ModelAndView model, HttpServletRequest request){
//        this.modelAndViewObjects(model,request);
//        model.setViewName("teacher/js_zsgl_tjzs.html");
//        return model;
//    }
//    @GetMapping("/js_zsgl")
//    public ModelAndView jsZsgl(ModelAndView model, HttpServletRequest request){
//        this.modelAndViewObjects(model,request);
//        model.setViewName("teacher/js_zsgl");
//        return model;
//    }
//    @GetMapping("/js_xxgl")
//    public ModelAndView js_Xxgl(ModelAndView model, HttpServletRequest request){
//        this.modelAndViewObjects(model,request);
//        model.setViewName("teacher/js_xxgl");
//        return model;
//    }

    @GetMapping("/error")
    public ModelAndView errorView(ModelAndView model,HttpServletRequest request){
        model.setViewName("error");
        return model;
    }
    private void modelAndViewObjects(ModelAndView model,HttpServletRequest request){
        model.addObject("title", systemConst.getTitle());
        model.addObject("copyright", systemConst.getCopyright());
        model.addObject("icpsecurity", systemConst.getIcpsecurity());
        model.addObject("plcecurity", systemConst.getPlcecurity());
        model.addObject("hotmail", systemConst.getHotmail());
        model.addObject("hotphone", systemConst.getHotphone());
    }
}
