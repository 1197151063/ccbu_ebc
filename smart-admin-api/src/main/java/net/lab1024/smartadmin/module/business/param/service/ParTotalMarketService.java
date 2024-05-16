package net.lab1024.smartadmin.module.business.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.param.dao.ParTotalMarketDao;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParTotalMarketAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParTotalMarketUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParTotalMarketQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParTotalMarketEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParTotalMarketVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParTotalMarketExcelVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [ 市场总额 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-30 18:27:21
 * @since JDK1.8
 */
@Service
public class ParTotalMarketService {

    @Autowired
    private ParTotalMarketDao parTotalMarketDao;

    /**
     * 根据id查询
     */
    public ParTotalMarketEntity getById(Long id){
        return  parTotalMarketDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-11-30 18:27:21
     */
    public ResponseDTO<PageResultDTO<ParTotalMarketVO>> queryByPage(ParTotalMarketQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ParTotalMarketVO> voList = parTotalMarketDao.queryByPage(page, queryDTO);
        PageResultDTO<ParTotalMarketVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-11-30 18:27:21
     */
    public ResponseDTO<String> add(ParTotalMarketAddDTO addDTO) {
        ParTotalMarketEntity entity = SmartBeanUtil.copy(addDTO, ParTotalMarketEntity.class);
        parTotalMarketDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-11-30 18:27:21
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ParTotalMarketUpdateDTO updateDTO) {
        ParTotalMarketEntity entity = SmartBeanUtil.copy(updateDTO, ParTotalMarketEntity.class);
        parTotalMarketDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-11-30 18:27:21
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        parTotalMarketDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-11-30 18:27:21
     */
    public List<ParTotalMarketExcelVO> queryAllExportData(ParTotalMarketQueryDTO queryDTO) {
        return parTotalMarketDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-11-30 18:27:21
     */
    public List<ParTotalMarketExcelVO> queryBatchExportData(List<Long> idList) {
        return parTotalMarketDao.queryBatchExportData(idList);
    }
}
