package net.lab1024.smartadmin.module.business.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.param.dao.ParInitialDataProfitDao;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataProfitAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataProfitUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataProfitQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParInitialDataProfitEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataProfitVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataProfitExcelVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [ 初始数据表-利润 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 13:47:09
 * @since JDK1.8
 */
@Service
public class ParInitialDataProfitService {

    @Autowired
    private ParInitialDataProfitDao parInitialDataProfitDao;

    /**
     * 根据id查询
     */
    public ParInitialDataProfitEntity getById(Long id){
        return  parInitialDataProfitDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-11-25 13:47:09
     */
    public ResponseDTO<PageResultDTO<ParInitialDataProfitVO>> queryByPage(ParInitialDataProfitQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ParInitialDataProfitVO> voList = parInitialDataProfitDao.queryByPage(page, queryDTO);
        PageResultDTO<ParInitialDataProfitVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-11-25 13:47:09
     */
    public ResponseDTO<String> add(ParInitialDataProfitAddDTO addDTO) {
        ParInitialDataProfitEntity entity = SmartBeanUtil.copy(addDTO, ParInitialDataProfitEntity.class);
        parInitialDataProfitDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-11-25 13:47:09
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ParInitialDataProfitUpdateDTO updateDTO) {
        ParInitialDataProfitEntity entity = SmartBeanUtil.copy(updateDTO, ParInitialDataProfitEntity.class);
        parInitialDataProfitDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-11-25 13:47:09
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        parInitialDataProfitDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-11-25 13:47:09
     */
    public List<ParInitialDataProfitExcelVO> queryAllExportData(ParInitialDataProfitQueryDTO queryDTO) {
        return parInitialDataProfitDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-11-25 13:47:09
     */
    public List<ParInitialDataProfitExcelVO> queryBatchExportData(List<Long> idList) {
        return parInitialDataProfitDao.queryBatchExportData(idList);
    }
}
