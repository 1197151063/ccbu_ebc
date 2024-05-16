package net.lab1024.smartadmin.module.business.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.param.dao.ParEconomicSituationDao;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParEconomicSituationAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParEconomicSituationUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParEconomicSituationQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParEconomicSituationEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParEconomicSituationVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParEconomicSituationExcelVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [ 经济形势分析报告 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-13 13:18:35
 * @since JDK1.8
 */
@Service
public class ParEconomicSituationService {

    @Autowired
    private ParEconomicSituationDao parEconomicSituationDao;

    /**
     * 根据id查询
     */
    public ParEconomicSituationEntity getById(Long id){
        return  parEconomicSituationDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-12-13 13:18:35
     */
    public ResponseDTO<PageResultDTO<ParEconomicSituationVO>> queryByPage(ParEconomicSituationQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ParEconomicSituationVO> voList = parEconomicSituationDao.queryByPage(page, queryDTO);
        PageResultDTO<ParEconomicSituationVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-12-13 13:18:35
     */
    public ResponseDTO<String> add(ParEconomicSituationAddDTO addDTO) {
        ParEconomicSituationEntity entity = SmartBeanUtil.copy(addDTO, ParEconomicSituationEntity.class);
        parEconomicSituationDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-12-13 13:18:35
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ParEconomicSituationUpdateDTO updateDTO) {
        ParEconomicSituationEntity entity = SmartBeanUtil.copy(updateDTO, ParEconomicSituationEntity.class);
        parEconomicSituationDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-12-13 13:18:35
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        parEconomicSituationDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-12-13 13:18:35
     */
    public List<ParEconomicSituationExcelVO> queryAllExportData(ParEconomicSituationQueryDTO queryDTO) {
        return parEconomicSituationDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-12-13 13:18:35
     */
    public List<ParEconomicSituationExcelVO> queryBatchExportData(List<Long> idList) {
        return parEconomicSituationDao.queryBatchExportData(idList);
    }

    /**
     *     查询经济形势分析报告
     * @param currentStageId
     * @return
     */
    public ResponseDTO<ParEconomicSituationVO> queryCurrentStageEconomy(Long currentStageId) {
        ParEconomicSituationVO parEconomicSituationVO = parEconomicSituationDao.queryCurrentStageEconomy(currentStageId);
        return ResponseDTO.succData(parEconomicSituationVO);
    }
}
