package net.lab1024.smartadmin.module.business;

import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.system.employee.EmployeeDao;
import net.lab1024.smartadmin.module.system.employee.domain.dto.EmployeeAddDTO;
import net.lab1024.smartadmin.module.system.employee.domain.dto.EmployeeDTO;
import net.lab1024.smartadmin.module.system.employee.domain.vo.EmployeeVO;
import net.lab1024.smartadmin.module.system.login.domain.RequestTokenBO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseUtilService {
    @Autowired
    private  EmployeeDao employeeDao;

    /**
     * 根据员工id查询员工信息
     * @param employeeId
     * @return
     */
    public ResponseDTO<EmployeeDTO> queryEmployee(Long employeeId) {
        return ResponseDTO.succData(employeeDao.getEmployeeById(employeeId));
    }


}
