package net.lab1024.smartadmin.module.business.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.param.dao.ParMarketSupplyDemandForecastDao;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParMarketSupplyDemandForecastAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParMarketSupplyDemandForecastQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParMarketSupplyDemandForecastUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParMarketSupplyDemandForecastEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParMarketSupplyDemandForecastExcelVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParMarketSupplyDemandForecastVO;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import net.lab1024.smartadmin.util.SmartPageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [ 市场供求预测表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 13:49:31
 * @since JDK1.8
 */
@Service
public class ParMarketSupplyDemandForecastService {

    @Autowired
    private ParMarketSupplyDemandForecastDao parMarketSupplyDemandForecastDao;

    /**
     * 根据id查询
     */
    public ParMarketSupplyDemandForecastEntity getById(Long id){
        return  parMarketSupplyDemandForecastDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-11-25 13:49:31
     */
    public ResponseDTO<PageResultDTO<ParMarketSupplyDemandForecastVO>> queryByPage(ParMarketSupplyDemandForecastQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ParMarketSupplyDemandForecastVO> voList = parMarketSupplyDemandForecastDao.queryByPage(page, queryDTO);
        PageResultDTO<ParMarketSupplyDemandForecastVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-11-25 13:49:31
     */
    public ResponseDTO<String> add(ParMarketSupplyDemandForecastAddDTO addDTO) {
        ParMarketSupplyDemandForecastEntity entity = SmartBeanUtil.copy(addDTO, ParMarketSupplyDemandForecastEntity.class);
        parMarketSupplyDemandForecastDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-11-25 13:49:31
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ParMarketSupplyDemandForecastUpdateDTO updateDTO) {
        ParMarketSupplyDemandForecastEntity entity = SmartBeanUtil.copy(updateDTO, ParMarketSupplyDemandForecastEntity.class);
        parMarketSupplyDemandForecastDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-11-25 13:49:31
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        parMarketSupplyDemandForecastDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-11-25 13:49:31
     */
    public List<ParMarketSupplyDemandForecastExcelVO> queryAllExportData(ParMarketSupplyDemandForecastQueryDTO queryDTO) {
        return parMarketSupplyDemandForecastDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-11-25 13:49:31
     */
    public List<ParMarketSupplyDemandForecastExcelVO> queryBatchExportData(List<Long> idList) {
        return parMarketSupplyDemandForecastDao.queryBatchExportData(idList);
    }

    public ResponseDTO<Map<String, ParMarketSupplyDemandForecastVO>> selectParMarketSupply(Long stageId) {
        Map<String, ParMarketSupplyDemandForecastVO> map = new HashMap<>();
        List<ParMarketSupplyDemandForecastVO> parMarketSupplyDemandForecastVOS = parMarketSupplyDemandForecastDao.query(stageId);
        for (ParMarketSupplyDemandForecastVO parMarketSupplyDemandForecastVO:parMarketSupplyDemandForecastVOS) {
            map.put(parMarketSupplyDemandForecastVO.getProjectName(),parMarketSupplyDemandForecastVO);
        }
        return ResponseDTO.succData(map);
    }
}
