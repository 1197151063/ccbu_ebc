package net.lab1024.smartadmin.module.business.basics.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.basics.dao.SysProjectTeamStageDao;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysBeginStageDTO;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysProjectTeamStageAddDTO;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysProjectTeamStageQueryDTO;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysProjectTeamStageUpdateDTO;
import net.lab1024.smartadmin.module.business.basics.domain.entity.SysProjectTeamStageEntity;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysProjectTeamStageExcelVO;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysProjectTeamStageVO;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import net.lab1024.smartadmin.util.SmartPageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * [ 项目团队阶段业务关联表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-22 11:10:47
 * @since JDK1.8
 */
@Service
public class SysProjectTeamStageService {

    @Autowired
    private SysProjectTeamStageDao sysProjectTeamStageDao;

    /**
     * 根据id查询
     */
    public SysProjectTeamStageEntity getById(Long id){
        return  sysProjectTeamStageDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-11-22 11:10:47
     */
    public ResponseDTO<PageResultDTO<SysProjectTeamStageVO>> queryByPage(SysProjectTeamStageQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<SysProjectTeamStageVO> voList = sysProjectTeamStageDao.queryByPage(page, queryDTO);
        PageResultDTO<SysProjectTeamStageVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-11-22 11:10:47
     */
    public ResponseDTO<String> add(SysProjectTeamStageAddDTO addDTO) {
        SysProjectTeamStageEntity entity = SmartBeanUtil.copy(addDTO, SysProjectTeamStageEntity.class);
        sysProjectTeamStageDao.insert(entity);

        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-11-22 11:10:47
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(SysProjectTeamStageUpdateDTO updateDTO) {
        SysProjectTeamStageEntity entity = SmartBeanUtil.copy(updateDTO, SysProjectTeamStageEntity.class);
        sysProjectTeamStageDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-11-22 11:10:47
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        sysProjectTeamStageDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-11-22 11:10:47
     */
    public List<SysProjectTeamStageExcelVO> queryAllExportData(SysProjectTeamStageQueryDTO queryDTO) {
        return sysProjectTeamStageDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-11-22 11:10:47
     */
    public List<SysProjectTeamStageExcelVO> queryBatchExportData(List<Long> idList) {
        return sysProjectTeamStageDao.queryBatchExportData(idList);
    }

    /**
     * 查询业务提交状态
     * @author SMS
     * @date 2021-11-22 11:10:47
     */
    public ResponseDTO<Integer> queryStatus(Long proId, Long teamId, Long stageId, String businessName) {
        //状态为0:未提交，1:预提交，2:已提交
        Integer status = sysProjectTeamStageDao.queryStatus(proId, teamId, stageId, businessName);
        return ResponseDTO.succData(status);
    }


    /**
     * 根据项目id查阶段和阶段对应的业务
     * @param proId
     * @return
     */
    public ResponseDTO<SysProjectTeamStageVO> selectCurrentStageBusiness(Long proId) {
        SysProjectTeamStageVO sysProjectTeamStageVO = null;
        sysProjectTeamStageVO = sysProjectTeamStageDao.selectCurrentStageBusiness(proId);
        return ResponseDTO.succData(sysProjectTeamStageVO);
    }
    /**
     * 列出项目的所有阶段信息
     * @param proId
     * @return
     */
    public ResponseDTO<List<SysProjectTeamStageVO>> selectStageBusiness(Long proId) {
        List<SysProjectTeamStageVO> list = new ArrayList<>();
        list = sysProjectTeamStageDao.selectStageBusiness(proId);
        return ResponseDTO.succData(list);
    }

    public ResponseDTO<List<SysProjectTeamStageVO>> queryBeforeStage(SysBeginStageDTO sysBeginStageDTO) {
        List<SysProjectTeamStageVO> sysProjectTeamStageVOS = sysProjectTeamStageDao.queryBeforeStage(sysBeginStageDTO);
        return ResponseDTO.succData(sysProjectTeamStageVOS);
    }
}
