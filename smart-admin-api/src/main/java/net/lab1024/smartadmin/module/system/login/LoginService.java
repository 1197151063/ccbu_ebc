package net.lab1024.smartadmin.module.system.login;

import net.lab1024.smartadmin.common.constant.JudgeEnum;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.constant.CommonConst;
import net.lab1024.smartadmin.module.business.basics.dao.SysProjectDao;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysProjectVO;
import net.lab1024.smartadmin.module.system.department.DepartmentDao;
import net.lab1024.smartadmin.module.system.department.domain.entity.DepartmentEntity;
import net.lab1024.smartadmin.module.system.employee.EmployeeDao;
import net.lab1024.smartadmin.module.system.employee.constant.EmployeeResponseCodeConst;
import net.lab1024.smartadmin.module.system.employee.constant.EmployeeStatusEnum;
import net.lab1024.smartadmin.module.system.employee.dao.UserPhotoDao;
import net.lab1024.smartadmin.module.system.employee.domain.dto.EmployeeDTO;
import net.lab1024.smartadmin.module.system.employee.domain.dto.EmployeeLoginFormDTO;
import net.lab1024.smartadmin.module.business.log.LogService;
import net.lab1024.smartadmin.module.business.log.userloginlog.domain.UserLoginLogEntity;
import net.lab1024.smartadmin.module.system.employee.domain.vo.UserPhotoVO;
import net.lab1024.smartadmin.module.system.login.domain.KaptchaVO;
import net.lab1024.smartadmin.module.system.login.domain.LoginDetailVO;
import net.lab1024.smartadmin.module.system.login.domain.LoginPrivilegeDTO;
import net.lab1024.smartadmin.module.system.login.domain.RequestTokenBO;
import net.lab1024.smartadmin.module.system.position.PositionService;
import net.lab1024.smartadmin.module.system.position.domain.dto.PositionRelationResultDTO;
import net.lab1024.smartadmin.module.system.privilege.domain.entity.PrivilegeEntity;
import net.lab1024.smartadmin.module.system.privilege.service.PrivilegeEmployeeService;
import net.lab1024.smartadmin.module.system.role.roleemployee.RoleEmployeeService;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import net.lab1024.smartadmin.util.SmartDigestUtil;
import net.lab1024.smartadmin.util.SmartIPUtil;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * [  ]
 *
 * @author yandanyang
 * @version 1.0
 * @company 1024lab.net
 * @copyright (c) 2018 1024lab.netInc. All rights reserved.
 * @date 2019/3/27 0027 下午 18:10
 * @since JDK1.8
 */
@Slf4j
@Service
public class LoginService {

    private static final String VERIFICATION_CODE_REDIS_PREFIX = "vc_%s";

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private PrivilegeEmployeeService privilegeEmployeeService;

    @Autowired
    private LoginTokenService loginTokenService;

    @Autowired
    private LogService logService;

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @Autowired
    private ValueOperations<String, String> redisValueOperations;

