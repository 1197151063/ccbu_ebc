package net.lab1024.smartadmin.module.business.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.param.dao.ParStockCalculationIndexDao;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParStockCalculationIndexAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParStockCalculationIndexUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParStockCalculationIndexQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParStockCalculationIndexEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParStockCalculationIndexVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParStockCalculationIndexExcelVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [ 股价计划指标 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-17 19:09:54
 * @since JDK1.8
 */
@Service
public class ParStockCalculationIndexService {

    @Autowired
    private ParStockCalculationIndexDao parStockCalculationIndexDao;

    /**
     * 根据id查询
     */
    public ParStockCalculationIndexEntity getById(Long id){
        return  parStockCalculationIndexDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-12-17 19:09:54
     */
    public ResponseDTO<PageResultDTO<ParStockCalculationIndexVO>> queryByPage(ParStockCalculationIndexQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ParStockCalculationIndexVO> voList = parStockCalculationIndexDao.queryByPage(page, queryDTO);
        PageResultDTO<ParStockCalculationIndexVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-12-17 19:09:54
     */
    public ResponseDTO<String> add(ParStockCalculationIndexAddDTO addDTO) {
        ParStockCalculationIndexEntity entity = SmartBeanUtil.copy(addDTO, ParStockCalculationIndexEntity.class);
        parStockCalculationIndexDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-12-17 19:09:54
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ParStockCalculationIndexUpdateDTO updateDTO) {
        ParStockCalculationIndexEntity entity = SmartBeanUtil.copy(updateDTO, ParStockCalculationIndexEntity.class);
        parStockCalculationIndexDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-12-17 19:09:54
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        parStockCalculationIndexDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-12-17 19:09:54
     */
    public List<ParStockCalculationIndexExcelVO> queryAllExportData(ParStockCalculationIndexQueryDTO queryDTO) {
        return parStockCalculationIndexDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-12-17 19:09:54
     */
    public List<ParStockCalculationIndexExcelVO> queryBatchExportData(List<Long> idList) {
        return parStockCalculationIndexDao.queryBatchExportData(idList);
    }
}
