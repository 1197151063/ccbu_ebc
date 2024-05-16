package net.lab1024.smartadmin.module.business.decision.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.basics.dao.SysProjectTeamStageDao;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysProjectTeamStageVO;
import net.lab1024.smartadmin.module.business.decision.dao.DecMarketStrategyDao;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecMarketStrategyAddDTO;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecMarketStrategyQueryDTO;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecMarketStrategyUpdateDTO;
import net.lab1024.smartadmin.module.business.decision.domain.entity.DecMarketStrategyEntity;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecMarketStrategyExcelVO;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecMarketStrategyVO;
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
 * [ 市场营销策略 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-23 16:31:22
 * @since JDK1.8
 */
@Service
@EnableAsync
public class DecMarketStrategyService {

    @Autowired
    private DecMarketStrategyDao decMarketStrategyDao;
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
    public DecMarketStrategyEntity getById(Long id) {
        return decMarketStrategyDao.selectById(id);
    }

    /**
     * 分页查询
     *
     * @author SMS
     * @date 2021-11-23 16:31:22
     */
    public ResponseDTO<PageResultDTO<DecMarketStrategyVO>> queryByPage(DecMarketStrategyQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<DecMarketStrategyVO> voList = decMarketStrategyDao.queryByPage(page, queryDTO);
        PageResultDTO<DecMarketStrategyVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     *
     * @author SMS
     * @date 2021-11-23 16:31:22
     */
    public ResponseDTO<String> add(DecMarketStrategyAddDTO addDTO, HttpServletRequest request) {
        DecMarketStrategyEntity entity = SmartBeanUtil.copy(addDTO, DecMarketStrategyEntity.class);
        DecMarketStrategyVO query = decMarketStrategyDao.query(entity.getCurrentProjectId(), entity.getCurrentTeamId(), entity.getCurrentStageId());
        if (null == query) {
            decMarketStrategyDao.insert(entity);
        } else {
            decMarketStrategyDao.deleteData(entity.getCurrentProjectId(), entity.getCurrentTeamId(), entity.getCurrentStageId());
            decMarketStrategyDao.insert(entity);
        }
        //添加决策总结表
        TDecisionSummaryEntity tDecisionSummaryEntity = tDecisionSummaryService.queryDecisionSummary(entity.getCurrentProjectId(), entity.getCurrentTeamId(), entity.getCurrentStageId(), "市场营销");
        if(tDecisionSummaryEntity==null){
            tDecisionSummaryService.addDecisionSummary(entity.getCurrentProjectId(), entity.getCurrentTeamId(), entity.getCurrentStageId(),"市场营销",request);
        }
        //提交后改变业务状态
        //Integer status = sysProjectTeamStageDao.queryStatus(entity.getCurrentProjectId(), entity.getCurrentTeamId(), entity.getCurrentStageId(), "市场营销");

        if (1 == addDTO.getState()) {
            sysProjectTeamStageDao.updateStatus(entity.getCurrentProjectId(), entity.getCurrentTeamId(), entity.getCurrentStageId(), "市场营销", 1);
//        if(status==1){
//            sysProjectTeamStageDao.updateStatus(entity.getCurrentProjectId(),entity.getCurrentTeamId(),entity.getCurrentStageId(),"市场营销",2);
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
                operationService.depositLoan(addDTO.getCurrentProjectId(), addDTO.getCurrentStageId());
            }
        }
        return ResponseDTO.succ();
    }

//    /**
//     * 预计算
//     *
//     * @author SMS
//     * @date 2021-11-23 16:31:22
//     */
//    public ResponseDTO<Map<String,Object>> preOperation(DecMarketStrategyAddDTO addDTO) {
//        DecMarketStrategyEntity entity = SmartBeanUtil.copy(addDTO, DecMarketStrategyEntity.class);
//        DecMarketStrategyVO query = decMarketStrategyDao.query(entity.getCurrentProjectId(), entity.getCurrentTeamId(), entity.getCurrentStageId());
//        if (null == query) {
//            decMarketStrategyDao.insert(entity);
//        } else {
//            decMarketStrategyDao.deleteData(entity.getCurrentProjectId(), entity.getCurrentTeamId(), entity.getCurrentStageId());
//            decMarketStrategyDao.insert(entity);
//        }
//        Map<String,Object> map = preOperationService.preOperation(addDTO.getCurrentProjectId(),addDTO.getCurrentTeamId(), addDTO.getCurrentStageId());
//        return ResponseDTO.succData(map);
//    }

    /**
     * 编辑
     *
     * @author SMS
     * @date 2021-11-23 16:31:22
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(DecMarketStrategyUpdateDTO updateDTO) {
        DecMarketStrategyEntity entity = SmartBeanUtil.copy(updateDTO, DecMarketStrategyEntity.class);
        decMarketStrategyDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     *
     * @author SMS
     * @date 2021-11-23 16:31:22
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        decMarketStrategyDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     *
     * @author SMS
     * @date 2021-11-23 16:31:22
     */
    public List<DecMarketStrategyExcelVO> queryAllExportData(DecMarketStrategyQueryDTO queryDTO) {
        return decMarketStrategyDao.queryAllExportData(queryDTO);
    }

    /**
     * 批量查询导出对象
     *
     * @author SMS
     * @date 2021-11-23 16:31:22
     */
    public List<DecMarketStrategyExcelVO> queryBatchExportData(List<Long> idList) {
        return decMarketStrategyDao.queryBatchExportData(idList);
    }

    /**
     *查询录入的决策信息
     * @param currentProjectId
     * @param currentTeamId
     * @param currentStageId
     * @return
     */
    public ResponseDTO<DecMarketStrategyVO> queryMarket(Long currentProjectId,Long currentTeamId,Long currentStageId) {
        DecMarketStrategyVO decMarketStrategyVO = decMarketStrategyDao.queryDataAndStatus(currentProjectId,currentTeamId,currentStageId);
        //新阶段自动获取上阶段决策数据，填入当前决策数据
        if (decMarketStrategyVO == null){
            decMarketStrategyVO = decMarketStrategyDao.queryDataAndStatus(currentProjectId,currentTeamId,currentStageId-1);
            if (decMarketStrategyVO == null){
                //第一次开启上阶段数据为空，new一个新对象来存储数据
                decMarketStrategyVO =  new DecMarketStrategyVO();
            }
            //还是重新set下当前阶段，防止返回页面的阶段信息错误。因为之前设置为空，所以项目，团队，阶段要重新set一遍
            decMarketStrategyVO.setCurrentProjectId(currentProjectId);
            decMarketStrategyVO.setCurrentTeamId(currentTeamId);
            decMarketStrategyVO.setCurrentStageId(currentStageId);
            decMarketStrategyVO.setId(null);
        }
        //查询提交状态
        Integer status = sysProjectTeamStageDao.querySubmitStatus(currentProjectId, currentTeamId, currentStageId, "市场营销");
        if (decMarketStrategyVO != null){
            decMarketStrategyVO.setStatus(status);
        }
        return ResponseDTO.succData(decMarketStrategyVO);
    }
}