    @Autowired
    private PositionService positionService;
    @Autowired
    private SysProjectDao sysProjectDao;
    @Autowired
    private UserPhotoDao userPhotoDao;
    /**
     * 登陆
     *
     * @param loginForm 登录名 密码
     * @return 登录用户基本信息
     */
    public ResponseDTO<LoginDetailVO> login(@Valid EmployeeLoginFormDTO loginForm, HttpServletRequest request) {
//        String redisVerificationCode = redisValueOperations.get(loginForm.getCodeUuid());
//        //增加删除已使用的验证码方式 频繁登录
//        redisValueOperations.getOperations().delete(loginForm.getCodeUuid());
//        if (StringUtils.isEmpty(redisVerificationCode)) {
//            return ResponseDTO.wrap(EmployeeResponseCodeConst.VERIFICATION_CODE_INVALID);
//        }
//        if (!redisVerificationCode.equalsIgnoreCase(loginForm.getCode())) {
//            return ResponseDTO.wrap(EmployeeResponseCodeConst.VERIFICATION_CODE_INVALID);
//        }
        String loginPwd = SmartDigestUtil.encryptPassword(CommonConst.Password.SALT_FORMAT, loginForm.getLoginPwd());
        //只允许超管登录
//        if(!("sysadmin").equals(loginForm.getLoginName())&&!("dang").equals(loginForm.getLoginName())){
//            return ResponseDTO.wrap(EmployeeResponseCodeConst.LOGIN_FAILED);
//        }
        EmployeeDTO employeeDTO = employeeDao.login(loginForm.getLoginName(), loginPwd);
        if (null == employeeDTO) {
            return ResponseDTO.wrap(EmployeeResponseCodeConst.LOGIN_FAILED);
        }
        if (EmployeeStatusEnum.DISABLED.equalsValue(employeeDTO.getIsDisabled())) {
            return ResponseDTO.wrap(EmployeeResponseCodeConst.IS_DISABLED);
        }
        //jwt token赋值
        String compactJws = loginTokenService.generateToken(employeeDTO);

        LoginDetailVO loginDTO = SmartBeanUtil.copy(employeeDTO, LoginDetailVO.class);

        //获取前端功能权限
        loginDTO.setPrivilegeList(initEmployeePrivilege(employeeDTO.getId()));

        loginDTO.setXAccessToken(compactJws);
        DepartmentEntity departmentEntity = departmentDao.selectById(employeeDTO.getDepartmentId());
        loginDTO.setDepartmentName(departmentEntity.getName());

        //判断是否为超管
//        Boolean isSuperman = privilegeEmployeeService.isSuperman(loginDTO.getId());
        loginDTO.setIsSuperMan(true);

        //获取用户的岗位(只能有一个)
        List<PositionRelationResultDTO> positionRelationResultDTOList = positionService.queryPositionByEmployeeId(loginDTO.getId());
        loginDTO.setUserPosition(positionRelationResultDTOList!=null&&positionRelationResultDTOList.size()>0?positionRelationResultDTOList.get(0).getPositionName():"");
//        //前端登录的时候也放session吧
//        HttpSession session = request.getSession();
//        session.setAttribute("app_user_login",loginDTO);
        //登陆操作日志
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        UserLoginLogEntity logEntity =
                UserLoginLogEntity.builder()
                        .userId(employeeDTO.getId())
                        .userName(employeeDTO.getActualName())
                        .remoteIp(SmartIPUtil.getRemoteIp(request))
                        .remotePort(request.getRemotePort())
                        .remoteBrowser(userAgent.getBrowser().getName())
                        .remoteOs(userAgent.getOperatingSystem().getName())
                        .loginStatus(JudgeEnum.YES.getValue()).build();
        logService.addLog(logEntity);
        return ResponseDTO.succData(loginDTO);
    }

    /**
     * 教师、学员登录
     * @param loginForm
     * @param request
     * @return
     */
    public EmployeeResponseCodeConst userLogin(@Valid EmployeeLoginFormDTO loginForm, HttpServletRequest request){
        String loginPwd = SmartDigestUtil.encryptPassword(CommonConst.Password.SALT_FORMAT, loginForm.getLoginPwd());
        //不允许超管登录
        if(("sysadmin").equals(loginForm.getLoginName())){
            return EmployeeResponseCodeConst.LOGIN_FAILED;
        }
        EmployeeDTO employeeDTO = employeeDao.login(loginForm.getLoginName(), loginPwd);
        if (null == employeeDTO) {
            return EmployeeResponseCodeConst.LOGIN_FAILED;
        }
        if (EmployeeStatusEnum.DISABLED.equalsValue(employeeDTO.getIsDisabled())) {
            return EmployeeResponseCodeConst.LOGIN_FAILED;
        }
        HttpSession session = request.getSession();

        //jwt token赋值
        String compactJws = loginTokenService.generateToken(employeeDTO);

        LoginDetailVO loginDTO = SmartBeanUtil.copy(employeeDTO, LoginDetailVO.class);

        //获取前端功能权限
        loginDTO.setPrivilegeList(initEmployeePrivilege(employeeDTO.getId()));

        loginDTO.setXAccessToken(compactJws);
        DepartmentEntity departmentEntity = departmentDao.selectById(employeeDTO.getDepartmentId());
        loginDTO.setDepartmentName(departmentEntity.getName());

        //判断是否为超管
//        Boolean isSuperman = privilegeEmployeeService.isSuperman(loginDTO.getId());
        loginDTO.setIsSuperMan(false);

        //获取用户的岗位(前台新加用户的时候，只能选一个)
        List<PositionRelationResultDTO> positionRelationResultDTOList = positionService.queryPositionByEmployeeId(loginDTO.getId());
        loginDTO.setUserPosition(positionRelationResultDTOList!=null&&positionRelationResultDTOList.size()>0?positionRelationResultDTOList.get(0).getPositionName():"");
        //获取用户所在的团队的所属项目信息
        Long projectId = departmentEntity.getProjectId();
        SysProjectVO sysProjectVO = sysProjectDao.queryData(projectId);
        //用户登录信息
        session.setAttribute("app_user_login",loginDTO);
        //用户头像信息
        UserPhotoVO userPhotoVO = userPhotoDao.queryById(loginDTO.getId());
        if(userPhotoVO == null || userPhotoVO.getUserPhoto()==null){
            userPhotoVO = new UserPhotoVO();
            userPhotoVO.setUserId(loginDTO.getId());
            userPhotoVO.setUserPhoto("/images/user_photo.png");
        }
        session.setAttribute("user_photo",userPhotoVO);
        //项目信息
        session.setAttribute("system_project",sysProjectVO);

        //登陆操作日志
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        UserLoginLogEntity logEntity =
                UserLoginLogEntity.builder()
                        .userId(employeeDTO.getId())
                        .userName(employeeDTO.getActualName())
                        .remoteIp(SmartIPUtil.getRemoteIp(request))
                        .remotePort(request.getRemotePort())
                        .remoteBrowser(userAgent.getBrowser().getName())
                        .remoteOs(userAgent.getOperatingSystem().getName())
                        .loginStatus(JudgeEnum.YES.getValue()).build();
        logService.addLog(logEntity);
        return null;
    }

