package net.lab1024.smartadmin.module.business.report.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.report.dao.RepLiquidityTotalityDao;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepLiquidityTotalityAddDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepLiquidityTotalityUpdateDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepLiquidityTotalityQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.entity.RepLiquidityTotalityEntity;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepLiquidityTotalityVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepLiquidityTotalityExcelVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * [ 流动性报表-总体 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-23 18:34:36
 * @since JDK1.8
 */
@Service
public class RepLiquidityTotalityService {

    @Autowired
    private RepLiquidityTotalityDao repLiquidityTotalityDao;

    /**
     * 根据id查询
     */
    public RepLiquidityTotalityEntity getById(Long id){
        return  repLiquidityTotalityDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-11-23 18:34:36
     */
    public ResponseDTO<PageResultDTO<RepLiquidityTotalityVO>> queryByPage(RepLiquidityTotalityQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<RepLiquidityTotalityVO> voList = repLiquidityTotalityDao.queryByPage(page, queryDTO);
        PageResultDTO<RepLiquidityTotalityVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-11-23 18:34:36
     */
    public ResponseDTO<String> add(RepLiquidityTotalityAddDTO addDTO) {
        RepLiquidityTotalityEntity entity = SmartBeanUtil.copy(addDTO, RepLiquidityTotalityEntity.class);
        repLiquidityTotalityDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-11-23 18:34:36
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(RepLiquidityTotalityUpdateDTO updateDTO) {
        RepLiquidityTotalityEntity entity = SmartBeanUtil.copy(updateDTO, RepLiquidityTotalityEntity.class);
        repLiquidityTotalityDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-11-23 18:34:36
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        repLiquidityTotalityDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-11-23 18:34:36
     */
    public List<RepLiquidityTotalityExcelVO> queryAllExportData(RepLiquidityTotalityQueryDTO queryDTO) {
        return repLiquidityTotalityDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-11-23 18:34:36
     */
    public List<RepLiquidityTotalityExcelVO> queryBatchExportData(List<Long> idList) {
        return repLiquidityTotalityDao.queryBatchExportData(idList);
    }

    /**
     * 将流动性报表-总体返回到页面
     *
     * @param currentTeamId
     * @return
     */
    public ResponseDTO<RepLiquidityTotalityVO> selectLiquidityTotality(Long currentTeamId,Long currentStageId){

        RepLiquidityTotalityVO repLiquidityTotalityVO = null;
            if (currentStageId < 2){
                repLiquidityTotalityVO = repLiquidityTotalityDao.selectLiquidityTotalityStageIdPar(1L);

            }else{
                repLiquidityTotalityVO = repLiquidityTotalityDao.selectTotalityTotalityStageIdRep(currentTeamId,currentStageId);
            }
        return ResponseDTO.succData(repLiquidityTotalityVO);
    }

    public List<RepLiquidityTotalityExcelVO> queryExportData(Long proId, Long stageId) {
        return repLiquidityTotalityDao.queryExportData(proId,stageId);
    }
}
