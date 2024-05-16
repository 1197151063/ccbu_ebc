package net.lab1024.smartadmin.module.business.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.param.dao.ParMarketShareDao;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParMarketShareAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParMarketShareUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParMarketShareQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParMarketShareEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParMarketShareVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParMarketShareExcelVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [ 市场份额 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-13 20:06:33
 * @since JDK1.8
 */
@Service
public class ParMarketShareService {

    @Autowired
    private ParMarketShareDao parMarketShareDao;

    /**
     * 根据id查询
     */
    public ParMarketShareEntity getById(Long id){
        return  parMarketShareDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-12-13 20:06:33
     */
    public ResponseDTO<PageResultDTO<ParMarketShareVO>> queryByPage(ParMarketShareQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ParMarketShareVO> voList = parMarketShareDao.queryByPage(page, queryDTO);
        PageResultDTO<ParMarketShareVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-12-13 20:06:33
     */
    public ResponseDTO<String> add(ParMarketShareAddDTO addDTO) {
        ParMarketShareEntity entity = SmartBeanUtil.copy(addDTO, ParMarketShareEntity.class);
        parMarketShareDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-12-13 20:06:33
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ParMarketShareUpdateDTO updateDTO) {
        ParMarketShareEntity entity = SmartBeanUtil.copy(updateDTO, ParMarketShareEntity.class);
        parMarketShareDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-12-13 20:06:33
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        parMarketShareDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-12-13 20:06:33
     */
    public List<ParMarketShareExcelVO> queryAllExportData(ParMarketShareQueryDTO queryDTO) {
        return parMarketShareDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-12-13 20:06:33
     */
    public List<ParMarketShareExcelVO> queryBatchExportData(List<Long> idList) {
        return parMarketShareDao.queryBatchExportData(idList);
    }

    public List<ParMarketShareExcelVO> queryExportData(Long proId, Long stageId) {
        return parMarketShareDao.queryExportData(proId,stageId);
    }
}
