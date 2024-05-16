package net.lab1024.smartadmin.module.business.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.param.dao.ParInitialDataInvestbusStockDao;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataInvestbusStockAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataInvestbusStockUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataInvestbusStockQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParInitialDataInvestbusStockEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataInvestbusStockVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataInvestbusStockExcelVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [ 初始数据表-投资业务营业数据表(股票) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 14:04:09
 * @since JDK1.8
 */
@Service
public class ParInitialDataInvestbusStockService {

    @Autowired
    private ParInitialDataInvestbusStockDao parInitialDataInvestbusStockDao;

    /**
     * 根据id查询
     */
    public ParInitialDataInvestbusStockEntity getById(Long id){
        return  parInitialDataInvestbusStockDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-11-25 14:04:09
     */
    public ResponseDTO<PageResultDTO<ParInitialDataInvestbusStockVO>> queryByPage(ParInitialDataInvestbusStockQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ParInitialDataInvestbusStockVO> voList = parInitialDataInvestbusStockDao.queryByPage(page, queryDTO);
        PageResultDTO<ParInitialDataInvestbusStockVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-11-25 14:04:09
     */
    public ResponseDTO<String> add(ParInitialDataInvestbusStockAddDTO addDTO) {
        ParInitialDataInvestbusStockEntity entity = SmartBeanUtil.copy(addDTO, ParInitialDataInvestbusStockEntity.class);
        parInitialDataInvestbusStockDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-11-25 14:04:09
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ParInitialDataInvestbusStockUpdateDTO updateDTO) {
        ParInitialDataInvestbusStockEntity entity = SmartBeanUtil.copy(updateDTO, ParInitialDataInvestbusStockEntity.class);
        parInitialDataInvestbusStockDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-11-25 14:04:09
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        parInitialDataInvestbusStockDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-11-25 14:04:09
     */
    public List<ParInitialDataInvestbusStockExcelVO> queryAllExportData(ParInitialDataInvestbusStockQueryDTO queryDTO) {
        return parInitialDataInvestbusStockDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-11-25 14:04:09
     */
    public List<ParInitialDataInvestbusStockExcelVO> queryBatchExportData(List<Long> idList) {
        return parInitialDataInvestbusStockDao.queryBatchExportData(idList);
    }
}
