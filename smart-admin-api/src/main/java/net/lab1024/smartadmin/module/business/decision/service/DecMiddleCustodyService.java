package net.lab1024.smartadmin.module.business.decision.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.basics.dao.SysProjectTeamStageDao;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysProjectTeamStageVO;
import net.lab1024.smartadmin.module.business.decision.dao.DecMiddleCustodyDao;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecMiddleCustodyAddDTO;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecMiddleCustodyQueryDTO;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecMiddleCustodyUpdateDTO;
import net.lab1024.smartadmin.module.business.decision.domain.entity.DecMiddleCustodyEntity;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecMiddleCustodyExcelVO;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecMiddleCustodyVO;
import net.lab1024.smartadmin.module.business.param.dao.ParAgencyBondIssueDao;
import net.lab1024.smartadmin.module.business.param.dao.ParMarketSupplyDemandForecastDao;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParAgencyBondIssueVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParMarketSupplyDemandForecastVO;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [ 中间业务提交表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-23 15:28:40
 * @since JDK1.8
 */
@Service
@EnableAsync
public class DecMiddleCustodyService {

    @Autowired
    private DecMiddleCustodyDao decMiddleCustodyDao;
    @Autowired
    private SysProjectTeamStageDao sysProjectTeamStageDao;
    @Autowired
    private OperationService operationService;
    @Autowired
    private ParMarketSupplyDemandForecastDao parMarketSupplyDemandForecastDao;
    @Autowired
    private ParAgencyBondIssueDao parAgencyBondIssueDao;
    @Autowired
    private PreOperationService preOperationService;
    @Autowired
    private TDecisionSummaryService tDecisionSummaryService;
    /**
     * 根据id查询
     */
    public DecMiddleCustodyEntity getById(Long id) {
        return decMiddleCustodyDao.selectById(id);
    }

    /**
     * 分页查询
     *
     * @author SMS
     * @date 2021-11-23 15:28:40
     */
    public ResponseDTO<PageResultDTO<DecMiddleCustodyVO>> queryByPage(DecMiddleCustodyQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<DecMiddleCustodyVO> voList = decMiddleCustodyDao.queryByPage(page, queryDTO);
        PageResultDTO<DecMiddleCustodyVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     *
     * @author SMS
     * @date 2021-11-23 15:28:40
     */
    public ResponseDTO<String> add(DecMiddleCustodyAddDTO addDTO, HttpServletRequest request) {
        DecMiddleCustodyEntity entity = SmartBeanUtil.copy(addDTO, DecMiddleCustodyEntity.class);
        DecMiddleCustodyVO query = decMiddleCustodyDao.query(entity.getCurrentProjectId(), entity.getCurrentTeamId(), entity.getCurrentStageId());
        if (null == query) {
            decMiddleCustodyDao.insert(entity);
        } else {
            decMiddleCustodyDao.deleteData(entity.getCurrentProjectId(), entity.getCurrentTeamId(), entity.getCurrentStageId());
            decMiddleCustodyDao.insert(entity);
        }
        //添加决策总结表
        TDecisionSummaryEntity tDecisionSummaryEntity = tDecisionSummaryService.queryDecisionSummary(entity.getCurrentProjectId(), entity.getCurrentTeamId(), entity.getCurrentStageId(), "中间业务");
        if(tDecisionSummaryEntity==null){
            tDecisionSummaryService.addDecisionSummary(entity.getCurrentProjectId(), entity.getCurrentTeamId(), entity.getCurrentStageId(),"中间业务",request);
        }
        //提交后改变业务状态
        if (1 == addDTO.getState()) {
//        Integer status = sysProjectTeamStageDao.queryStatus(entity.getCurrentProjectId(), entity.getCurrentTeamId(), entity.getCurrentStageId(), "中间业务");
//        if(status==0){
            sysProjectTeamStageDao.updateStatus(entity.getCurrentProjectId(), entity.getCurrentTeamId(), entity.getCurrentStageId(), "中间业务", 1);
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
//     * @date 2021-11-23 15:28:40
//     */
//    public ResponseDTO<Map<String,Object>> preOperation(DecMiddleCustodyAddDTO addDTO) {
//        DecMiddleCustodyEntity entity = SmartBeanUtil.copy(addDTO, DecMiddleCustodyEntity.class);
//        DecMiddleCustodyVO query = decMiddleCustodyDao.query(entity.getCurrentProjectId(), entity.getCurrentTeamId(), entity.getCurrentStageId());
//        if (null == query) {
//            decMiddleCustodyDao.insert(entity);
//        } else {
//            decMiddleCustodyDao.deleteData(entity.getCurrentProjectId(), entity.getCurrentTeamId(), entity.getCurrentStageId());
//            decMiddleCustodyDao.insert(entity);
//        }
//        Map<String,Object> map = preOperationService.preOperation(addDTO.getCurrentProjectId(),addDTO.getCurrentTeamId(), addDTO.getCurrentStageId());
//        return ResponseDTO.succData(map);
//    }

    /**
     * 编辑
     *
     * @author SMS
     * @date 2021-11-23 15:28:40
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(DecMiddleCustodyUpdateDTO updateDTO) {
        DecMiddleCustodyEntity entity = SmartBeanUtil.copy(updateDTO, DecMiddleCustodyEntity.class);
        decMiddleCustodyDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     *
     * @author SMS
     * @date 2021-11-23 15:28:40
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        decMiddleCustodyDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     *
     * @author SMS
     * @date 2021-11-23 15:28:40
     */
    public List<DecMiddleCustodyExcelVO> queryAllExportData(DecMiddleCustodyQueryDTO queryDTO) {
        return decMiddleCustodyDao.queryAllExportData(queryDTO);
    }

    /**
     * 批量查询导出对象
     *
     * @author SMS
     * @date 2021-11-23 15:28:40
     */
    public List<DecMiddleCustodyExcelVO> queryBatchExportData(List<Long> idList) {
        return decMiddleCustodyDao.queryBatchExportData(idList);
    }

    public ResponseDTO<Map<String, Object>> query(Long currentStageId, Long agencyCurrentStageId) {
        List<ParMarketSupplyDemandForecastVO> parMarketSupplyDemandForecastVOS = parMarketSupplyDemandForecastDao.query(currentStageId);
        List<ParAgencyBondIssueVO> parAgencyBondIssueVOS = parAgencyBondIssueDao.queryAgency(agencyCurrentStageId);
        Map<String, Object> initParameters = new HashMap<String, Object>();
        initParameters.put("市场供求预测", parMarketSupplyDemandForecastVOS);
        initParameters.put("代理债券发行业务信息", parAgencyBondIssueVOS);
        return ResponseDTO.succData(initParameters);
    }

    /**
     *查询中间业务决策表
     * @param currentProjectId
     * @param currentTeamId
     * @param currentStageId
     * @return
     */
    public ResponseDTO<DecMiddleCustodyVO> queryData(Long currentProjectId, Long currentTeamId, Long currentStageId) {
            DecMiddleCustodyVO decMiddleCustodyVO =decMiddleCustodyDao.queryDataAndStatus(currentProjectId,currentTeamId,currentStageId);
        //新阶段开始获取上阶段数据，填充初始决策数据
        if (decMiddleCustodyVO == null){
            decMiddleCustodyVO = decMiddleCustodyDao.queryDataAndStatus(currentProjectId,currentTeamId,currentStageId-1);
            if (decMiddleCustodyVO == null){
                //第一次开启上阶段数据为空，new一个新对象来存储数据
                decMiddleCustodyVO = new DecMiddleCustodyVO();
            }
            //还是重新set下当前阶段，防止返回页面的阶段信息错误。因为之前设置为空，所以项目，团队，阶段要重新set一遍
            decMiddleCustodyVO.setCurrentProjectId(currentProjectId);
            decMiddleCustodyVO.setCurrentTeamId(currentTeamId);
            decMiddleCustodyVO.setCurrentStageId(currentStageId);
            //获取上一阶段数据，在保存和提交本阶段数据时根据id删除上一阶段数据，所以将id设为空
            decMiddleCustodyVO.setId(null);
        }
        //查询提交状态
        Integer status = sysProjectTeamStageDao.querySubmitStatus(currentProjectId, currentTeamId, currentStageId, "中间业务");
        if(decMiddleCustodyVO!=null) {
            decMiddleCustodyVO.setStatus(status);
        }
        return ResponseDTO.succData(decMiddleCustodyVO);
    }
}
