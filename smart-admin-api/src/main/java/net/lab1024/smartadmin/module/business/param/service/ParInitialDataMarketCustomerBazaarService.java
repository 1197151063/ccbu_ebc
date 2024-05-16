package net.lab1024.smartadmin.module.business.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.param.dao.ParInitialDataMarketCustomerBazaarDao;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataMarketCustomerBazaarAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataMarketCustomerBazaarUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataMarketCustomerBazaarQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParInitialDataMarketCustomerBazaarEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataMarketCustomerBazaarVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataMarketCustomerBazaarExcelVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [ 初始数据表-市场营销数据(客户市场)单位：百万元 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-02 16:39:55
 * @since JDK1.8
 */
@Service
public class ParInitialDataMarketCustomerBazaarService {

    @Autowired
    private ParInitialDataMarketCustomerBazaarDao parInitialDataMarketCustomerBazaarDao;

    /**
     * 根据id查询
     */
    public ParInitialDataMarketCustomerBazaarEntity getById(Long id){
        return  parInitialDataMarketCustomerBazaarDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-12-02 16:39:55
     */
    public ResponseDTO<PageResultDTO<ParInitialDataMarketCustomerBazaarVO>> queryByPage(ParInitialDataMarketCustomerBazaarQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ParInitialDataMarketCustomerBazaarVO> voList = parInitialDataMarketCustomerBazaarDao.queryByPage(page, queryDTO);
        PageResultDTO<ParInitialDataMarketCustomerBazaarVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-12-02 16:39:55
     */
    public ResponseDTO<String> add(ParInitialDataMarketCustomerBazaarAddDTO addDTO) {
        ParInitialDataMarketCustomerBazaarEntity entity = SmartBeanUtil.copy(addDTO, ParInitialDataMarketCustomerBazaarEntity.class);
        parInitialDataMarketCustomerBazaarDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-12-02 16:39:55
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ParInitialDataMarketCustomerBazaarUpdateDTO updateDTO) {
        ParInitialDataMarketCustomerBazaarEntity entity = SmartBeanUtil.copy(updateDTO, ParInitialDataMarketCustomerBazaarEntity.class);
        parInitialDataMarketCustomerBazaarDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-12-02 16:39:55
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        parInitialDataMarketCustomerBazaarDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-12-02 16:39:55
     */
    public List<ParInitialDataMarketCustomerBazaarExcelVO> queryAllExportData(ParInitialDataMarketCustomerBazaarQueryDTO queryDTO) {
        return parInitialDataMarketCustomerBazaarDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-12-02 16:39:55
     */
    public List<ParInitialDataMarketCustomerBazaarExcelVO> queryBatchExportData(List<Long> idList) {
        return parInitialDataMarketCustomerBazaarDao.queryBatchExportData(idList);
    }
}
