package net.lab1024.smartadmin.module.business.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.param.dao.ParInitialDataPeopleLogisticsDao;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataPeopleLogisticsAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataPeopleLogisticsUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataPeopleLogisticsQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParInitialDataPeopleLogisticsEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataPeopleLogisticsVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataPeopleLogisticsExcelVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [ 人事后勤信息录入 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-07 16:27:47
 * @since JDK1.8
 */
@Service
public class ParInitialDataPeopleLogisticsService {

    @Autowired
    private ParInitialDataPeopleLogisticsDao parInitialDataPeopleLogisticsDao;

    /**
     * 根据id查询
     */
    public ParInitialDataPeopleLogisticsEntity getById(Long id){
        return  parInitialDataPeopleLogisticsDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-12-07 16:27:47
     */
    public ResponseDTO<PageResultDTO<ParInitialDataPeopleLogisticsVO>> queryByPage(ParInitialDataPeopleLogisticsQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ParInitialDataPeopleLogisticsVO> voList = parInitialDataPeopleLogisticsDao.queryByPage(page, queryDTO);
        PageResultDTO<ParInitialDataPeopleLogisticsVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-12-07 16:27:47
     */
    public ResponseDTO<String> add(ParInitialDataPeopleLogisticsAddDTO addDTO) {
        ParInitialDataPeopleLogisticsEntity entity = SmartBeanUtil.copy(addDTO, ParInitialDataPeopleLogisticsEntity.class);
        parInitialDataPeopleLogisticsDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-12-07 16:27:47
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ParInitialDataPeopleLogisticsUpdateDTO updateDTO) {
        ParInitialDataPeopleLogisticsEntity entity = SmartBeanUtil.copy(updateDTO, ParInitialDataPeopleLogisticsEntity.class);
        parInitialDataPeopleLogisticsDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-12-07 16:27:47
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        parInitialDataPeopleLogisticsDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-12-07 16:27:47
     */
    public List<ParInitialDataPeopleLogisticsExcelVO> queryAllExportData(ParInitialDataPeopleLogisticsQueryDTO queryDTO) {
        return parInitialDataPeopleLogisticsDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-12-07 16:27:47
     */
    public List<ParInitialDataPeopleLogisticsExcelVO> queryBatchExportData(List<Long> idList) {
        return parInitialDataPeopleLogisticsDao.queryBatchExportData(idList);
    }
}
