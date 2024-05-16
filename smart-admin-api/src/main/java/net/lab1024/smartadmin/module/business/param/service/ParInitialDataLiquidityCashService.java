package net.lab1024.smartadmin.module.business.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.param.dao.ParInitialDataLiquidityCashDao;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataLiquidityCashAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataLiquidityCashUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataLiquidityCashQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParInitialDataLiquidityCashEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataLiquidityCashVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataLiquidityCashExcelVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [ 初始数据表-流动性报表-现金 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 13:44:40
 * @since JDK1.8
 */
@Service
public class ParInitialDataLiquidityCashService {

    @Autowired
    private ParInitialDataLiquidityCashDao parInitialDataLiquidityCashDao;

    /**
     * 根据id查询
     */
    public ParInitialDataLiquidityCashEntity getById(Long id){
        return  parInitialDataLiquidityCashDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-11-25 13:44:40
     */
    public ResponseDTO<PageResultDTO<ParInitialDataLiquidityCashVO>> queryByPage(ParInitialDataLiquidityCashQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ParInitialDataLiquidityCashVO> voList = parInitialDataLiquidityCashDao.queryByPage(page, queryDTO);
        PageResultDTO<ParInitialDataLiquidityCashVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-11-25 13:44:40
     */
    public ResponseDTO<String> add(ParInitialDataLiquidityCashAddDTO addDTO) {
        ParInitialDataLiquidityCashEntity entity = SmartBeanUtil.copy(addDTO, ParInitialDataLiquidityCashEntity.class);
        parInitialDataLiquidityCashDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-11-25 13:44:40
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ParInitialDataLiquidityCashUpdateDTO updateDTO) {
        ParInitialDataLiquidityCashEntity entity = SmartBeanUtil.copy(updateDTO, ParInitialDataLiquidityCashEntity.class);
        parInitialDataLiquidityCashDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-11-25 13:44:40
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        parInitialDataLiquidityCashDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-11-25 13:44:40
     */
    public List<ParInitialDataLiquidityCashExcelVO> queryAllExportData(ParInitialDataLiquidityCashQueryDTO queryDTO) {
        return parInitialDataLiquidityCashDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-11-25 13:44:40
     */
    public List<ParInitialDataLiquidityCashExcelVO> queryBatchExportData(List<Long> idList) {
        return parInitialDataLiquidityCashDao.queryBatchExportData(idList);
    }
}
