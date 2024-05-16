package net.lab1024.smartadmin.module.business.decision.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.basics.dao.SysProjectTeamStageDao;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysProjectTeamStageVO;
import net.lab1024.smartadmin.module.business.decision.dao.DecFinancialManagementDao;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecFinancialManagementAddDTO;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecFinancialManagementQueryDTO;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecFinancialManagementUpdateDTO;
import net.lab1024.smartadmin.module.business.decision.domain.entity.DecFinancialManagementEntity;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecFinancialManagementExcelVO;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecFinancialManagementVO;
import net.lab1024.smartadmin.module.business.report.service.OperationService;
import net.lab1024.smartadmin.module.business.report.service.PreOperationService;
import net.lab1024.smartadmin.module.business.teacher.domain.entity.TDecisionSummaryEntity;
import net.lab1024.smartadmin.module.business.teacher.service.TDecisionSummaryService;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import net.lab1024.smartadmin.util.SmartPageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * [ 财务管理表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-23 16:32:30
 * @since JDK1.8
 */
@Service
@EnableAsync
public class DecFinancialManagementService {

    @Autowired
    private DecFinancialManagementDao decFinancialManagementDao;
    @Autowired
    private SysProjectTeamStageDao sysProjectTeamStageDao;
    @Autowired
    private OperationService operationService;
    @Autowired
    private PreOperationService preOperationService;
    @Autowired
    private TDecisionSummaryService tDecisionSummaryService;
    /**
     * 根据id查询
     */
    public DecFinancialManagementEntity getById(Long id) {
        return decFinancialManagementDao.selectById(id);
    }

    /**
     * 分页查询
     *
     * @author SMS
     * @date 2021-11-23 16:32:30
     */
    public ResponseDTO<PageResultDTO<DecFinancialManagementVO>> queryByPage(DecFinancialManagementQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<DecFinancialManagementVO> voList = decFinancialManagementDao.queryByPage(page, queryDTO);
        PageResultDTO<DecFinancialManagementVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     *
     * @author SMS
     * @date 2021-11-23 16:32:30
     */
    public ResponseDTO<String> add(DecFinancialManagementAddDTO addDTO, HttpServletRequest request) {
        DecFinancialManagementEntity entity = SmartBeanUtil.copy(addDTO, DecFinancialManagementEntity.class);
        DecFinancialManagementVO query = decFinancialManagementDao.query(entity.getCurrentProjectId(), entity.getCurrentTeamId(), entity.getCurrentStageId());
        if (query == null) {
            decFinancialManagementDao.insert(entity);
        } else {
            decFinancialManagementDao.deleteData(entity.getCurrentProjectId(), entity.getCurrentTeamId(), entity.getCurrentStageId());
            decFinancialManagementDao.insert(entity);
        }
        //添加决策总结表
        TDecisionSummaryEntity tDecisionSummaryEntity = tDecisionSummaryService.queryDecisionSummary(entity.getCurrentProjectId(), entity.getCurrentTeamId(), entity.getCurrentStageId(), "财务管理");
        if(tDecisionSummaryEntity==null){
            tDecisionSummaryService.addDecisionSummary(entity.getCurrentProjectId(), entity.getCurrentTeamId(), entity.getCurrentStageId(),"财务管理",request);
        }
        //提交后改变业务状态
        //Integer status = sysProjectTeamStageDao.queryStatus(entity.getCurrentProjectId(), entity.getCurrentTeamId(), entity.getCurrentStageId(), "财务管理");
        if (1 == addDTO.getState()) {
            sysProjectTeamStageDao.updateStatus(entity.getCurrentProjectId(), entity.getCurrentTeamId(), entity.getCurrentStageId(), "财务管理", 1);
            List<SysProjectTeamStageVO> sysProjectTeamStageVOs = sysProjectTeamStageDao.selectByStatus(addDTO.getCurrentProjectId(),addDTO.getCurrentStageId());
            boolean flag = true;
            for (SysProjectTeamStageVO s : sysProjectTeamStageVOs) {
                if (s.getStatus() != 1) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                //调用计算接口
                operationService.depositLoan(addDTO.getCurrentProjectId(), addDTO.getCurrentStageId());
            }
        }
        return ResponseDTO.succ();
    }

//    /**
//     * 预计算
//     *
//     * @author SMS
//     * @date 2021-11-23 16:32:30
//     */
//    public ResponseDTO<Map<String,Object>> preOperation(DecFinancialManagementAddDTO addDTO) {
//        DecFinancialManagementEntity entity = SmartBeanUtil.copy(addDTO, DecFinancialManagementEntity.class);
//        DecFinancialManagementVO query = decFinancialManagementDao.query(entity.getCurrentProjectId(), entity.getCurrentTeamId(), entity.getCurrentStageId());
//        if (query == null) {
//            decFinancialManagementDao.insert(entity);
//        } else {
//            decFinancialManagementDao.deleteData(entity.getCurrentProjectId(), entity.getCurrentTeamId(), entity.getCurrentStageId());
//            decFinancialManagementDao.insert(entity);
//        }
//        Map<String,Object> map = preOperationService.preOperation(addDTO.getCurrentProjectId(),addDTO.getCurrentTeamId(), addDTO.getCurrentStageId());
//        return ResponseDTO.succData(map);
//    }

    /**
     * 编辑
     *
     * @author SMS
     * @date 2021-11-23 16:32:30
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(DecFinancialManagementUpdateDTO updateDTO) {
        DecFinancialManagementEntity entity = SmartBeanUtil.copy(updateDTO, DecFinancialManagementEntity.class);
        decFinancialManagementDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     *
     * @author SMS
     * @date 2021-11-23 16:32:30
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        decFinancialManagementDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     *
     * @author SMS
     * @date 2021-11-23 16:32:30
     */
    public List<DecFinancialManagementExcelVO> queryAllExportData(DecFinancialManagementQueryDTO queryDTO) {
        return decFinancialManagementDao.queryAllExportData(queryDTO);
    }

    /**
     * 批量查询导出对象
     *
     * @author SMS
     * @date 2021-11-23 16:32:30
     */
    public List<DecFinancialManagementExcelVO> queryBatchExportData(List<Long> idList) {
        return decFinancialManagementDao.queryBatchExportData(idList);
    }

    /**
     * 查询决策表
     * @param currentProjectId
     * @param currentTeamId
     * @param currentStageId
     * @return
     */
    public ResponseDTO<DecFinancialManagementVO> queryFinancial(Long currentProjectId, Long currentTeamId, Long currentStageId) {
        DecFinancialManagementVO decFinancialManagementVO =decFinancialManagementDao.queryDataAndStatus(currentProjectId,currentTeamId,currentStageId);
        //自动获取上阶段的决策数据，填充当前阶段初始决策数据
        if (decFinancialManagementVO == null){
            decFinancialManagementVO = decFinancialManagementDao.queryDataAndStatus(currentProjectId,currentTeamId,currentStageId-1);
            if (decFinancialManagementVO == null){
                //第一次开启上阶段数据为空，new一个新对象来存储数据
                decFinancialManagementVO =  new DecFinancialManagementVO();
            }
            //还是重新set下当前阶段，防止返回页面的阶段信息错误。因为之前设置为空，所以项目，团队，阶段要重新set一遍
            decFinancialManagementVO.setCurrentProjectId(currentProjectId);
            decFinancialManagementVO.setCurrentTeamId(currentTeamId);
            decFinancialManagementVO.setCurrentStageId(currentStageId);
            decFinancialManagementVO.setId(null);
        }

        //查询提交状态
        Integer status = sysProjectTeamStageDao.querySubmitStatus(currentProjectId, currentTeamId, currentStageId, "财务管理");
        if(decFinancialManagementVO != null){
            decFinancialManagementVO.setStatus(status);
        }
        return ResponseDTO.succData(decFinancialManagementVO);
    }
}
