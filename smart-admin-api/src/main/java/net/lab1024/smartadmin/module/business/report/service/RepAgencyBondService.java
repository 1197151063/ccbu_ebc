package net.lab1024.smartadmin.module.business.report.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.report.dao.RepAgencyBondDao;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepAgencyBondAddDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepAgencyBondUpdateDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepAgencyBondQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.entity.RepAgencyBondEntity;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepAgencyBondVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepAgencyBondExcelVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * [ 代理债券发行业务数据表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-08 12:46:28
 * @since JDK1.8
 */
@Service
public class RepAgencyBondService {

    @Autowired
    private RepAgencyBondDao repAgencyBondDao;

    /**
     * 根据id查询
     */
    public RepAgencyBondEntity getById(Long id){
        return  repAgencyBondDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-12-08 12:46:28
     */
    public ResponseDTO<PageResultDTO<RepAgencyBondVO>> queryByPage(RepAgencyBondQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<RepAgencyBondVO> voList = repAgencyBondDao.queryByPage(page, queryDTO);
        PageResultDTO<RepAgencyBondVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-12-08 12:46:28
     */
    public ResponseDTO<String> add(RepAgencyBondAddDTO addDTO) {
        RepAgencyBondEntity entity = SmartBeanUtil.copy(addDTO, RepAgencyBondEntity.class);
        repAgencyBondDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-12-08 12:46:28
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(RepAgencyBondUpdateDTO updateDTO) {
        RepAgencyBondEntity entity = SmartBeanUtil.copy(updateDTO, RepAgencyBondEntity.class);
        repAgencyBondDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-12-08 12:46:28
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        repAgencyBondDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-12-08 12:46:28
     */
    public List<RepAgencyBondExcelVO> queryAllExportData(RepAgencyBondQueryDTO queryDTO) {
        return repAgencyBondDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-12-08 12:46:28
     */
    public List<RepAgencyBondExcelVO> queryBatchExportData(List<Long> idList) {
        return repAgencyBondDao.queryBatchExportData(idList);
    }

//    /**
//     * @author zhs
//     * @param currentProjectId
//     * @param currentStageId
//     * @return
//     */
//    public ResponseDTO<List<RepAgencyBondVO>> selectAgencyBondAll(Long currentProjectId, Long currentStageId) {
//        List<RepAgencyBondVO> repAgencyBondVOS = repAgencyBondDao.selectAgencyBondAll(currentProjectId, currentStageId);
//        return ResponseDTO.succData(repAgencyBondVOS);
//    }

    /**
     * wz
     * @param currentProjectId
     * @param currentStageId
     * @return
     */
    public ResponseDTO<List<RepAgencyBondVO>> selectAgencyBondAll(Long currentProjectId, Long currentStageId) {
        List<RepAgencyBondVO> repAgencyBondVOS = new ArrayList<>();
        if (currentStageId < 5){
             repAgencyBondVOS = repAgencyBondDao.selectAgencyBond(1L);
        }else {
            repAgencyBondVOS = repAgencyBondDao.selectAgencyBondAll(currentProjectId, currentStageId);
        }
        return ResponseDTO.succData(repAgencyBondVOS);
    }
}
