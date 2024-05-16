package net.lab1024.smartadmin.module.business.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.param.dao.ParInitialDataLoanDepositDao;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataLoanDepositAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataLoanDepositUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataLoanDepositQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParInitialDataLoanDepositEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataLoanDepositVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataLoanDepositExcelVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [ 初始内容表-资产负债 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 13:46:26
 * @since JDK1.8
 */
@Service
public class ParInitialDataLoanDepositService {

    @Autowired
    private ParInitialDataLoanDepositDao parInitialDataLoanDepositDao;

    /**
     * 根据id查询
     */
    public ParInitialDataLoanDepositEntity getById(Long id){
        return  parInitialDataLoanDepositDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-11-25 13:46:26
     */
    public ResponseDTO<PageResultDTO<ParInitialDataLoanDepositVO>> queryByPage(ParInitialDataLoanDepositQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ParInitialDataLoanDepositVO> voList = parInitialDataLoanDepositDao.queryByPage(page, queryDTO);
        PageResultDTO<ParInitialDataLoanDepositVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-11-25 13:46:26
     */
    public ResponseDTO<String> add(ParInitialDataLoanDepositAddDTO addDTO) {
        ParInitialDataLoanDepositEntity entity = SmartBeanUtil.copy(addDTO, ParInitialDataLoanDepositEntity.class);
        parInitialDataLoanDepositDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-11-25 13:46:26
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ParInitialDataLoanDepositUpdateDTO updateDTO) {
        ParInitialDataLoanDepositEntity entity = SmartBeanUtil.copy(updateDTO, ParInitialDataLoanDepositEntity.class);
        parInitialDataLoanDepositDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-11-25 13:46:26
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        parInitialDataLoanDepositDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-11-25 13:46:26
     */
    public List<ParInitialDataLoanDepositExcelVO> queryAllExportData(ParInitialDataLoanDepositQueryDTO queryDTO) {
        return parInitialDataLoanDepositDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-11-25 13:46:26
     */
    public List<ParInitialDataLoanDepositExcelVO> queryBatchExportData(List<Long> idList) {
        return parInitialDataLoanDepositDao.queryBatchExportData(idList);
    }
}
