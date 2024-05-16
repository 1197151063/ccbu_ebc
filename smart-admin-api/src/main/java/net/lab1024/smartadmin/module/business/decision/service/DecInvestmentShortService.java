package net.lab1024.smartadmin.module.business.decision.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.basics.dao.SysProjectTeamStageDao;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysProjectTeamStageVO;
import net.lab1024.smartadmin.module.business.decision.dao.DecInvestmentLongDao;
import net.lab1024.smartadmin.module.business.decision.dao.DecInvestmentShortDao;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecInvestmentLongAddDTO;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecInvestmentShortQueryDTO;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecInvestmentShortUpdateDTO;
import net.lab1024.smartadmin.module.business.decision.domain.entity.DecInvestmentLongEntity;
import net.lab1024.smartadmin.module.business.decision.domain.entity.DecInvestmentShortEntity;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecInvestmentLongVO;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecInvestmentShortExcelVO;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecInvestmentShortVO;
import net.lab1024.smartadmin.module.business.param.dao.ParProjectRateDao;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParProjectRateVO;
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
 * [ 投资业务(短期投资)提交表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-23 16:36:08
 * @since JDK1.8
 */
@Service
@EnableAsync
public class DecInvestmentShortService {

    @Autowired
    private DecInvestmentShortDao decInvestmentShortDao;
    @Autowired
    private DecInvestmentLongDao decInvestmentLongDao;
    @Autowired
    private SysProjectTeamStageDao sysProjectTeamStageDao;
    @Autowired
    private OperationService operationService;
    @Autowired
    private ParProjectRateDao parProjectRateDao;
    @Autowired
    private PreOperationService preOperationService;
    @Autowired
    private TDecisionSummaryService tDecisionSummaryService;
    /**
     * 根据id查询
     */
    public DecInvestmentShortEntity getById(Long id){
        return  decInvestmentShortDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-11-23 16:36:08
     */
    public ResponseDTO<PageResultDTO<DecInvestmentShortVO>> queryByPage(DecInvestmentShortQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<DecInvestmentShortVO> voList = decInvestmentShortDao.queryByPage(page, queryDTO);
        PageResultDTO<DecInvestmentShortVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }
    /*
    查询长短期数据
     */
    public ResponseDTO<DecInvestmentLongVO> query(Long currentProjectId, Long currentTeamId, Long currentStageId,String bondType) {
        DecInvestmentLongVO decInvestmentLongVO =null;
         decInvestmentLongVO = (DecInvestmentLongVO) decInvestmentShortDao.query(currentProjectId,currentTeamId,currentStageId);
         decInvestmentLongVO =decInvestmentLongDao.query(currentProjectId,currentTeamId,currentStageId,bondType);
        //查询提交状态
        Integer status = sysProjectTeamStageDao.querySubmitStatus(currentProjectId, currentTeamId, currentStageId, "投资业务");
        decInvestmentLongVO.setStatus(status);
        return ResponseDTO.succData(decInvestmentLongVO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-11-23 16:36:08
     */
    public ResponseDTO<String> add(DecInvestmentShortVO addDTO, HttpServletRequest request) {
        DecInvestmentShortEntity decInvestmentShortEntity = SmartBeanUtil.copy(addDTO, DecInvestmentShortEntity.class);
        if(addDTO.getId()!=null){
            decInvestmentShortDao.deleteById(addDTO.getId());
        }
        decInvestmentShortDao.insert(decInvestmentShortEntity);
        //添加决策总结表
        TDecisionSummaryEntity tDecisionSummaryEntity = tDecisionSummaryService.queryDecisionSummary(decInvestmentShortEntity.getCurrentProjectId(), decInvestmentShortEntity.getCurrentTeamId(), decInvestmentShortEntity.getCurrentStageId(), "投资业务");
        if(tDecisionSummaryEntity==null){
            tDecisionSummaryService.addDecisionSummary(decInvestmentShortEntity.getCurrentProjectId(), decInvestmentShortEntity.getCurrentTeamId(), decInvestmentShortEntity.getCurrentStageId(),"投资业务",request);
        }
        if (1 == addDTO.getState()) {
            sysProjectTeamStageDao.updateStatus(decInvestmentShortEntity.getCurrentProjectId(), decInvestmentShortEntity.getCurrentTeamId(), decInvestmentShortEntity.getCurrentStageId(), "投资业务", 1);
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
//     * @author SMS
//     * @date 2021-11-23 16:36:08
//     */
//    public ResponseDTO<Map<String,Object>> preOperation(DecInvestmentShortVO addDTO) {
//        DecInvestmentShortEntity decInvestmentShortEntity = SmartBeanUtil.copy(addDTO, DecInvestmentShortEntity.class);
//        if(addDTO.getId()!=null){
//            decInvestmentShortDao.deleteById(addDTO.getId());
//        }
//        decInvestmentShortDao.insert(decInvestmentShortEntity);
//        Map<String,Object> map = preOperationService.preOperation(addDTO.getCurrentProjectId(),addDTO.getCurrentTeamId(), addDTO.getCurrentStageId());
//        return ResponseDTO.succData(map);
//    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-11-23 16:36:08
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(DecInvestmentShortUpdateDTO updateDTO) {
        DecInvestmentShortEntity entity = SmartBeanUtil.copy(updateDTO, DecInvestmentShortEntity.class);
        decInvestmentShortDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-11-23 16:36:08
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        decInvestmentShortDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-11-23 16:36:08
     */
    public List<DecInvestmentShortExcelVO> queryAllExportData(DecInvestmentShortQueryDTO queryDTO) {
        return decInvestmentShortDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-11-23 16:36:08
     */
    public List<DecInvestmentShortExcelVO> queryBatchExportData(List<Long> idList) {
        return decInvestmentShortDao.queryBatchExportData(idList);
    }


    /**
     * 查询股票债券
     * @param beforeCurrentStageId
     * @param currentStageId
     * @return
     */
    public ResponseDTO<Map<Long,List<ParProjectRateVO>>> selectPar(Long beforeCurrentStageId, Long currentStageId) {
        Map<Long,List<ParProjectRateVO>> map =new HashMap<>();
        List<ParProjectRateVO> parProjectRateVO  = parProjectRateDao.selectPar(beforeCurrentStageId);
        List<ParProjectRateVO> parProjectRateVOb  = parProjectRateDao.selectPar(currentStageId);
        map.put(beforeCurrentStageId,parProjectRateVO);
        map.put(currentStageId,parProjectRateVOb);
        return ResponseDTO.succData(map);
    }

    public ResponseDTO<DecInvestmentShortVO> query(Long currentProjectId, Long currentTeamId, Long currentStageId) {
            DecInvestmentShortVO decInvestmentShortVO = decInvestmentShortDao.query(currentProjectId, currentTeamId, currentStageId);
        //新阶段开启时自动获取上一阶段数据，填充初始决策数据
        if (decInvestmentShortVO == null){
            decInvestmentShortVO = decInvestmentShortDao.query(currentProjectId,currentTeamId,currentStageId-1);
            if (decInvestmentShortVO == null){
                //第一次开启上阶段数据为空，new一个新对象来存储数据
                decInvestmentShortVO = new DecInvestmentShortVO();
            }
            //还是重新set下当前阶段，防止返回页面的阶段信息错误。因为之前设置为空，所以项目，团队，阶段要重新set一遍
            decInvestmentShortVO.setCurrentProjectId(currentProjectId);
            decInvestmentShortVO.setCurrentTeamId(currentTeamId);
            decInvestmentShortVO.setCurrentStageId(currentStageId);
            //获取上一阶段数据，在保存和提交本阶段数据时根据id删除上一阶段数据，所以将id设为空
            decInvestmentShortVO.setId(null);
        }
        //查询提交状态
        Integer status = sysProjectTeamStageDao.querySubmitStatus(currentProjectId, currentTeamId, currentStageId, "投资业务");

        if(decInvestmentShortVO!=null){
            decInvestmentShortVO.setStatus(status);
        }
        return ResponseDTO.succData(decInvestmentShortVO);
    }

    public ResponseDTO<String> addLongInvestment(DecInvestmentLongAddDTO addDTO) {
        //获取提交的数据，然后根据类型，分别插入
        //定义投资类型：ABCDEF为债券类型，G表示股票
        Long currentProjectId = addDTO.getCurrentProjectId();
        Long currentStageId = addDTO.getCurrentStageId();
        Long currentTeamId = addDTO.getCurrentTeamId();
        DecInvestmentLongEntity base = new DecInvestmentLongEntity();
        base.setCurrentProjectId(currentProjectId);
        base.setCurrentStageId(currentStageId);
        base.setCurrentTeamId(currentTeamId);
        String[] type = new String[]{"A","B","C","D","E","F","G"};
        //1阶段没有数据，2阶段 G-2
//        if (currentStageId == 2 && addDTO.getShares1()!=null){
//            DecInvestmentLongEntity decInvestmentLongVO = SmartBeanUtil.copy(base, DecInvestmentLongEntity.class);
//            decInvestmentLongVO.setBondType(type[6]);
//            decInvestmentLongVO.setBuySell(addDTO.getShares1());
//            decInvestmentLongDao.deleteInvesmentDataByType(currentProjectId,currentTeamId,currentStageId,type[6]);
//            decInvestmentLongDao.insert(decInvestmentLongVO);
//        }
        if (currentStageId == 3){
            if(addDTO.getShares2()!=null){
                DecInvestmentLongEntity decInvestmentLongVO = SmartBeanUtil.copy(base, DecInvestmentLongEntity.class);
                decInvestmentLongVO.setBondType(type[6]);
                decInvestmentLongVO.setBuySell(addDTO.getShares2());
                decInvestmentLongDao.deleteInvesmentDataByType(currentProjectId,currentTeamId,currentStageId,type[6]);
                decInvestmentLongDao.insert(decInvestmentLongVO);
            }
            if (addDTO.getBond_a_2()!=null){
                DecInvestmentLongEntity decInvestmentLongVO = SmartBeanUtil.copy(base, DecInvestmentLongEntity.class);
                decInvestmentLongVO.setBondType(type[0]);
                decInvestmentLongVO.setBuySell(addDTO.getBond_a_2());
                decInvestmentLongDao.deleteInvesmentDataByType(currentProjectId,currentTeamId,currentStageId,type[0]);
                decInvestmentLongDao.insert(decInvestmentLongVO);
            }
            if (addDTO.getBond_b_2()!=null){
                DecInvestmentLongEntity decInvestmentLongVO = SmartBeanUtil.copy(base, DecInvestmentLongEntity.class);
                decInvestmentLongVO.setBondType(type[1]);
                decInvestmentLongVO.setBuySell(addDTO.getBond_b_2());
                decInvestmentLongDao.deleteInvesmentDataByType(currentProjectId,currentTeamId,currentStageId,type[1]);
                decInvestmentLongDao.insert(decInvestmentLongVO);
            }
            if (addDTO.getBond_c_2()!=null){
                DecInvestmentLongEntity decInvestmentLongVO = SmartBeanUtil.copy(base, DecInvestmentLongEntity.class);
                decInvestmentLongVO.setBondType(type[2]);
                decInvestmentLongDao.deleteInvesmentDataByType(currentProjectId,currentTeamId,currentStageId,type[2]);
                decInvestmentLongVO.setBuySell(addDTO.getBond_c_2());
                decInvestmentLongDao.insert(decInvestmentLongVO);
            }
        }
        if (currentStageId ==4 ){
            if(addDTO.getShares3()!=null){
                DecInvestmentLongEntity decInvestmentLongVO = SmartBeanUtil.copy(base, DecInvestmentLongEntity.class);
                decInvestmentLongVO.setBondType(type[6]);
                decInvestmentLongVO.setBuySell(addDTO.getShares3());
                decInvestmentLongDao.deleteInvesmentDataByType(currentProjectId,currentTeamId,currentStageId,type[6]);
                decInvestmentLongDao.insert(decInvestmentLongVO);
            }
            if (addDTO.getBond_a_3()!=null){
                DecInvestmentLongEntity decInvestmentLongVO = SmartBeanUtil.copy(base, DecInvestmentLongEntity.class);
                decInvestmentLongVO.setBondType(type[0]);
                decInvestmentLongVO.setBuySell(addDTO.getBond_a_3());
                decInvestmentLongDao.deleteInvesmentDataByType(currentProjectId,currentTeamId,currentStageId,type[0]);
                decInvestmentLongDao.insert(decInvestmentLongVO);
            }
            if (addDTO.getBond_b_3()!=null){
                DecInvestmentLongEntity decInvestmentLongVO = SmartBeanUtil.copy(base, DecInvestmentLongEntity.class);
                decInvestmentLongVO.setBondType(type[1]);
                decInvestmentLongVO.setBuySell(addDTO.getBond_b_3());
                decInvestmentLongDao.deleteInvesmentDataByType(currentProjectId,currentTeamId,currentStageId,type[1]);

                decInvestmentLongDao.insert(decInvestmentLongVO);
            }
            if (addDTO.getBond_c_3()!=null){
                DecInvestmentLongEntity decInvestmentLongVO = SmartBeanUtil.copy(base, DecInvestmentLongEntity.class);
                decInvestmentLongVO.setBondType(type[2]);
                decInvestmentLongVO.setBuySell(addDTO.getBond_c_3());
                decInvestmentLongDao.deleteInvesmentDataByType(currentProjectId,currentTeamId,currentStageId,type[2]);
                decInvestmentLongDao.insert(decInvestmentLongVO);
            }
            if (addDTO.getBond_d_3()!=null){
                DecInvestmentLongEntity decInvestmentLongVO = SmartBeanUtil.copy(base, DecInvestmentLongEntity.class);
                decInvestmentLongVO.setBondType(type[3]);
                decInvestmentLongVO.setBuySell(addDTO.getBond_d_3());
                decInvestmentLongDao.deleteInvesmentDataByType(currentProjectId,currentTeamId,currentStageId,type[3]);
                decInvestmentLongDao.insert(decInvestmentLongVO);
            }
        }
        if (currentStageId == 5){
            if(addDTO.getShares4()!=null){
                DecInvestmentLongEntity decInvestmentLongVO = SmartBeanUtil.copy(base, DecInvestmentLongEntity.class);
                decInvestmentLongVO.setBondType(type[6]);
                decInvestmentLongVO.setBuySell(addDTO.getShares4());
                decInvestmentLongDao.deleteInvesmentDataByType(currentProjectId,currentTeamId,currentStageId,type[6]);
                decInvestmentLongDao.insert(decInvestmentLongVO);
            }
            if (addDTO.getBond_a_4()!=null){
                DecInvestmentLongEntity decInvestmentLongVO = SmartBeanUtil.copy(base, DecInvestmentLongEntity.class);
                decInvestmentLongVO.setBondType(type[0]);
                decInvestmentLongVO.setBuySell(addDTO.getBond_a_4());
                decInvestmentLongDao.deleteInvesmentDataByType(currentProjectId,currentTeamId,currentStageId,type[0]);
                decInvestmentLongDao.insert(decInvestmentLongVO);
            }
            if (addDTO.getBond_b_4()!=null){
                DecInvestmentLongEntity decInvestmentLongVO = SmartBeanUtil.copy(base, DecInvestmentLongEntity.class);
                decInvestmentLongVO.setBondType(type[1]);
                decInvestmentLongVO.setBuySell(addDTO.getBond_b_4());
                decInvestmentLongDao.deleteInvesmentDataByType(currentProjectId,currentTeamId,currentStageId,type[1]);
                decInvestmentLongDao.insert(decInvestmentLongVO);
            }
            if (addDTO.getBond_c_4()!=null){
                DecInvestmentLongEntity decInvestmentLongVO = SmartBeanUtil.copy(base, DecInvestmentLongEntity.class);
                decInvestmentLongVO.setBondType(type[2]);
                decInvestmentLongVO.setBuySell(addDTO.getBond_c_4());
                decInvestmentLongDao.deleteInvesmentDataByType(currentProjectId,currentTeamId,currentStageId,type[2]);
                decInvestmentLongDao.insert(decInvestmentLongVO);
            }
            if (addDTO.getBond_d_4()!=null){
                DecInvestmentLongEntity decInvestmentLongVO = SmartBeanUtil.copy(base, DecInvestmentLongEntity.class);
                decInvestmentLongVO.setBondType(type[3]);
                decInvestmentLongVO.setBuySell(addDTO.getBond_d_4());
                decInvestmentLongDao.deleteInvesmentDataByType(currentProjectId,currentTeamId,currentStageId,type[3]);
                decInvestmentLongDao.insert(decInvestmentLongVO);
            }
            if (addDTO.getBond_e_4()!=null){
                DecInvestmentLongEntity decInvestmentLongVO = SmartBeanUtil.copy(base, DecInvestmentLongEntity.class);
                decInvestmentLongVO.setBondType(type[4]);
                decInvestmentLongVO.setBuySell(addDTO.getBond_e_4());
                decInvestmentLongDao.deleteInvesmentDataByType(currentProjectId,currentTeamId,currentStageId,type[4]);
                decInvestmentLongDao.insert(decInvestmentLongVO);
            }
        }
        if (currentStageId ==6 ){
            if(addDTO.getShares5()!=null){
                DecInvestmentLongEntity decInvestmentLongVO = SmartBeanUtil.copy(base, DecInvestmentLongEntity.class);
                decInvestmentLongVO.setBondType(type[6]);
                decInvestmentLongVO.setBuySell(addDTO.getShares5());
                decInvestmentLongDao.deleteInvesmentDataByType(currentProjectId,currentTeamId,currentStageId,type[6]);
                decInvestmentLongDao.insert(decInvestmentLongVO);
            }
            if (addDTO.getBond_a_5()!=null){
                DecInvestmentLongEntity decInvestmentLongVO = SmartBeanUtil.copy(base, DecInvestmentLongEntity.class);
                decInvestmentLongVO.setBondType(type[0]);
                decInvestmentLongVO.setBuySell(addDTO.getBond_a_5());
                decInvestmentLongDao.deleteInvesmentDataByType(currentProjectId,currentTeamId,currentStageId,type[0]);
                decInvestmentLongDao.insert(decInvestmentLongVO);
            }
            if (addDTO.getBond_b_5()!=null){
                DecInvestmentLongEntity decInvestmentLongVO = SmartBeanUtil.copy(base, DecInvestmentLongEntity.class);
                decInvestmentLongVO.setBondType(type[1]);
                decInvestmentLongVO.setBuySell(addDTO.getBond_b_5());
                decInvestmentLongDao.deleteInvesmentDataByType(currentProjectId,currentTeamId,currentStageId,type[1]);
                decInvestmentLongDao.insert(decInvestmentLongVO);
            }
            if (addDTO.getBond_c_5()!=null){
                DecInvestmentLongEntity decInvestmentLongVO = SmartBeanUtil.copy(base, DecInvestmentLongEntity.class);
                decInvestmentLongVO.setBondType(type[2]);
                decInvestmentLongVO.setBuySell(addDTO.getBond_c_5());
                decInvestmentLongDao.deleteInvesmentDataByType(currentProjectId,currentTeamId,currentStageId,type[2]);
                decInvestmentLongDao.insert(decInvestmentLongVO);
            }
            if (addDTO.getBond_d_5()!=null){
                DecInvestmentLongEntity decInvestmentLongVO = SmartBeanUtil.copy(base, DecInvestmentLongEntity.class);
                decInvestmentLongVO.setBondType(type[3]);
                decInvestmentLongVO.setBuySell(addDTO.getBond_d_5());
                decInvestmentLongDao.deleteInvesmentDataByType(currentProjectId,currentTeamId,currentStageId,type[3]);
                decInvestmentLongDao.insert(decInvestmentLongVO);
            }
            if (addDTO.getBond_e_5()!=null){
                DecInvestmentLongEntity decInvestmentLongVO = SmartBeanUtil.copy(base, DecInvestmentLongEntity.class);
                decInvestmentLongVO.setBondType(type[4]);
                decInvestmentLongVO.setBuySell(addDTO.getBond_e_5());
                decInvestmentLongDao.deleteInvesmentDataByType(currentProjectId,currentTeamId,currentStageId,type[4]);
                decInvestmentLongDao.insert(decInvestmentLongVO);
            }
            if (addDTO.getBond_f_5()!=null){
                DecInvestmentLongEntity decInvestmentLongVO = SmartBeanUtil.copy(base, DecInvestmentLongEntity.class);
                decInvestmentLongVO.setBondType(type[5]);
                decInvestmentLongVO.setBuySell(addDTO.getBond_f_5());
                decInvestmentLongDao.deleteInvesmentDataByType(currentProjectId,currentTeamId,currentStageId,type[5]);
                decInvestmentLongDao.insert(decInvestmentLongVO);
            }
        }
//        if (addDTO.getBond_a_1()!=null){
//            DecInvestmentLongEntity decInvestmentLongVO = SmartBeanUtil.copy(base, DecInvestmentLongEntity.class);
//            decInvestmentLongVO.setBondType(type[0]);
//            decInvestmentLongVO.setBuySell(addDTO.getBond_a_1());
//            decInvestmentLongDao.insert(decInvestmentLongVO);
//        }
//        if (addDTO.getBond_b_1()!=null){
//            DecInvestmentLongEntity decInvestmentLongVO = SmartBeanUtil.copy(base, DecInvestmentLongEntity.class);
//            decInvestmentLongVO.setBondType(type[1]);
//            decInvestmentLongVO.setBuySell(addDTO.getBond_b_1());
//            decInvestmentLongDao.insert(decInvestmentLongVO);
//        }
        return ResponseDTO.succ();
    }

    public ResponseDTO<List<DecInvestmentLongVO>> queryAllLongInvestment(Long currentProjectId, Long currentTeamId) {
        return ResponseDTO.succData(decInvestmentLongDao.queryAllLongInvestment(currentProjectId,currentTeamId));
    }
}
