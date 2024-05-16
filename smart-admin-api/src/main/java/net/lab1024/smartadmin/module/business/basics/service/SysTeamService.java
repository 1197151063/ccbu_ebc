package net.lab1024.smartadmin.module.business.basics.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.basics.dao.SysProjectTeamStageDao;
import net.lab1024.smartadmin.module.business.basics.dao.SysStageBusinessDao;
import net.lab1024.smartadmin.module.business.basics.dao.SysStageDao;
import net.lab1024.smartadmin.module.business.basics.dao.SysTeamDao;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysTeamAddDTO;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysTeamQueryDTO;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysTeamUpdateDTO;
import net.lab1024.smartadmin.module.business.basics.domain.entity.SysDepartmentEmployeeEntity;
import net.lab1024.smartadmin.module.business.basics.domain.entity.SysTeamEntity;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysStageBusinessVO;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysTeamExcelVO;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysTeamVO;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import net.lab1024.smartadmin.util.SmartPageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [ 团队表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-21 10:13:24
 * @since JDK1.8
 */
@Service
public class SysTeamService {

    @Autowired
    private SysTeamDao sysTeamDao;
    @Autowired
    private SysProjectTeamStageDao sysProjectTeamStageDao;
    @Autowired
    private SysStageDao sysStageDao;
    @Autowired
    private SysStageBusinessDao sysStageBusinessDao;
    /**
     * 根据id查询
     */
    public SysTeamEntity getById(Long id) {
        return sysTeamDao.selectById(id);
    }

    /**
     * 分页查询
     *
     * @author SMS
     * @date 2021-11-21 10:13:24
     */
    public ResponseDTO<PageResultDTO<SysTeamVO>> queryByPage(SysTeamQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<SysTeamVO> voList = sysTeamDao.queryByPage(page, queryDTO);
        PageResultDTO<SysTeamVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     *
     * @author SMS
     * @date 2021-11-21 10:13:24
     */
    public ResponseDTO<String> add(SysTeamAddDTO addDTO) {
        SysTeamEntity entity = SmartBeanUtil.copy(addDTO, SysTeamEntity.class);
        sysTeamDao.insertTeam(entity);
        List<SysStageBusinessVO> sysStageBusinessVOS = sysStageBusinessDao.queryAllData();
        for (SysStageBusinessVO s:sysStageBusinessVOS) {
            String[] split = s.getBusinessName().split(",");
            for (int i = 0; i < split.length; i++) {
                sysProjectTeamStageDao.insertProjectTeamStage(addDTO.getProId(),entity.getTeamId(),s.getStageId(), split[i],0);
            }
        }
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     *
     * @author SMS
     * @date 2021-11-21 10:13:24
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(SysTeamUpdateDTO updateDTO) {
        SysTeamEntity entity = SmartBeanUtil.copy(updateDTO, SysTeamEntity.class);
        sysTeamDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     *
     * @author SMS
     * @date 2021-11-21 10:13:24
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        sysTeamDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     *
     * @author SMS
     * @date 2021-11-21 10:13:24
     */
    public List<SysTeamExcelVO> queryAllExportData(SysTeamQueryDTO queryDTO) {
        return sysTeamDao.queryAllExportData(queryDTO);
    }

    /**
     * 批量查询导出对象
     *
     * @author SMS
     * @date 2021-11-21 10:13:24
     */
    public List<SysTeamExcelVO> queryBatchExportData(List<Long> idList) {
        return sysTeamDao.queryBatchExportData(idList);
    }

    public ResponseDTO<List<SysDepartmentEmployeeEntity>> selectDepartmentEmployee(Long id) {
        List<SysDepartmentEmployeeEntity> sysDepartmentEmployeeEntities = sysTeamDao.selectDepartmentEmployee(id);
        return ResponseDTO.succData(sysDepartmentEmployeeEntities);
    }
}
