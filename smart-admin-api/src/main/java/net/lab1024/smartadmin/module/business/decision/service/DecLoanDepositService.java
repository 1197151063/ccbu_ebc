package net.lab1024.smartadmin.module.business.decision.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.basics.dao.SysProjectTeamStageDao;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysProjectTeamStageVO;
import net.lab1024.smartadmin.module.business.decision.dao.DecLoanDepositDao;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecLoanDepositAddDTO;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecLoanDepositQueryDTO;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecLoanDepositUpdateDTO;
import net.lab1024.smartadmin.module.business.decision.domain.entity.DecLoanDepositEntity;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecLoanDepositExcelVO;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecLoanDepositVO;
import net.lab1024.smartadmin.module.business.param.dao.ParProjectRateDao;
import net.lab1024.smartadmin.module.business.param.dao.ParPropertyLiabilityRateDao;
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
 * [ 存贷款 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-22 13:54:20
 * @since JDK1.8
 */
@Service
@EnableAsync
public class DecLoanDepositService {

    @Autowired
    private DecLoanDepositDao decLoanDepositDao;
    @Autowired
    private SysProjectTeamStageDao sysProjectTeamStageDao;
    @Autowired
    private OperationService operationService;
    @Autowired
    private ParProjectRateDao parProjectRateDao;
    @Autowired
    private ParPropertyLiabilityRateDao parPropertyLiabilityRateDao;
    @Autowired
    private PreOperationService preOperationService;
    @Autowired
    private TDecisionSummaryService tDecisionSummaryService;
    /**
     * 根据id查询
     */
    public DecLoanDepositEntity getById(Long id) {
        return decLoanDepositDao.selectById(id);
    }

    /**
     * 分页查询
     *
     * @author SMS
     * @date 2021-11-22 13:54:20
     */
    public ResponseDTO<PageResultDTO<DecLoanDepositVO>> queryByPage(DecLoanDepositQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<DecLoanDepositVO> voList = decLoanDepositDao.queryByPage(page, queryDTO);
        PageResultDTO<DecLoanDepositVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }
    /**
     * 查询参数
     *
     * @author SMS
     * @date 2021-11-22 13:54:20
     */
    public ResponseDTO<DecLoanDepositVO> query(Long currentProjectId,  Long currentTeamId, Long currentStageId) {
//        DecLoanDepositVO decLoanDepositVO =decLoanDepositDao.queryDataAndStatus(currentProjectId,currentTeamId,currentStageId,"存贷款");
        DecLoanDepositVO decLoanDepositVO =decLoanDepositDao.queryDataAndStatus(currentProjectId,currentTeamId,currentStageId);
        //新阶段开启时自动获取上一阶段数据，填充初始决策数据
        if(decLoanDepositVO==null){
            decLoanDepositVO = decLoanDepositDao.queryDataAndStatus(currentProjectId,currentTeamId,currentStageId-1);
            if (decLoanDepositVO == null){
                //第一次开启上阶段数据为空，new一个新对象来存储数据
                decLoanDepositVO =  new DecLoanDepositVO();
            }
            //还是重新set下当前阶段，防止返回页面的阶段信息错误。因为之前设置为空，所以项目，团队，阶段要重新set一遍
            decLoanDepositVO.setCurrentProjectId(currentProjectId);
            decLoanDepositVO.setCurrentTeamId(currentTeamId);
            decLoanDepositVO.setCurrentStageId(currentStageId);
            //获取上一阶段数据，在保存和提交本阶段数据时根据id删除上一阶段数据，所以将id设为空
            decLoanDepositVO.setId(null);
        }
        //查询提交状态
        Integer status = sysProjectTeamStageDao.querySubmitStatus(currentProjectId, currentTeamId, currentStageId, "存贷款");

       if(decLoanDepositVO!=null){
           decLoanDepositVO.setStatus(status);
       }
        return ResponseDTO.succData(decLoanDepositVO);
    }
    /**
     * 添加
     *
     * @author SMS
     * @date 2021-11-22 13:54:20
     */
    public ResponseDTO<String> add(DecLoanDepositAddDTO addDTO, HttpServletRequest request) {
        DecLoanDepositEntity entity = SmartBeanUtil.copy(addDTO, DecLoanDepositEntity.class);
        DecLoanDepositVO query = decLoanDepositDao.query(entity.getCurrentProjectId(),entity.getCurrentTeamId(), entity.getCurrentStageId());
        if (null == query) {
            decLoanDepositDao.insert(entity);
        } else {
            decLoanDepositDao.deleteData(entity.getCurrentProjectId(),entity.getCurrentTeamId(), entity.getCurrentStageId());
            decLoanDepositDao.insert(entity);
        }
        //添加决策总结表
        TDecisionSummaryEntity tDecisionSummaryEntity = tDecisionSummaryService.queryDecisionSummary(entity.getCurrentProjectId(), entity.getCurrentTeamId(), entity.getCurrentStageId(), "存贷款");
        if(tDecisionSummaryEntity==null){
            tDecisionSummaryService.addDecisionSummary(entity.getCurrentProjectId(), entity.getCurrentTeamId(), entity.getCurrentStageId(),"存贷款",request);
        }
        //提交后改变业务状态
        if (1 == addDTO.getState()) {
            sysProjectTeamStageDao.updateStatus(entity.getCurrentProjectId(), entity.getCurrentTeamId(), entity.getCurrentStageId(), "存贷款", 1);

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
                operationService.depositLoan(addDTO.getCurrentProjectId(), addDTO.getCurrentStageId());
            }
        }
        return ResponseDTO.succ();
    }
//    /**
//     * 预计算
//     *
//     * @author SMS
//     * @date 2021-11-22 13:54:20
//     */
//    public ResponseDTO<Map<String,Object>> preOperation(Long currentProjectId,  Long currentTeamId, Long currentStageId) {
////        DecLoanDepositEntity entity = SmartBeanUtil.copy(addDTO, DecLoanDepositEntity.class);
////        DecLoanDepositVO query = decLoanDepositDao.query(entity.getCurrentProjectId(),entity.getCurrentTeamId(), entity.getCurrentStageId());
////        if (null == query) {
////            decLoanDepositDao.insert(entity);
////        } else {
////            decLoanDepositDao.deleteData(entity.getCurrentProjectId(),entity.getCurrentTeamId(), entity.getCurrentStageId());
////            decLoanDepositDao.insert(entity);
////        }
//        Map<String,Object> map = preOperationService.preOperation(currentProjectId,currentTeamId,currentStageId);
////        Map<String,Object> map = new HashMap<>();
//        return ResponseDTO.succData(map);
//    }
    /**
     * 编辑
     *
     * @author SMS
     * @date 2021-11-22 13:54:20
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(DecLoanDepositUpdateDTO updateDTO) {
        DecLoanDepositEntity entity = SmartBeanUtil.copy(updateDTO, DecLoanDepositEntity.class);
        decLoanDepositDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     *
     * @author SMS
     * @date 2021-11-22 13:54:20
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        decLoanDepositDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     *
     * @author SMS
     * @date 2021-11-22 13:54:20
     */
    public List<DecLoanDepositExcelVO> queryAllExportData(DecLoanDepositQueryDTO queryDTO) {
        return decLoanDepositDao.queryAllExportData(queryDTO);
    }

    /**
     * 批量查询导出对象
     *
     * @author SMS
     * @date 2021-11-22 13:54:20
     */
    public List<DecLoanDepositExcelVO> queryBatchExportData(List<Long> idList) {
        return decLoanDepositDao.queryBatchExportData(idList);
    }

    public List<DecLoanDepositExcelVO> queryExportData(Long proId, Long stageId) {
        return decLoanDepositDao.queryExportData(proId,stageId);
    }
}
