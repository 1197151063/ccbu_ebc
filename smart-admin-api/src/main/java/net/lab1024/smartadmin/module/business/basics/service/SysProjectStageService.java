package net.lab1024.smartadmin.module.business.basics.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.basics.dao.SysProjectStageDao;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysBeginStageDTO;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysProjectStageAddDTO;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysProjectStageQueryDTO;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysProjectStageUpdateDTO;
import net.lab1024.smartadmin.module.business.basics.domain.entity.SysProjectStageEntity;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysProjectStageExcelVO;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysProjectStageVO;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import net.lab1024.smartadmin.util.SmartPageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [ 项目阶段关联表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-22 16:13:42
 * @since JDK1.8
 */
@Service
public class SysProjectStageService {

    @Autowired
    private SysProjectStageDao sysProjectStageDao;

    /**
     * 根据id查询
     */
    public SysProjectStageEntity getById(Long id){
        return  sysProjectStageDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-11-22 16:13:42
     */
    public ResponseDTO<PageResultDTO<SysProjectStageVO>> queryByPage(SysProjectStageQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<SysProjectStageVO> voList = sysProjectStageDao.queryByPage(page, queryDTO);
        PageResultDTO<SysProjectStageVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }
    public ResponseDTO<List<SysProjectStageVO>> querySysProjectStage(SysProjectStageVO sysProjectStageVO) {
        List<SysProjectStageVO> voList = sysProjectStageDao.querySysProjectStage(sysProjectStageVO);
        return ResponseDTO.succData(voList);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-11-22 16:13:42
     */
    public ResponseDTO<String> add(SysProjectStageAddDTO addDTO) {
        SysProjectStageEntity entity = SmartBeanUtil.copy(addDTO, SysProjectStageEntity.class);
        sysProjectStageDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-11-22 16:13:42
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(SysProjectStageUpdateDTO updateDTO) {
        SysProjectStageEntity entity = SmartBeanUtil.copy(updateDTO, SysProjectStageEntity.class);
        sysProjectStageDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-11-22 16:13:42
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        sysProjectStageDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-11-22 16:13:42
     */
    public List<SysProjectStageExcelVO> queryAllExportData(SysProjectStageQueryDTO queryDTO) {
        return sysProjectStageDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-11-22 16:13:42
     */
    public List<SysProjectStageExcelVO> queryBatchExportData(List<Long> idList) {
        return sysProjectStageDao.queryBatchExportData(idList);
    }
    /**
     * 开启阶段开关
     * @author SMS
     * @date 2021-11-22 16:13:42
     */
    public ResponseDTO<String> beginStage(SysBeginStageDTO sysBeginStageDTO) {
        sysProjectStageDao.beginStage(sysBeginStageDTO.getCurrentProjectId(),sysBeginStageDTO.getCurrentStageId(),1);
        sysProjectStageDao.beginStage(sysBeginStageDTO.getCurrentProjectId(),sysBeginStageDTO.getCurrentStageId()-1,2);
        return ResponseDTO.succ();
    }
}
