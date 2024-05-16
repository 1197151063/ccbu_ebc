package net.lab1024.smartadmin.module.business.report.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.report.dao.RepPersonnelLogisticsDataAutomationDao;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepPersonnelLogisticsDataAutomationAddDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepPersonnelLogisticsDataAutomationUpdateDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepPersonnelLogisticsDataAutomationQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.entity.RepPersonnelLogisticsDataAutomationEntity;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepPersonnelLogisticsDataAutomationVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepPersonnelLogisticsDataAutomationExcelVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * [ 人事后勤数据表(自动化投资) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 11:24:21
 * @since JDK1.8
 */
@Service
public class RepPersonnelLogisticsDataAutomationService {

    @Autowired
    private RepPersonnelLogisticsDataAutomationDao repPersonnelLogisticsDataAutomationDao;

    /**
     * 根据id查询
     */
    public RepPersonnelLogisticsDataAutomationEntity getById(Long id){
        return  repPersonnelLogisticsDataAutomationDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-11-25 11:24:21
     */
    public ResponseDTO<PageResultDTO<RepPersonnelLogisticsDataAutomationVO>> queryByPage(RepPersonnelLogisticsDataAutomationQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<RepPersonnelLogisticsDataAutomationVO> voList = repPersonnelLogisticsDataAutomationDao.queryByPage(page, queryDTO);
        PageResultDTO<RepPersonnelLogisticsDataAutomationVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-11-25 11:24:21
     */
    public ResponseDTO<String> add(RepPersonnelLogisticsDataAutomationAddDTO addDTO) {
        RepPersonnelLogisticsDataAutomationEntity entity = SmartBeanUtil.copy(addDTO, RepPersonnelLogisticsDataAutomationEntity.class);
        repPersonnelLogisticsDataAutomationDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-11-25 11:24:21
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(RepPersonnelLogisticsDataAutomationUpdateDTO updateDTO) {
        RepPersonnelLogisticsDataAutomationEntity entity = SmartBeanUtil.copy(updateDTO, RepPersonnelLogisticsDataAutomationEntity.class);
        repPersonnelLogisticsDataAutomationDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-11-25 11:24:21
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        repPersonnelLogisticsDataAutomationDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-11-25 11:24:21
     */
    public List<RepPersonnelLogisticsDataAutomationExcelVO> queryAllExportData(RepPersonnelLogisticsDataAutomationQueryDTO queryDTO) {
        return repPersonnelLogisticsDataAutomationDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-11-25 11:24:21
     */
    public List<RepPersonnelLogisticsDataAutomationExcelVO> queryBatchExportData(List<Long> idList) {
        return repPersonnelLogisticsDataAutomationDao.queryBatchExportData(idList);
    }

    public ResponseDTO<RepPersonnelLogisticsDataAutomationVO> selectPerLogDataAutomation(Long currentTeamId, Long currentStageId) {
        RepPersonnelLogisticsDataAutomationVO repPerLogDataAutomationVO = null;
            if (currentStageId < 3){
                repPerLogDataAutomationVO = repPersonnelLogisticsDataAutomationDao.selectPerLogDataAutomationPar(1L);
                System.out.println(repPerLogDataAutomationVO);
            }else{
                repPerLogDataAutomationVO = repPersonnelLogisticsDataAutomationDao.selectPerLogDataAutomationRep(currentTeamId,currentStageId);
                System.out.println(repPerLogDataAutomationVO);
        }
        return ResponseDTO.succData(repPerLogDataAutomationVO);
    }

    public List<RepPersonnelLogisticsDataAutomationExcelVO> queryExportData(Long proId, Long stageId) {
        return repPersonnelLogisticsDataAutomationDao.queryExportData(proId,stageId);
    }
}
