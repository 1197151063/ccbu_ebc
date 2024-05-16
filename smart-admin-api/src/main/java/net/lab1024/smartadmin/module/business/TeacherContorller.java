package net.lab1024.smartadmin.module.business;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lab1024.smartadmin.common.anno.NoNeedLogin;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.constant.SystemConst;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysProjectVO;
import net.lab1024.smartadmin.module.business.basics.service.SysProjectService;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecLoanDepositVO;
import net.lab1024.smartadmin.module.system.employee.constant.EmployeeResponseCodeConst;
import net.lab1024.smartadmin.module.system.employee.domain.dto.EmployeeLoginFormDTO;
import net.lab1024.smartadmin.module.system.login.LoginService;
import net.lab1024.smartadmin.module.system.login.domain.LoginDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by ${} on ${}.
 * 所有学员、教师端请求路径统一以service开头
 */
@Controller
@RequestMapping("/service")
public class TeacherContorller {
    @Autowired
    private SystemConst systemConst;
    @Autowired
    private SysProjectService sysProjectService;
    /**
     * 教师消息管理
     * @param model
     * @return
     */
    @GetMapping("/message")
    public ModelAndView messageManage(ModelAndView model){
        model.addObject("title", systemConst.getTitle());
        model.addObject("copyright", systemConst.getCopyright());
        model.addObject("icpsecurity", systemConst.getIcpsecurity());
        model.addObject("plcecurity", systemConst.getPlcecurity());
        model.setViewName("teacher/js_xxgl");
        return model;
    }

    /**
     * 教师知识管理
     * @param model
     * @return
     */
    @GetMapping("/knowledge")
    public ModelAndView knowledgeKnowledge(ModelAndView model){
        model.addObject("title", systemConst.getTitle());
        model.addObject("copyright", systemConst.getCopyright());
        model.addObject("icpsecurity", systemConst.getIcpsecurity());
        model.addObject("plcecurity", systemConst.getPlcecurity());
        model.setViewName("teacher/js_zsgl");
        return model;
    }
    /**
     * 教师知识管理
     * @param model
     * @return
     */
    @GetMapping("/knowledge_upload")
    public ModelAndView knowledgeKnowledgeUpload(ModelAndView model){
        model.addObject("title", systemConst.getTitle());
        model.addObject("copyright", systemConst.getCopyright());
        model.addObject("icpsecurity", systemConst.getIcpsecurity());
        model.addObject("plcecurity", systemConst.getPlcecurity());
        model.setViewName("teacher/js_zsgl_tjzs");
        return model;
    }


    /**
     * 教师团队管理
     * @param model
     * @return
     */
    @GetMapping("/team")
    public ModelAndView teamTeam(ModelAndView model){
        model.addObject("title", systemConst.getTitle());
        model.addObject("copyright", systemConst.getCopyright());
        model.addObject("icpsecurity", systemConst.getIcpsecurity());
        model.addObject("plcecurity", systemConst.getPlcecurity());
        model.setViewName("teacher/js_tdgl");
        return model;
    }


    /**
     * 教师场景管理
     * @param model
     * @return
     */
    @GetMapping("/scene")
    public ModelAndView sceneScene(ModelAndView model){
        model.addObject("title", systemConst.getTitle());
        model.addObject("copyright", systemConst.getCopyright());
        model.addObject("icpsecurity", systemConst.getIcpsecurity());
        model.addObject("plcecurity", systemConst.getPlcecurity());
        model.setViewName("teacher/js_cjgl");
        return model;
    }


    /**
     * 教师竞赛管理
     * @param model
     * @return
     */
    @GetMapping("/competition")
    public ModelAndView competitionCompetition(ModelAndView model){
        model.addObject("title", systemConst.getTitle());
        model.addObject("copyright", systemConst.getCopyright());
        model.addObject("icpsecurity", systemConst.getIcpsecurity());
        model.addObject("plcecurity", systemConst.getPlcecurity());
        model.setViewName("teacher/js_jsgl");
        return model;
    }


    /**
     * 教师系统管理
     * @param model
     * @return
     */
    @GetMapping("/system")
    public ModelAndView systemManage(ModelAndView model,HttpServletRequest request){
        model.addObject("title", systemConst.getTitle());
        model.addObject("copyright", systemConst.getCopyright());
        model.addObject("icpsecurity", systemConst.getIcpsecurity());
        model.addObject("plcecurity", systemConst.getPlcecurity());
        ResponseDTO<List<SysProjectVO>> list = sysProjectService.queryAllProject();
//        model.ad
        if(list.getData().size()>0){
            model.addObject("default",list.getData().get(0));
        }
        model.addObject("list",list);
        model.setViewName("teacher/js_xtgl");
        return model;
    }

    /**
     * 查询所有项目
     * @return
     */
//    @RequestMapping("/queryAllProject")
//    public ResponseDTO<List<SysProjectVO>> queryAllProject(){
//        ResponseDTO<List<SysProjectVO>> list = sysProjectService.queryAllProject();
//        return list;
//    }

}
