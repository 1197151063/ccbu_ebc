package net.lab1024.smartadmin.module.business.report.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.param.dao.ParInitialDataPeopleLogisticsDao;
import net.lab1024.smartadmin.module.business.report.dao.RepPeopleLogisticsDao;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepPeopleLogisticsAddDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepPeopleLogisticsQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepPeopleLogisticsUpdateDTO;
import net.lab1024.smartadmin.module.business.report.domain.entity.RepPeopleLogisticsEntity;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepPeopleLogisticsExcelVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepPeopleLogisticsVO;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import net.lab1024.smartadmin.util.SmartPageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [ 人事后勤信息录入 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-07 17:25:20
 * @since JDK1.8
 */
@Service
public class RepPeopleLogisticsService {

    @Autowired
    private RepPeopleLogisticsDao repPeopleLogisticsDao;
    @Autowired
    private ParInitialDataPeopleLogisticsDao parInitialDataPeopleLogisticsDao;

    /**
     * 根据id查询
     */
    public RepPeopleLogisticsEntity getById(Long id) {
        return repPeopleLogisticsDao.selectById(id);
    }

    /**
     * 分页查询
     *
     * @author SMS
     * @date 2021-12-07 17:25:20
     */
    public ResponseDTO<PageResultDTO<RepPeopleLogisticsVO>> queryByPage(RepPeopleLogisticsQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<RepPeopleLogisticsVO> voList = repPeopleLogisticsDao.queryByPage(page, queryDTO);
        PageResultDTO<RepPeopleLogisticsVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     *
     * @author SMS
     * @date 2021-12-07 17:25:20
     */
    public ResponseDTO<String> add(RepPeopleLogisticsAddDTO addDTO) {
        RepPeopleLogisticsEntity entity = SmartBeanUtil.copy(addDTO, RepPeopleLogisticsEntity.class);
        repPeopleLogisticsDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     *
     * @author SMS
     * @date 2021-12-07 17:25:20
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(RepPeopleLogisticsUpdateDTO updateDTO) {
        RepPeopleLogisticsEntity entity = SmartBeanUtil.copy(updateDTO, RepPeopleLogisticsEntity.class);
        repPeopleLogisticsDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     *
     * @author SMS
     * @date 2021-12-07 17:25:20
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        repPeopleLogisticsDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     *
     * @author SMS
     * @date 2021-12-07 17:25:20
     */
    public List<RepPeopleLogisticsExcelVO> queryAllExportData(RepPeopleLogisticsQueryDTO queryDTO) {
        return repPeopleLogisticsDao.queryAllExportData(queryDTO);
    }

    /**
     * 批量查询导出对象
     *
     * @author SMS
     * @date 2021-12-07 17:25:20
     */
    public List<RepPeopleLogisticsExcelVO> queryBatchExportData(List<Long> idList) {
        return repPeopleLogisticsDao.queryBatchExportData(idList);
    }

    public ResponseDTO<List<RepPeopleLogisticsVO>> selectPeopleLogisticsData(Long currentTeamId, Long currentStageId) {
        List<RepPeopleLogisticsVO> repPeopleLogisticsDataVOList = new ArrayList<>();
        if (currentStageId < 3) {
            repPeopleLogisticsDataVOList = repPeopleLogisticsDao.selectPeopleLogisticsDataPar(null);
        }else{
            repPeopleLogisticsDataVOList = repPeopleLogisticsDao.selectPeopleLogisticsDataRep(currentTeamId, currentStageId);
        }
        return ResponseDTO.succData(repPeopleLogisticsDataVOList);
    }

    /**
     * 查询人事后勤结果表和参数表
     *
     * @param currentTeamId
     * @param currentStageId
     * @return
     */
    public ResponseDTO<Map<String, RepPeopleLogisticsVO>> selectPeopleLogistics(Long currentTeamId, Long currentStageId) {
//        List<Map<String, RepPeopleLogisticsVO>> list = new ArrayList<>();
        Map<String, RepPeopleLogisticsVO> map = new HashMap<>();
        if (currentStageId < 3) {
            List<RepPeopleLogisticsVO> repPeopleLogisticsVOs = parInitialDataPeopleLogisticsDao.selectPeopleLogisticsPar(1L);
            for (RepPeopleLogisticsVO repPeopleLogisticsVO:repPeopleLogisticsVOs ) {
                map.put(repPeopleLogisticsVO.getBusinessType(), repPeopleLogisticsVO);
            }
        }else{
            List<RepPeopleLogisticsVO> repPeopleLogisticsVOs = repPeopleLogisticsDao.selectPeopleLogisticsRep(currentTeamId, currentStageId);
            for (RepPeopleLogisticsVO repPeopleLogisticsVO:repPeopleLogisticsVOs ) {
                map.put(repPeopleLogisticsVO.getBusinessType(), repPeopleLogisticsVO);
            }
        }
//        list.add(map);
        return ResponseDTO.succData(map);
    }

    public List<RepPeopleLogisticsExcelVO> queryExportData(Long proId, Long stageId) {
        return repPeopleLogisticsDao.queryExportData(proId,stageId);
    }
}
