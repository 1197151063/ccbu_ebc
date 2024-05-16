package net.lab1024.smartadmin.module.business.decision.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.basics.dao.SysProjectTeamStageDao;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysProjectTeamStageVO;
import net.lab1024.smartadmin.module.business.decision.dao.DecPersonnelDao;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecPersonnelAddDTO;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecPersonnelQueryDTO;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecPersonnelUpdateDTO;
import net.lab1024.smartadmin.module.business.decision.domain.entity.DecPersonnelEntity;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecPersonnelExcelVO;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecPersonnelVO;
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
 * [ 人事后勤信息录入 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-23 16:27:46
 * @since JDK1.8
 */
@Service
@EnableAsync
public class DecPersonnelService {

    @Autowired
    private DecPersonnelDao decPersonnelDao;
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
    public DecPersonnelEntity getById(Long id) {
        return decPersonnelDao.selectById(id);
    }

    /**
     * 分页查询
     *
     * @author SMS
     * @date 2021-11-23 16:27:46
     */
    public ResponseDTO<PageResultDTO<DecPersonnelVO>> queryByPage(DecPersonnelQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<DecPersonnelVO> voList = decPersonnelDao.queryByPage(page, queryDTO);
        PageResultDTO<DecPersonnelVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     *
     * @author SMS
     * @date 2021-11-23 16:27:46
     */
    public ResponseDTO<String> add(DecPersonnelAddDTO addDTO, HttpServletRequest request) {
        DecPersonnelEntity entity = SmartBeanUtil.copy(addDTO, DecPersonnelEntity.class);
        DecPersonnelVO query = decPersonnelDao.query(entity.getCurrentProjectId(), entity.getCurrentTeamId(), entity.getCurrentStageId());

        if (query == null) {
            decPersonnelDao.insert(entity);
        } else {
            decPersonnelDao.deleteData(entity.getCurrentProjectId(), entity.getCurrentTeamId(), entity.getCurrentStageId());
            decPersonnelDao.insert(entity);
        }
        //添加决策总结表
        TDecisionSummaryEntity tDecisionSummaryEntity = tDecisionSummaryService.queryDecisionSummary(entity.getCurrentProjectId(), entity.getCurrentTeamId(), entity.getCurrentStageId(), "人事后勤");
        if(tDecisionSummaryEntity==null){
            tDecisionSummaryService.addDecisionSummary(entity.getCurrentProjectId(), entity.getCurrentTeamId(), entity.getCurrentStageId(),"人事后勤",request);
        }
        //提交后改变业务状态
        if (1 == addDTO.getState()) {
            // Integer status = sysProjectTeamStageDao.queryStatus(entity.getCurrentProjectId(), entity.getCurrentTeamId(), entity.getCurrentStageId(), "人事后勤");
            sysProjectTeamStageDao.updateStatus(entity.getCurrentProjectId(), entity.getCurrentTeamId(), entity.getCurrentStageId(), "人事后勤", 1);
//        if(status==1){
//            sysProjectTeamStageDao.updateStatus(entity.getCurrentProjectId(),entity.getCurrentTeamId(),entity.getCurrentStageId(),"人事后勤",2);
//        }
            List<SysProjectTeamStageVO> sysProjectTeamStageVOs = sysProjectTeamStageDao.selectByStatus(addDTO.getCurrentProjectId(), addDTO.getCurrentStageId());
            boolean flag = true;
            for (SysProjectTeamStageVO s : sysProjectTeamStageVOs) {
                if (s.getStatus() != 1) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                //调用计算接口
                operationService.depositLoan(addDTO.getCurrentProjectId(),addDTO.getCurrentStageId());
            }
        }
        return ResponseDTO.succ();
    }

//    /**
//     * 预计算
//     *
//     * @author SMS
//     * @date 2021-11-23 16:27:46
//     */
//    public ResponseDTO<Map<String,Object>> preOperation(DecPersonnelAddDTO addDTO) {
//        DecPersonnelEntity entity = SmartBeanUtil.copy(addDTO, DecPersonnelEntity.class);
//        DecPersonnelVO query = decPersonnelDao.query(entity.getCurrentProjectId(), entity.getCurrentTeamId(), entity.getCurrentStageId());
//
//        if (query == null) {
//            decPersonnelDao.insert(entity);
//        } else {
//            decPersonnelDao.deleteData(entity.getCurrentProjectId(), entity.getCurrentTeamId(), entity.getCurrentStageId());
//            decPersonnelDao.insert(entity);
//        }
//        Map<String,Object> map = preOperationService.preOperation(addDTO.getCurrentProjectId(),addDTO.getCurrentTeamId(), addDTO.getCurrentStageId());
//        return ResponseDTO.succData(map);
//    }

    /**
     * 编辑
     *
     * @author SMS
     * @date 2021-11-23 16:27:46
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(DecPersonnelUpdateDTO updateDTO) {
        DecPersonnelEntity entity = SmartBeanUtil.copy(updateDTO, DecPersonnelEntity.class);
        decPersonnelDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     *
     * @author SMS
     * @date 2021-11-23 16:27:46
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        decPersonnelDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     *
     * @author SMS
     * @date 2021-11-23 16:27:46
     */
    public List<DecPersonnelExcelVO> queryAllExportData(DecPersonnelQueryDTO queryDTO) {
        return decPersonnelDao.queryAllExportData(queryDTO);
    }

    /**
     * 批量查询导出对象
     *
     * @author SMS
     * @date 2021-11-23 16:27:46
     */
    public List<DecPersonnelExcelVO> queryBatchExportData(List<Long> idList) {
        return decPersonnelDao.queryBatchExportData(idList);
    }

    /**
     * 查询人事决策表
     * @param currentProjectId
     * @param currentTeamId
     * @param currentStageId
     * @return
     */
    public ResponseDTO<DecPersonnelVO> queryPeople(Long currentProjectId, Long currentTeamId, Long currentStageId) {
        DecPersonnelVO decPersonnelVO = decPersonnelDao.queryPeople(currentProjectId,currentTeamId,currentStageId);
        //新阶段开启时自动获取上阶段决策数据，填入初始决策数据
        if (decPersonnelVO == null){
            decPersonnelVO = decPersonnelDao.queryPeople(currentProjectId,currentTeamId,currentStageId-1);
            if (decPersonnelVO == null){
                decPersonnelVO = new DecPersonnelVO();
            }
            //还是重新set下当前阶段，防止返回页面的阶段信息错误。因为之前设置为空，所以项目，团队，阶段要重新set一遍
            decPersonnelVO.setCurrentProjectId(currentProjectId);
            decPersonnelVO.setCurrentTeamId(currentTeamId);
            decPersonnelVO.setCurrentStageId(currentStageId);
            decPersonnelVO.setId(null);
        }
        //查询提交状态
        Integer status = sysProjectTeamStageDao.querySubmitStatus(currentProjectId, currentTeamId, currentStageId, "人事后勤");
        if ( decPersonnelVO != null){
            decPersonnelVO.setStatus(status);
        }
        return ResponseDTO.succData(decPersonnelVO);
    }
}
