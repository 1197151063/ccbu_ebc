package net.lab1024.smartadmin.module.system.project;

import net.lab1024.smartadmin.common.anno.NoNeedLogin;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.constant.SwaggerTagConst;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysProjectAddDTO;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysProjectUpdateDTO;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysProjectQueryDTO;
import net.lab1024.smartadmin.module.business.basics.domain.entity.SysProjectEntity;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysProjectVO;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysProjectExcelVO;
import net.lab1024.smartadmin.module.business.basics.service.SysProjectService;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.poi.ss.usermodel.Workbook;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@Api(tags = {SwaggerTagConst.StudentSystem.MANAGER_PROJECT})
public class ProjectManagementController extends BaseController {

    @Autowired
    private SysProjectService sysProjectService;

    @ApiOperation(value = "分页查询项目列表",notes = "@author SMS")
    @PostMapping("/project/page/query")
    public ResponseDTO<PageResultDTO<SysProjectVO>> queryByPage(@RequestBody SysProjectQueryDTO queryDTO) {
        return sysProjectService.queryByPage(queryDTO);
    }
    @ApiOperation(value = "分页查询项目列表",notes = "@author SMS")
    @PostMapping("/project/query")
    public ResponseDTO<SysProjectEntity> queryById(@RequestParam Long id) {
        return ResponseDTO.succData(sysProjectService.getById(id));
    }
    @ApiOperation(value = "新增项目",notes = "@author SMS")
    @PostMapping("/project/add")
    public ResponseDTO<String> add(@RequestBody SysProjectAddDTO addTO){
        return sysProjectService.add(addTO);
    }

    @ApiOperation(value="修改项目",notes = "@author SMS")
    @PostMapping("/project/update")
    public ResponseDTO<String> update(@RequestBody SysProjectAddDTO updateDTO){
        return sysProjectService.updateProjectByProId(updateDTO);
    }

    @ApiOperation(value="批量删除项目",notes = "@author SMS")
    @PostMapping("/project/remove")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return sysProjectService.deleteByIds(idList);
    }

    @ApiOperation(value="单个删除项目",notes = "@author SMS")
    @GetMapping("/project/delete/{proId}")
    public ResponseDTO<String> deleteByProID(@PathVariable Long proId) {
        return sysProjectService.deleteById(proId);
    }

    @ApiOperation(value="查询所有项目",notes = "@author SMS")
    @GetMapping("/service/queryAllProject")
    public ResponseDTO<List<SysProjectVO>> queryAllProject() {
        return sysProjectService.queryAllProject();
    }
}
