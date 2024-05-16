package net.lab1024.smartadmin.module.business.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.param.dao.ParInitialDataAutomationDao;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataAutomationAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataAutomationUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataAutomationQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParInitialDataAutomationEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataAutomationVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataAutomationExcelVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [ 初始数据表-人事后勤数据表(自动化投资) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-06 14:22:45
 * @since JDK1.8
 */
@Service
public class ParInitialDataAutomationService {

    @Autowired
    private ParInitialDataAutomationDao parInitialDataAutomationDao;

    /**
     * 根据id查询
     */
    public ParInitialDataAutomationEntity getById(Long id){
        return  parInitialDataAutomationDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-12-06 14:22:45
     */
    public ResponseDTO<PageResultDTO<ParInitialDataAutomationVO>> queryByPage(ParInitialDataAutomationQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ParInitialDataAutomationVO> voList = parInitialDataAutomationDao.queryByPage(page, queryDTO);
        PageResultDTO<ParInitialDataAutomationVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-12-06 14:22:45
     */
    public ResponseDTO<String> add(ParInitialDataAutomationAddDTO addDTO) {
        ParInitialDataAutomationEntity entity = SmartBeanUtil.copy(addDTO, ParInitialDataAutomationEntity.class);
        parInitialDataAutomationDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-12-06 14:22:45
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ParInitialDataAutomationUpdateDTO updateDTO) {
        ParInitialDataAutomationEntity entity = SmartBeanUtil.copy(updateDTO, ParInitialDataAutomationEntity.class);
        parInitialDataAutomationDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-12-06 14:22:45
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        parInitialDataAutomationDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-12-06 14:22:45
     */
    public List<ParInitialDataAutomationExcelVO> queryAllExportData(ParInitialDataAutomationQueryDTO queryDTO) {
        return parInitialDataAutomationDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-12-06 14:22:45
     */
    public List<ParInitialDataAutomationExcelVO> queryBatchExportData(List<Long> idList) {
        return parInitialDataAutomationDao.queryBatchExportData(idList);
    }
}
