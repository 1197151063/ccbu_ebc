package net.lab1024.smartadmin.module.business.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.param.dao.ParProjectRateDao;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParProjectRateAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParProjectRateUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParProjectRateQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParProjectRateEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParProjectRateVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParProjectRateExcelVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParPropertyLiabilityRateVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [ 项目利率表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 13:52:11
 * @since JDK1.8
 */
@Service
public class ParProjectRateService {

    @Autowired
    private ParProjectRateDao parProjectRateDao;

    /**
     * 根据id查询
     */
    public ParProjectRateEntity getById(Long id){
        return  parProjectRateDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-11-25 13:52:11
     */
    public ResponseDTO<PageResultDTO<ParProjectRateVO>> queryByPage(ParProjectRateQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ParProjectRateVO> voList = parProjectRateDao.queryByPage(page, queryDTO);
        PageResultDTO<ParProjectRateVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-11-25 13:52:11
     */
    public ResponseDTO<String> add(ParProjectRateAddDTO addDTO) {
        ParProjectRateEntity entity = SmartBeanUtil.copy(addDTO, ParProjectRateEntity.class);
        parProjectRateDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-11-25 13:52:11
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ParProjectRateUpdateDTO updateDTO) {
        ParProjectRateEntity entity = SmartBeanUtil.copy(updateDTO, ParProjectRateEntity.class);
        parProjectRateDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-11-25 13:52:11
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        parProjectRateDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-11-25 13:52:11
     */
    public List<ParProjectRateExcelVO> queryAllExportData(ParProjectRateQueryDTO queryDTO) {
        return parProjectRateDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-11-25 13:52:11
     */
    public List<ParProjectRateExcelVO> queryBatchExportData(List<Long> idList) {
        return parProjectRateDao.queryBatchExportData(idList);
    }

    public ResponseDTO<Map<String, ParProjectRateVO>> selectParProjectRate(Long stageId) {
        Map<String, ParProjectRateVO> map = new HashMap<>();
        List<ParProjectRateVO> parProjectRateVOS = parProjectRateDao.selectRate(stageId);
        for (ParProjectRateVO parProjectRateVO:parProjectRateVOS) {
            map.put(parProjectRateVO.getProjectName(),parProjectRateVO);
        }
        return ResponseDTO.succData(map);
    }
}
