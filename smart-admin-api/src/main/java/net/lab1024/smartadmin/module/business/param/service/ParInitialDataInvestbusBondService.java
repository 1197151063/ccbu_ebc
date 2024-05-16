package net.lab1024.smartadmin.module.business.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.param.dao.ParInitialDataInvestbusBondDao;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataInvestbusBondAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataInvestbusBondUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataInvestbusBondQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParInitialDataInvestbusBondEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataInvestbusBondVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataInvestbusBondExcelVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [ 初始数据表-投资业务营业数据表(债券) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 14:03:28
 * @since JDK1.8
 */
@Service
public class ParInitialDataInvestbusBondService {

    @Autowired
    private ParInitialDataInvestbusBondDao parInitialDataInvestbusBondDao;

    /**
     * 根据id查询
     */
    public ParInitialDataInvestbusBondEntity getById(Long id){
        return  parInitialDataInvestbusBondDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-11-25 14:03:28
     */
    public ResponseDTO<PageResultDTO<ParInitialDataInvestbusBondVO>> queryByPage(ParInitialDataInvestbusBondQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ParInitialDataInvestbusBondVO> voList = parInitialDataInvestbusBondDao.queryByPage(page, queryDTO);
        PageResultDTO<ParInitialDataInvestbusBondVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-11-25 14:03:28
     */
    public ResponseDTO<String> add(ParInitialDataInvestbusBondAddDTO addDTO) {
        ParInitialDataInvestbusBondEntity entity = SmartBeanUtil.copy(addDTO, ParInitialDataInvestbusBondEntity.class);
        parInitialDataInvestbusBondDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-11-25 14:03:28
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ParInitialDataInvestbusBondUpdateDTO updateDTO) {
        ParInitialDataInvestbusBondEntity entity = SmartBeanUtil.copy(updateDTO, ParInitialDataInvestbusBondEntity.class);
        parInitialDataInvestbusBondDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-11-25 14:03:28
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        parInitialDataInvestbusBondDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-11-25 14:03:28
     */
    public List<ParInitialDataInvestbusBondExcelVO> queryAllExportData(ParInitialDataInvestbusBondQueryDTO queryDTO) {
        return parInitialDataInvestbusBondDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-11-25 14:03:28
     */
    public List<ParInitialDataInvestbusBondExcelVO> queryBatchExportData(List<Long> idList) {
        return parInitialDataInvestbusBondDao.queryBatchExportData(idList);
    }
}
