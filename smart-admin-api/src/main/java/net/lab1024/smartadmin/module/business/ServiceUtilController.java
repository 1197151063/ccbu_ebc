package net.lab1024.smartadmin.module.business;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.system.department.DepartmentService;
import net.lab1024.smartadmin.module.system.department.domain.dto.DepartmentUpdateDTO;
import net.lab1024.smartadmin.module.system.department.domain.dto.DepartmentVO;
import net.lab1024.smartadmin.module.system.employee.EmployeeService;
import net.lab1024.smartadmin.module.system.employee.domain.dto.EmployeeDTO;
import net.lab1024.smartadmin.module.system.employee.domain.dto.EmployeeUpdateDTO;
import net.lab1024.smartadmin.module.system.login.domain.LoginDetailVO;
import net.lab1024.smartadmin.module.system.role.basic.RoleService;
import net.lab1024.smartadmin.module.system.role.basic.domain.dto.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * 学员、教师业务工具类
 */
@RestController
@Api(tags = {"学员、教师相关业务公共类"})
@RequestMapping("/service")
public class ServiceUtilController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private BaseUtilService baseUtilService;
    @Autowired
    private RoleService roleService;
    @Value("${file-upload-service.path}")
    private String filePath1 = "";
    @ApiOperation(value = "学员首页查询团队成员", notes = "学员首页查询团队成员")
    @GetMapping("/query/listEmployeeByDepartmentName")
    public ResponseDTO<List<DepartmentVO>> listDepartmentEmployee(@RequestParam String departmentName) {
        return departmentService.listAllDepartmentEmployee(departmentName);
    }
    @ApiOperation(value = "更新员工信息", notes = "@author yandanyang")
    @PostMapping("/user/update")
    public ResponseDTO<String> updateEmployee(@RequestBody EmployeeUpdateDTO employeeUpdateDto) {
        return employeeService.updateEmployee(employeeUpdateDto);
    }
    @ApiOperation(value = "查询员工信息", notes = "@author ypk")
    @GetMapping("/user/queryById")
    public ResponseDTO<EmployeeDTO> updateEmployee(@RequestParam Long id) {
        return baseUtilService.queryEmployee(id);
    }
    @PostMapping("/upload")
    public ResponseDTO<String> upload(@RequestParam MultipartFile file, HttpServletRequest request) {
        String result = "";
        try {
            //获取文件在服务器的储存位置
            String basePath = ResourceUtils.getURL("classpath:").getPath() + "static/upload/";
            File filePath = new File(filePath1);
            if (!filePath.exists() && !filePath.isDirectory()) {
                filePath.mkdir();
            }
            //获取原始文件名称(包含格式)
            String originalFileName = file.getOriginalFilename();
            //获取文件类型，以最后一个`.`为标识
            String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
            //获取文件名称（不包含格式）
            String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));
            String fileName = UUID.randomUUID()  + "." + type;
            //在指定路径下创建一个文件
            File targetFile = new File(filePath1, fileName);
            //将文件保存到服务器指定位置
            file.transferTo(targetFile);
            //将文件在服务器的存储路径返回
            result = fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseDTO.succData(result);
    }
    @ApiOperation(value = "获取部门信息", notes = "获取部门")
    @GetMapping("/department/query/{deptId}")
    public ResponseDTO<DepartmentVO> getDepartment(@PathVariable Long deptId) {
        return departmentService.getDepartmentById(deptId);
    }
    //更新团队logo、名称、口号
    @ApiOperation(value = "更新部门信息", notes = "更新部门信息")
    @PostMapping("/department/update")
    public ResponseDTO<String> updateDepartment(@RequestBody DepartmentVO departmentVO,HttpServletRequest request) {
        //更新session中团队信息
        HttpSession session = request.getSession();
        LoginDetailVO loginDetailVO = (LoginDetailVO)session.getAttribute("app_user_login");
        loginDetailVO.setDepartmentName(departmentVO.getName());
        session.setAttribute("app_user_login",loginDetailVO);
        return departmentService.updateDepartmentInfo(departmentVO);
    }
    //更新用户角色（remark存储）

    //获取所有的角色
    @ApiOperation(value = "获取所有角色", notes = "获取所有角色数据")
    @GetMapping("/role/getAll")
    public ResponseDTO<List<RoleVO>> getAllRole() {
        return roleService.getAllRole();
    }
}
