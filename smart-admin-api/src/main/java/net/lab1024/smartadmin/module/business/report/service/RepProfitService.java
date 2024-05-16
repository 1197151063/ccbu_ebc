package net.lab1024.smartadmin.module.business.report.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.report.dao.RepProfitDao;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepProfitAddDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepProfitUpdateDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepProfitQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.entity.RepProfitEntity;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepProfitVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepProfitExcelVO;
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
 * [ 利润表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-23 14:42:17
 * @since JDK1.8
 */
@Service
public class RepProfitService {

    /**
     * 查询利润结果表
     */
    @Autowired
    private RepProfitDao repProfitDao;


    /**
     * 根据id查询
     */
    public RepProfitEntity getById(Long id) {
        return repProfitDao.selectById(id);
    }

    /**
     * 分页查询
     *
     * @author SMS
     * @date 2021-11-23 14:42:17
     */
    public ResponseDTO<PageResultDTO<RepProfitVO>> queryByPage(RepProfitQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<RepProfitVO> voList = repProfitDao.queryByPage(page, queryDTO);
        PageResultDTO<RepProfitVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     *
     * @author SMS
     * @date 2021-11-23 14:42:17
     */
    public ResponseDTO<String> add(RepProfitAddDTO addDTO) {
        RepProfitEntity entity = SmartBeanUtil.copy(addDTO, RepProfitEntity.class);
        repProfitDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     *
     * @author SMS
     * @date 2021-11-23 14:42:17
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(RepProfitUpdateDTO updateDTO) {
        RepProfitEntity entity = SmartBeanUtil.copy(updateDTO, RepProfitEntity.class);
        repProfitDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     *
     * @author SMS
     * @date 2021-11-23 14:42:17
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        repProfitDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     *
     * @author SMS
     * @date 2021-11-23 14:42:17
     */
    public List<RepProfitExcelVO> queryAllExportData(RepProfitQueryDTO queryDTO) {
        return repProfitDao.queryAllExportData(queryDTO);
    }

    /**
     * 批量查询导出对象
     *
     * @author SMS
     * @date 2021-11-23 14:42:17
     */
    public List<RepProfitExcelVO> queryBatchExportData(List<Long> idList) {
        return repProfitDao.queryBatchExportData(idList);
    }


    /**
     * 查询利润结果表
     *
     * @param currentTeamId
     * @param currentStageIds
     * @return
     * @author wz
     */
    public ResponseDTO<List<Map<Long, RepProfitVO>>> selectProfit(Long currentTeamId, Long[] currentStageIds) {
        List<Map<Long, RepProfitVO>> repProfitVOList = new ArrayList<>();
        Map<Long, RepProfitVO> map = new HashMap<>();
        RepProfitVO repProfitVO = null;
        //如果阶段等于0或-1 查询参数表
        for (int i = 0; i < currentStageIds.length; i++) {
            Long currentStageId = currentStageIds[i];
            if (currentStageId < 2) {
                repProfitVO = repProfitDao.selectProfitStageIdPar(currentStageId);
                map.put(currentStageId, repProfitVO);
            }else{
                repProfitVO = repProfitDao.selectProfitStageIdRep(currentTeamId, currentStageId);
                map.put(currentStageId, repProfitVO);
            }
        }
        repProfitVOList.add(map);
        return ResponseDTO.succData(repProfitVOList);
    }

    /**
     * 查询某团队某阶段的利润输数据
     * @param currentTeamId
     * @param currentStageId
     * @return
     */
    public ResponseDTO<RepProfitVO> queryProfitByTidAndSid(Long currentTeamId,Long currentStageId){
        RepProfitVO repProfitVO = new RepProfitVO();
        if (currentStageId < 2) {
            repProfitVO = repProfitDao.selectProfitStageIdPar(currentStageId);
        }else{
            repProfitVO = repProfitDao.selectProfitStageIdRep(currentTeamId, currentStageId);
        }
        return ResponseDTO.succData(repProfitVO);
    }

    public List<RepProfitExcelVO> queryExportData(Long currentProjectId,Long currentStageId){
        return repProfitDao.queryExportData(currentProjectId,currentStageId);
    }
}
