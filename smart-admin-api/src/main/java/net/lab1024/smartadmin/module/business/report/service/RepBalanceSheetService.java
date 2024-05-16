package net.lab1024.smartadmin.module.business.report.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.report.dao.RepBalanceSheetDao;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepBalanceSheetAddDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepBalanceSheetUpdateDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepBalanceSheetQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.entity.RepBalanceSheetEntity;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepBalanceSheetVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepBalanceSheetExcelVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [ 资产负债表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-21 14:33:59
 * @since JDK1.8
 */
@Service
public class RepBalanceSheetService {

    @Autowired
    private RepBalanceSheetDao repBalanceSheetDao;

    /**
     * 根据id查询
     */
    public RepBalanceSheetEntity getById(Long id) {
        return repBalanceSheetDao.selectById(id);
    }

    /**
     * 分页查询
     *
     * @author SMS
     * @date 2021-11-21 14:33:59
     */
    public ResponseDTO<PageResultDTO<RepBalanceSheetVO>> queryByPage(RepBalanceSheetQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<RepBalanceSheetVO> voList = repBalanceSheetDao.queryByPage(page, queryDTO);
        PageResultDTO<RepBalanceSheetVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     *
     * @author SMS
     * @date 2021-11-21 14:33:59
     */
    public ResponseDTO<String> add(RepBalanceSheetAddDTO addDTO) {
        RepBalanceSheetEntity entity = SmartBeanUtil.copy(addDTO, RepBalanceSheetEntity.class);
        repBalanceSheetDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     *
     * @author SMS
     * @date 2021-11-21 14:33:59
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(RepBalanceSheetUpdateDTO updateDTO) {
        RepBalanceSheetEntity entity = SmartBeanUtil.copy(updateDTO, RepBalanceSheetEntity.class);
        repBalanceSheetDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     *
     * @author SMS
     * @date 2021-11-21 14:33:59
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        repBalanceSheetDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     *
     * @author SMS
     * @date 2021-11-21 14:33:59
     */
    public List<RepBalanceSheetExcelVO> queryAllExportData(RepBalanceSheetQueryDTO queryDTO) {
        return repBalanceSheetDao.queryAllExportData(queryDTO);
    }

    /**
     * 批量查询导出对象
     *
     * @author SMS
     * @date 2021-11-21 14:33:59
     */
    public List<RepBalanceSheetExcelVO> queryBatchExportData(List<Long> idList) {
        return repBalanceSheetDao.queryBatchExportData(idList);
    }

    /**
     * 批量查询导出对象
     *
     * @author SMS
     * @date 2021-11-21 14:33:59
     */
    public List<RepBalanceSheetExcelVO> queryExportData(Long proId,Long stageId) {
        return repBalanceSheetDao.queryExcelList(proId,stageId);
    }

    /**
     * 查询查询资产负债结果表
     *
     * @author wz
     * @date 2021-11-21 14:33:59
     */
    public ResponseDTO<List<Map<Long,RepBalanceSheetVO>>> selectBalanceResult(Long currentTeamId, Long[] currentStageIds) {

        List<Map<Long,RepBalanceSheetVO>> repBalanceSheetVOList = new ArrayList<Map<Long, RepBalanceSheetVO>>();

        Map<Long,RepBalanceSheetVO> map = new HashMap<>();

        RepBalanceSheetVO repBalanceSheetVO = null;

        //Map<Integer,RepBalanceSheetVO> map = new HashMap<>();
        //如果阶段等于0或-1 查询参数表
        for (int i = 0; i < currentStageIds.length; i++) {
            Long currentStageId = currentStageIds[i];
            if (currentStageId <2) {
                repBalanceSheetVO = repBalanceSheetDao.selectBalanceResultPar(currentStageId);
            }else{
                repBalanceSheetVO = repBalanceSheetDao.selectBalanceResultRep(currentTeamId, currentStageId);
            }
            map.put(currentStageId,repBalanceSheetVO);
        }
        repBalanceSheetVOList.add(map);
        return ResponseDTO.succData(repBalanceSheetVOList);
    }

    /*
    查询所有银行资产负债,未完成
     */
    public ResponseDTO<List<Map<Long, RepBalanceSheetVO>>> selectBalanceAllResult(Long currentProjectId, Long currentStageIds) {

        List<Map<Long, RepBalanceSheetVO>> repBalanceSheetVOList = new ArrayList<Map<Long, RepBalanceSheetVO>>();
        Map<Long, RepBalanceSheetVO> map = new HashMap<>();

        List<RepBalanceSheetVO> repBalanceSheetVOS = repBalanceSheetDao.selectBalanceAllResult(currentProjectId, currentStageIds);

        for (RepBalanceSheetVO repBalanceSheetVO:repBalanceSheetVOS) {
//            String name =repBalanceSheetDao.se(id);
//            repBalanceSheetVO.s
//            map.put(repBalanceSheetVO.getCurrentTeamId(), repBalanceSheetVO);
        }
        repBalanceSheetVOList.add(map);
        return ResponseDTO.succData(repBalanceSheetVOList);
    }

    /**
     * 查询某项目中某团队某阶段的资产负债数据
     * @param currentProjectId
     * @param currentTeamId
     * @param currentStageId
     * @return
     */
    public ResponseDTO<RepBalanceSheetVO> queryBalanceByPidAndTidAndSid(Long currentProjectId,Long currentTeamId,Long currentStageId){
        RepBalanceSheetVO repBalanceSheetVO = new RepBalanceSheetVO();
        if (currentStageId <2) {
            repBalanceSheetVO = repBalanceSheetDao.selectBalanceResultPar(currentStageId);
        }else{
            repBalanceSheetVO = repBalanceSheetDao.query(currentProjectId, currentTeamId, currentStageId);
        }
        return ResponseDTO.succData(repBalanceSheetVO);
    }
}
