package net.lab1024.smartadmin.module.business.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.param.dao.ParInitialDataLiquidityTotalityDao;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataLiquidityTotalityAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataLiquidityTotalityUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataLiquidityTotalityQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParInitialDataLiquidityTotalityEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataLiquidityTotalityVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataLiquidityTotalityExcelVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [ 初始数据表-流动性报表-总体 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 13:45:23
 * @since JDK1.8
 */
@Service
public class ParInitialDataLiquidityTotalityService {

    @Autowired
    private ParInitialDataLiquidityTotalityDao parInitialDataLiquidityTotalityDao;

    /**
     * 根据id查询
     */
    public ParInitialDataLiquidityTotalityEntity getById(Long id){
        return  parInitialDataLiquidityTotalityDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-11-25 13:45:23
     */
    public ResponseDTO<PageResultDTO<ParInitialDataLiquidityTotalityVO>> queryByPage(ParInitialDataLiquidityTotalityQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ParInitialDataLiquidityTotalityVO> voList = parInitialDataLiquidityTotalityDao.queryByPage(page, queryDTO);
        PageResultDTO<ParInitialDataLiquidityTotalityVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-11-25 13:45:23
     */
    public ResponseDTO<String> add(ParInitialDataLiquidityTotalityAddDTO addDTO) {
        ParInitialDataLiquidityTotalityEntity entity = SmartBeanUtil.copy(addDTO, ParInitialDataLiquidityTotalityEntity.class);
        parInitialDataLiquidityTotalityDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-11-25 13:45:23
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ParInitialDataLiquidityTotalityUpdateDTO updateDTO) {
        ParInitialDataLiquidityTotalityEntity entity = SmartBeanUtil.copy(updateDTO, ParInitialDataLiquidityTotalityEntity.class);
        parInitialDataLiquidityTotalityDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-11-25 13:45:23
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        parInitialDataLiquidityTotalityDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-11-25 13:45:23
     */
    public List<ParInitialDataLiquidityTotalityExcelVO> queryAllExportData(ParInitialDataLiquidityTotalityQueryDTO queryDTO) {
        return parInitialDataLiquidityTotalityDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-11-25 13:45:23
     */
    public List<ParInitialDataLiquidityTotalityExcelVO> queryBatchExportData(List<Long> idList) {
        return parInitialDataLiquidityTotalityDao.queryBatchExportData(idList);
    }
}
