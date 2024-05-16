package net.lab1024.smartadmin.module.business.report.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.report.dao.RepStockPriceDao;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepStockPriceAddDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepStockPriceUpdateDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepStockPriceQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.entity.RepStockPriceEntity;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepStockPriceVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepStockPriceExcelVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [ 股价表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-17 19:06:10
 * @since JDK1.8
 */
@Service
public class RepStockPriceService {

    @Autowired
    private RepStockPriceDao repStockPriceDao;

    /**
     * 根据id查询
     */
    public RepStockPriceEntity getById(Long id){
        return  repStockPriceDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-12-17 19:06:10
     */
    public ResponseDTO<PageResultDTO<RepStockPriceVO>> queryByPage(RepStockPriceQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<RepStockPriceVO> voList = repStockPriceDao.queryByPage(page, queryDTO);
        PageResultDTO<RepStockPriceVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-12-17 19:06:10
     */
    public ResponseDTO<String> add(RepStockPriceAddDTO addDTO) {
        RepStockPriceEntity entity = SmartBeanUtil.copy(addDTO, RepStockPriceEntity.class);
        repStockPriceDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-12-17 19:06:10
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(RepStockPriceUpdateDTO updateDTO) {
        RepStockPriceEntity entity = SmartBeanUtil.copy(updateDTO, RepStockPriceEntity.class);
        repStockPriceDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-12-17 19:06:10
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        repStockPriceDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-12-17 19:06:10
     */
    public List<RepStockPriceExcelVO> queryAllExportData(RepStockPriceQueryDTO queryDTO) {
        return repStockPriceDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-12-17 19:06:10
     */
    public List<RepStockPriceExcelVO> queryBatchExportData(List<Long> idList) {
        return repStockPriceDao.queryBatchExportData(idList);
    }

    public  List<RepStockPriceVO> queryStockPriceByProid(Long proId){
        return repStockPriceDao.queryStockPriceByProid(proId);
    }
}
