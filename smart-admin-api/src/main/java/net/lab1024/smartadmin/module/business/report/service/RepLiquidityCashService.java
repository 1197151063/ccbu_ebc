package net.lab1024.smartadmin.module.business.report.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.report.dao.RepLiquidityCashDao;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepLiquidityCashAddDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepLiquidityCashUpdateDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepLiquidityCashQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.entity.RepLiquidityCashEntity;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepBalanceSheetVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepLiquidityCashVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepLiquidityCashExcelVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepLiquidityTotalityVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * [ 流动性报表-现金 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-23 18:30:53
 * @since JDK1.8
 */
@Service
public class RepLiquidityCashService {

    @Autowired
    private RepLiquidityCashDao repLiquidityCashDao;

    /**
     * 根据id查询
     */
    public RepLiquidityCashEntity getById(Long id) {
        return repLiquidityCashDao.selectById(id);
    }

    /**
     * 分页查询
     *
     * @author SMS
     * @date 2021-11-23 18:30:53
     */
    public ResponseDTO<PageResultDTO<RepLiquidityCashVO>> queryByPage(RepLiquidityCashQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<RepLiquidityCashVO> voList = repLiquidityCashDao.queryByPage(page, queryDTO);
        PageResultDTO<RepLiquidityCashVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     *
     * @author SMS
     * @date 2021-11-23 18:30:53
     */
    public ResponseDTO<String> add(RepLiquidityCashAddDTO addDTO) {
        RepLiquidityCashEntity entity = SmartBeanUtil.copy(addDTO, RepLiquidityCashEntity.class);
        repLiquidityCashDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     *
     * @author SMS
     * @date 2021-11-23 18:30:53
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(RepLiquidityCashUpdateDTO updateDTO) {
        RepLiquidityCashEntity entity = SmartBeanUtil.copy(updateDTO, RepLiquidityCashEntity.class);
        repLiquidityCashDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     *
     * @author SMS
     * @date 2021-11-23 18:30:53
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        repLiquidityCashDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     *
     * @author SMS
     * @date 2021-11-23 18:30:53
     */
    public List<RepLiquidityCashExcelVO> queryAllExportData(RepLiquidityCashQueryDTO queryDTO) {
        return repLiquidityCashDao.queryAllExportData(queryDTO);
    }

    /**
     * 批量查询导出对象
     *
     * @author SMS
     * @date 2021-11-23 18:30:53
     */
    public List<RepLiquidityCashExcelVO> queryBatchExportData(List<Long> idList) {
        return repLiquidityCashDao.queryBatchExportData(idList);
    }

    /**
     * 将流动性报表-现金返回到页面
     * @param currentTeamId
     * @param currentStageId
     * @return
     */
    public ResponseDTO<RepLiquidityCashVO> selectLiquidityCash(Long currentTeamId, Long currentStageId) {

        RepLiquidityCashVO repLiquidityCashVO = null;
            if (currentStageId < 2 ) {
                repLiquidityCashVO = repLiquidityCashDao.selectLiquidityCashStageIdPar(1L);
            }else{
                repLiquidityCashVO = repLiquidityCashDao.selectLiquidityCashStageIdRep(currentTeamId, currentStageId);
            }
        return ResponseDTO.succData(repLiquidityCashVO);
    }

    public List<RepLiquidityCashExcelVO> queryExportData(Long proId, Long stageId) {
        return repLiquidityCashDao.queryExportData(proId,stageId);
    }
}
