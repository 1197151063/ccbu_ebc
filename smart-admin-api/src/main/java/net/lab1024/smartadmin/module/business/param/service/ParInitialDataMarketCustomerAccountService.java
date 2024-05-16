package net.lab1024.smartadmin.module.business.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.param.dao.ParInitialDataMarketCustomerAccountDao;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataMarketCustomerAccountAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataMarketCustomerAccountUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataMarketCustomerAccountQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParInitialDataMarketCustomerAccountEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataMarketCustomerAccountVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataMarketCustomerAccountExcelVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [ 初始数据表-市场营销数据(客户账户) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-02 16:38:35
 * @since JDK1.8
 */
@Service
public class ParInitialDataMarketCustomerAccountService {

    @Autowired
    private ParInitialDataMarketCustomerAccountDao parInitialDataMarketCustomerAccountDao;

    /**
     * 根据id查询
     */
    public ParInitialDataMarketCustomerAccountEntity getById(Long id){
        return  parInitialDataMarketCustomerAccountDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-12-02 16:38:35
     */
    public ResponseDTO<PageResultDTO<ParInitialDataMarketCustomerAccountVO>> queryByPage(ParInitialDataMarketCustomerAccountQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ParInitialDataMarketCustomerAccountVO> voList = parInitialDataMarketCustomerAccountDao.queryByPage(page, queryDTO);
        PageResultDTO<ParInitialDataMarketCustomerAccountVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-12-02 16:38:35
     */
    public ResponseDTO<String> add(ParInitialDataMarketCustomerAccountAddDTO addDTO) {
        ParInitialDataMarketCustomerAccountEntity entity = SmartBeanUtil.copy(addDTO, ParInitialDataMarketCustomerAccountEntity.class);
        parInitialDataMarketCustomerAccountDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-12-02 16:38:35
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ParInitialDataMarketCustomerAccountUpdateDTO updateDTO) {
        ParInitialDataMarketCustomerAccountEntity entity = SmartBeanUtil.copy(updateDTO, ParInitialDataMarketCustomerAccountEntity.class);
        parInitialDataMarketCustomerAccountDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-12-02 16:38:35
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        parInitialDataMarketCustomerAccountDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-12-02 16:38:35
     */
    public List<ParInitialDataMarketCustomerAccountExcelVO> queryAllExportData(ParInitialDataMarketCustomerAccountQueryDTO queryDTO) {
        return parInitialDataMarketCustomerAccountDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-12-02 16:38:35
     */
    public List<ParInitialDataMarketCustomerAccountExcelVO> queryBatchExportData(List<Long> idList) {
        return parInitialDataMarketCustomerAccountDao.queryBatchExportData(idList);
    }
}
