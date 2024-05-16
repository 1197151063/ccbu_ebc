package net.lab1024.smartadmin.module.business.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.param.dao.ParPropertyLiabilityRateDao;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParPropertyLiabilityRateAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParPropertyLiabilityRateUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParPropertyLiabilityRateQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParPropertyLiabilityRateEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParPropertyLiabilityRateVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParPropertyLiabilityRateExcelVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [ 资产负债利率表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 13:52:53
 * @since JDK1.8
 */
@Service
public class ParPropertyLiabilityRateService {

    @Autowired
    private ParPropertyLiabilityRateDao parPropertyLiabilityRateDao;

    /**
     * 根据id查询
     */
    public ParPropertyLiabilityRateEntity getById(Long id){
        return  parPropertyLiabilityRateDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-11-25 13:52:53
     */
    public ResponseDTO<PageResultDTO<ParPropertyLiabilityRateVO>> queryByPage(ParPropertyLiabilityRateQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ParPropertyLiabilityRateVO> voList = parPropertyLiabilityRateDao.queryByPage(page, queryDTO);
        PageResultDTO<ParPropertyLiabilityRateVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-11-25 13:52:53
     */
    public ResponseDTO<String> add(ParPropertyLiabilityRateAddDTO addDTO) {
        ParPropertyLiabilityRateEntity entity = SmartBeanUtil.copy(addDTO, ParPropertyLiabilityRateEntity.class);
        parPropertyLiabilityRateDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-11-25 13:52:53
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ParPropertyLiabilityRateUpdateDTO updateDTO) {
        ParPropertyLiabilityRateEntity entity = SmartBeanUtil.copy(updateDTO, ParPropertyLiabilityRateEntity.class);
        parPropertyLiabilityRateDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-11-25 13:52:53
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        parPropertyLiabilityRateDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-11-25 13:52:53
     */
    public List<ParPropertyLiabilityRateExcelVO> queryAllExportData(ParPropertyLiabilityRateQueryDTO queryDTO) {
        return parPropertyLiabilityRateDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-11-25 13:52:53
     */
    public List<ParPropertyLiabilityRateExcelVO> queryBatchExportData(List<Long> idList) {
        return parPropertyLiabilityRateDao.queryBatchExportData(idList);
    }

    public ResponseDTO<Map<String,ParPropertyLiabilityRateVO>> selectRate(Long stageId) {
        Map<String,ParPropertyLiabilityRateVO> map = new HashMap<>();
        List<ParPropertyLiabilityRateVO> parPropertyLiabilityRateVOs = parPropertyLiabilityRateDao.selectRate(stageId);
        for (ParPropertyLiabilityRateVO parPropertyLiabilityRateVO:parPropertyLiabilityRateVOs) {
            map.put(parPropertyLiabilityRateVO.getInterestRateType(),parPropertyLiabilityRateVO);
        }
        return ResponseDTO.succData(map);
    }

}
