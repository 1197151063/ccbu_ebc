package net.lab1024.smartadmin.module.business.report.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.report.dao.RepInvestbusOperatDataStockDao;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepInvestbusOperatDataStockAddDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepInvestbusOperatDataStockQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepInvestbusOperatDataStockUpdateDTO;
import net.lab1024.smartadmin.module.business.report.domain.entity.RepInvestbusOperatDataStockEntity;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepInvestbusOperatDataStockExcelVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepInvestbusOperatDataStockVO;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import net.lab1024.smartadmin.util.SmartPageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [ 投资业务营业数据表(股票) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-24 18:23:18
 * @since JDK1.8
 */
@Service
public class RepInvestbusOperatDataStockService {

    @Autowired
    private RepInvestbusOperatDataStockDao repInvestbusOperatDataStockDao;

    /**
     * 根据id查询
     */
    public RepInvestbusOperatDataStockEntity getById(Long id){
        return  repInvestbusOperatDataStockDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-11-24 18:23:18
     */
    public ResponseDTO<PageResultDTO<RepInvestbusOperatDataStockVO>> queryByPage(RepInvestbusOperatDataStockQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<RepInvestbusOperatDataStockVO> voList = repInvestbusOperatDataStockDao.queryByPage(page, queryDTO);
        PageResultDTO<RepInvestbusOperatDataStockVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-11-24 18:23:18
     */
    public ResponseDTO<String> add(RepInvestbusOperatDataStockAddDTO addDTO) {
        RepInvestbusOperatDataStockEntity entity = SmartBeanUtil.copy(addDTO, RepInvestbusOperatDataStockEntity.class);
        repInvestbusOperatDataStockDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-11-24 18:23:18
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(RepInvestbusOperatDataStockUpdateDTO updateDTO) {
        RepInvestbusOperatDataStockEntity entity = SmartBeanUtil.copy(updateDTO, RepInvestbusOperatDataStockEntity.class);
        repInvestbusOperatDataStockDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-11-24 18:23:18
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        repInvestbusOperatDataStockDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-11-24 18:23:18
     */
    public List<RepInvestbusOperatDataStockExcelVO> queryAllExportData(RepInvestbusOperatDataStockQueryDTO queryDTO) {
        return repInvestbusOperatDataStockDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-11-24 18:23:18
     */
    public List<RepInvestbusOperatDataStockExcelVO> queryBatchExportData(List<Long> idList) {
        return repInvestbusOperatDataStockDao.queryBatchExportData(idList);
    }



    public ResponseDTO<Map<String,Map<String, RepInvestbusOperatDataStockVO>>> selectInvestbusOperatDataStock(Long currentTeamId, Long currentStageId) {

        Map<String,Map<String, RepInvestbusOperatDataStockVO>> map =new HashMap<>();

            if (currentStageId <= 1 ){
                List<RepInvestbusOperatDataStockVO>  repInvestbusOperatDataStockVOList = repInvestbusOperatDataStockDao.selectInvestbusOperatDataStockPar(1L,"短期");
                Map<String,RepInvestbusOperatDataStockVO> map1 =new HashMap<>();
                for (RepInvestbusOperatDataStockVO repInvestbusOperatDataStockVO:repInvestbusOperatDataStockVOList) {
                    map1.put(repInvestbusOperatDataStockVO.getStockProject(),repInvestbusOperatDataStockVO);
                    map.put(repInvestbusOperatDataStockVO.getStockType(),map1);
                }
                List<RepInvestbusOperatDataStockVO>  repInvestbusOperatDataStockVOList1 = repInvestbusOperatDataStockDao.selectInvestbusOperatDataStockPar(1L,"长期");
                Map<String,RepInvestbusOperatDataStockVO> map2 =new HashMap<>();
                for (RepInvestbusOperatDataStockVO repInvestbusOperatDataStockVO:repInvestbusOperatDataStockVOList1) {
                    map2.put(repInvestbusOperatDataStockVO.getStockProject(),repInvestbusOperatDataStockVO);
                    map.put(repInvestbusOperatDataStockVO.getStockType(),map2);
                }

            } else {
                List<RepInvestbusOperatDataStockVO>  repInvestbusOperatDataStockVOList = repInvestbusOperatDataStockDao.selectInvestbusOperatDataStockRep(currentTeamId,currentStageId,"短期");
                Map<String,RepInvestbusOperatDataStockVO> map1 =new HashMap<>();
            for (RepInvestbusOperatDataStockVO repInvestbusOperatDataStockVO:repInvestbusOperatDataStockVOList) {
                map1.put(repInvestbusOperatDataStockVO.getStockProject(),repInvestbusOperatDataStockVO);
                map.put(repInvestbusOperatDataStockVO.getStockType(),map1);
            }
                List<RepInvestbusOperatDataStockVO>  repInvestbusOperatDataStockVOList1 = repInvestbusOperatDataStockDao.selectInvestbusOperatDataStockRep(currentTeamId,currentStageId,"长期");
                Map<String,RepInvestbusOperatDataStockVO> map2 =new HashMap<>();
            for (RepInvestbusOperatDataStockVO repInvestbusOperatDataStockVO:repInvestbusOperatDataStockVOList1) {
                map2.put(repInvestbusOperatDataStockVO.getStockProject(),repInvestbusOperatDataStockVO);
                map.put(repInvestbusOperatDataStockVO.getStockType(),map2);
            }
        }
        return ResponseDTO.succData(map);
    }

    public List<RepInvestbusOperatDataStockExcelVO> queryExportData(Long proId, Long stageId) {
        return repInvestbusOperatDataStockDao.queryExportData(proId,stageId);
    }
}