    /**
     * 手机端退出登陆，清除token缓存
     *
     * @param requestToken
     * @return 退出登陆是否成功，bool
     */
    public ResponseDTO<Boolean> logoutByToken(RequestTokenBO requestToken) {
        privilegeEmployeeService.removeCache(requestToken.getRequestUserId());
        return ResponseDTO.succ();
    }

    /**
     * 获取验证码
     *
     * @return
     */
    public ResponseDTO<KaptchaVO> verificationCode() {
        KaptchaVO kaptchaVO = new KaptchaVO();
        String uuid = buildVerificationCodeRedisKey(UUID.randomUUID().toString());
        String kaptchaText = defaultKaptcha.createText();

        String base64Code = "";

        BufferedImage image = defaultKaptcha.createImage(kaptchaText);
        ByteArrayOutputStream outputStream = null;
        try {
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", outputStream);
            base64Code = Base64.encodeBase64String(outputStream.toByteArray());
        } catch (Exception e) {
            log.error("verificationCode exception .{}", e);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception e) {
                    log.error("verificationCode outputStream close exception .{}", e);
                }
            }
        }
        kaptchaVO.setUuid(uuid);
        kaptchaVO.setCode("data:image/png;base64," + base64Code);
        redisValueOperations.set(uuid, kaptchaText, 60L, TimeUnit.SECONDS);
        return ResponseDTO.succData(kaptchaVO);
    }

    private String buildVerificationCodeRedisKey(String uuid) {
        return String.format(VERIFICATION_CODE_REDIS_PREFIX, uuid);
    }

    /**
     * 初始化员工权限
     *
     * @param employeeId
     * @return
     */
    public List<LoginPrivilegeDTO> initEmployeePrivilege(Long employeeId) {
        List<PrivilegeEntity> privilegeList = privilegeEmployeeService.getPrivilegesByEmployeeId(employeeId);
        privilegeEmployeeService.updateCachePrivilege(employeeId, privilegeList);
        return SmartBeanUtil.copyList(privilegeList, LoginPrivilegeDTO.class);
    }

    public LoginDetailVO getSession(RequestTokenBO requestUser) {
        LoginDetailVO loginDTO = SmartBeanUtil.copy(requestUser.getEmployeeBO(), LoginDetailVO.class);
        List<PrivilegeEntity> privilegeEntityList = privilegeEmployeeService.getEmployeeAllPrivilege(requestUser.getRequestUserId());
        //======  开启缓存   ======
        if (privilegeEntityList == null) {
            List<LoginPrivilegeDTO> loginPrivilegeDTOS = initEmployeePrivilege(requestUser.getRequestUserId());
            loginDTO.setPrivilegeList(loginPrivilegeDTOS);
        } else {
            loginDTO.setPrivilegeList(SmartBeanUtil.copyList(privilegeEntityList, LoginPrivilegeDTO.class));
        }

        //======  不开启缓存   ======
//        List<LoginPrivilegeDTO> loginPrivilegeDTOS = initEmployeePrivilege(requestUser.getRequestUserId());
//        loginDTO.setPrivilegeList(loginPrivilegeDTOS);

        //判断是否为超管
        Boolean isSuperman = privilegeEmployeeService.isSuperman(loginDTO.getId());
        loginDTO.setIsSuperMan(isSuperman);
        return loginDTO;
    }
}
