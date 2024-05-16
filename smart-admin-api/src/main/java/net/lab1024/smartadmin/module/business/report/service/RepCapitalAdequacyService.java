package net.lab1024.smartadmin.module.business.report.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.report.dao.RepCapitalAdequacyDao;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepCapitalAdequacyAddDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepCapitalAdequacyUpdateDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepCapitalAdequacyQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.entity.RepCapitalAdequacyEntity;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepCapitalAdequacyVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepCapitalAdequacyExcelVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * [ 资本充足率报告 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 11:17:26
 * @since JDK1.8
 */
@Service
public class RepCapitalAdequacyService {

    @Autowired
    private RepCapitalAdequacyDao repCapitalAdequacyDao;

    /**
     * 根据id查询
     */
    public RepCapitalAdequacyEntity getById(Long id){
        return  repCapitalAdequacyDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-11-25 11:17:26
     */
    public ResponseDTO<PageResultDTO<RepCapitalAdequacyVO>> queryByPage(RepCapitalAdequacyQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<RepCapitalAdequacyVO> voList = repCapitalAdequacyDao.queryByPage(page, queryDTO);
        PageResultDTO<RepCapitalAdequacyVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-11-25 11:17:26
     */
    public ResponseDTO<String> add(RepCapitalAdequacyAddDTO addDTO) {
        RepCapitalAdequacyEntity entity = SmartBeanUtil.copy(addDTO, RepCapitalAdequacyEntity.class);
        repCapitalAdequacyDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-11-25 11:17:26
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(RepCapitalAdequacyUpdateDTO updateDTO) {
        RepCapitalAdequacyEntity entity = SmartBeanUtil.copy(updateDTO, RepCapitalAdequacyEntity.class);
        repCapitalAdequacyDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-11-25 11:17:26
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        repCapitalAdequacyDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-11-25 11:17:26
     */
    public List<RepCapitalAdequacyExcelVO> queryAllExportData(RepCapitalAdequacyQueryDTO queryDTO) {
        return repCapitalAdequacyDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-11-25 11:17:26
     */
    public List<RepCapitalAdequacyExcelVO> queryBatchExportData(List<Long> idList) {
        return repCapitalAdequacyDao.queryBatchExportData(idList);
    }

    public ResponseDTO<List<RepCapitalAdequacyVO>> selectRepCapitalAdequacy(Long currentTeamId, Long currentStageId) {
        List<RepCapitalAdequacyVO> repCapitalAdequacyVOList = new ArrayList<>();
        //阶段id有多条数值
            if (currentStageId < 2){
                repCapitalAdequacyVOList = repCapitalAdequacyDao.selectRepCapitalAdequacyPar(1L);
            }else {
            repCapitalAdequacyVOList = repCapitalAdequacyDao.selectRepCapitalAdequacyRep(currentTeamId,currentStageId);
            }
        return ResponseDTO.succData(repCapitalAdequacyVOList);
    }

    public List<RepCapitalAdequacyExcelVO> queryExportData(Long proId, Long stageId) {
        return repCapitalAdequacyDao.queryExportData(proId,stageId);
    }
}
