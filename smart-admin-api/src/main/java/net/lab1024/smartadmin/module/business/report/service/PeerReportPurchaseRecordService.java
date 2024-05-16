package net.lab1024.smartadmin.module.business.report.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.exception.SmartResponseCodeException;
import net.lab1024.smartadmin.module.business.basics.dao.SysProjectDao;
import net.lab1024.smartadmin.module.business.basics.dao.SysProjectStageDao;
import net.lab1024.smartadmin.module.business.basics.dao.SysProjectTeamStageDao;
import net.lab1024.smartadmin.module.business.basics.dao.SysStageDao;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysProjectAddDTO;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysProjectQueryDTO;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysProjectUpdateDTO;
import net.lab1024.smartadmin.module.business.basics.domain.entity.SysProjectEntity;
import net.lab1024.smartadmin.module.business.basics.domain.entity.SysProjectTeamStageEntity;
import net.lab1024.smartadmin.module.business.basics.domain.entity.SysStageEntity;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysProjectExcelVO;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysProjectStageVO;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysProjectTeamStageVO;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysProjectVO;
import net.lab1024.smartadmin.module.business.report.constant.PeerReportPurchaseRecordResponseCodeConst;
import net.lab1024.smartadmin.module.business.report.dao.*;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepAgencyBondAddDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepPeerReportPurchaseDTO;
import net.lab1024.smartadmin.module.business.report.domain.entity.RepAgencyBondEntity;
import net.lab1024.smartadmin.module.business.report.domain.entity.RepBalanceSheetEntity;
import net.lab1024.smartadmin.module.business.report.domain.entity.RepPeerReportPurchaseEntity;
import net.lab1024.smartadmin.module.business.report.domain.vo.*;
import net.lab1024.smartadmin.module.system.department.DepartmentDao;
import net.lab1024.smartadmin.module.system.department.domain.dto.DepartmentVO;
import net.lab1024.smartadmin.module.system.department.domain.entity.DepartmentEntity;
import net.lab1024.smartadmin.module.system.login.domain.LoginDetailVO;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 购买同业合同记录相关
 *
 * @author SMS
 * @version 1.0
 * @company 博明信德科技有限公司
 * @copyright 博明信德科技有限公司
 * @date 2021-12-23 09:08:37
 * @since JDK1.8
 */
@Service
public class PeerReportPurchaseRecordService {

    @Autowired
    private SysProjectDao sysProjectDao;
    @Autowired
    private SysProjectTeamStageDao sysProjectTeamStageDao;
    @Autowired
    private SysStageDao sysStageDao;
    @Autowired
    private SysProjectStageDao sysProjectStageDao;
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private RepPeerReportPurchaseDao repPeerReportPurchaseDao;
    @Autowired
    private RepBalanceSheetDao repBalanceSheetDao;
    @Autowired
    private RepProfitDao repProfitDao;
    @Autowired
    private RepLiquidityCashDao repLiquidityCashDao;
    @Autowired
    private RepInvestbusOperatDataStockDao repInvestbusOperatDataStockDao;
    @Autowired
    private RepInvestbusOperatDataBondDao repInvestbusOperatDataBondDao;
    @Autowired
    private RepAgencyBondDao repAgencyBondDao;
    @Autowired
    private RepPeopleLogisticsDao repPeopleLogisticsDao;
    @Autowired
    private RepPersonnelLogisticsDataAutomationDao repPersonnelLogisticsDataAutomationDao;

    public Map<String, Object> queryAllDepartmentAndStage(Long projectId, Long userId, HttpServletRequest request) {
        //数据封装
        Map<String,Object> result = new HashMap<String,Object>();
        //查询项目下所有团队
        List<DepartmentVO>  departmentVOList = departmentDao.queryDeptListByProId(projectId);
        Map<String,DepartmentVO> teamStages = new HashMap<>();
        for(DepartmentVO departmentVO : departmentVOList){
            Long teamId = departmentVO.getId();
            SysProjectStageVO sysProjectStageVO = new SysProjectStageVO();
            sysProjectStageVO.setProId(projectId);
            sysProjectStageVO.setStatus(2);//2：已经结束的
            List<SysProjectStageVO> stageVos = sysProjectStageDao.querySysProjectEndStage(sysProjectStageVO);
            List<SysProjectStageVO> stages = new ArrayList<SysProjectStageVO>();
            for(int i=0;i<stageVos.size();i++){
                SysProjectStageVO stageVo = stageVos.get(i);
                stageVo.setCheckValue(teamId+"A"+stageVo.getStageId());
                stages.add(stageVo);
            }
            departmentVO.setStages(stages);
            teamStages.put(teamId.toString(),departmentVO);
        }
        result.put("teamStages",teamStages);
        result.put("buyList",this.queryByUserId(userId,request));
        return result;
    }
    /**
     * 添加购买同业报告记录
     * @author SMS
     * @date 2021-12-23 12:46:28
     */
    public ResponseDTO<String> saveTeamsPeerReportRecord(String projectId,String teamId,List checkVal,HttpServletRequest request) {
        if(null==checkVal || checkVal.size()==0){
            ResponseDTO.wrap(PeerReportPurchaseRecordResponseCodeConst.STAGE_NOT_EXISTS);
        }
        if(SmartStringUtil.isEmpty(projectId)){
            ResponseDTO.wrap(PeerReportPurchaseRecordResponseCodeConst.ERROR_PARAM);
        }
        if(SmartStringUtil.isEmpty(teamId)){
            ResponseDTO.wrap(PeerReportPurchaseRecordResponseCodeConst.ERROR_PARAM);
        }
        LoginDetailVO vo = (LoginDetailVO)request.getSession().getAttribute("app_user_login");
        if(null==vo){
            ResponseDTO.wrap(PeerReportPurchaseRecordResponseCodeConst.ERROR_PARAM);
        }
        int count = 0;
        for(int i=0;i<checkVal.size();i++){
            String teamIdAndStageId = (String) checkVal.get(i);
            if(SmartStringUtil.isEmpty(teamIdAndStageId)){
                ResponseDTO.wrap(PeerReportPurchaseRecordResponseCodeConst.ERROR_PARAM);
            }
            String[] teamIdAndStageIdArr = teamIdAndStageId.split("A");
            if(teamIdAndStageIdArr.length!=2){
                ResponseDTO.wrap(PeerReportPurchaseRecordResponseCodeConst.ERROR_PARAM);
            }
            String teamId1 = teamIdAndStageIdArr[0];
            String stageId1 = teamIdAndStageIdArr[1];
            if(teamId.equals(teamId1)){
                count++;
                RepPeerReportPurchaseDTO dto = new RepPeerReportPurchaseDTO();
                dto.setProjectId(Long.parseLong(projectId));
                dto.setTeamId(Long.parseLong(teamId));
                dto.setStageId(Long.parseLong(stageId1));
                dto.setUserId(vo.getId());
                dto.setUserName(vo.getLoginName());
                DepartmentEntity team = departmentDao.selectById(teamId);
                dto.setTeamName(team.getName());
                SysStageEntity stage = sysStageDao.selectById(Long.parseLong(stageId1));
                dto.setStage(stage.getStageName());
                SysProjectEntity project = sysProjectDao.selectById(projectId);
                dto.setProject(project.getProName());
                RepPeerReportPurchaseEntity entity = SmartBeanUtil.copy(dto, RepPeerReportPurchaseEntity.class);
                repPeerReportPurchaseDao.insert(entity);
            }
        }
        if(count==0){
            ResponseDTO.wrap(PeerReportPurchaseRecordResponseCodeConst.STAGE_NOT_EXISTS);
        }
        return ResponseDTO.succ();
    }
    private List<RepPeerReportPurchaseEntity> queryByUserId(Long userId,HttpServletRequest request){
        List<RepPeerReportPurchaseEntity> repPeerReportPurchaseList = new ArrayList<RepPeerReportPurchaseEntity>();
        Map map = new HashMap();
        map.put("user_id",userId);
        repPeerReportPurchaseList = repPeerReportPurchaseDao.selectByMap(map);
        for(RepPeerReportPurchaseEntity vo : repPeerReportPurchaseList){
            Long projectId = vo.getProjectId();
            Long teamId = vo.getTeamId();
            Long stageId = vo.getStageId();
            String url = request.getContextPath()+"/service/bg_cdk?bgProjectId="+projectId+"&bgTeamId="+teamId+"&bgStageId="+stageId;
            vo.setToSeeUrl(url);
        }
        return  repPeerReportPurchaseList;
    }

    /**
     * 查询资产负债结果表
     * @param projectId
     * @param teamId
     * @param stageId
     * @return
     */
    public ResponseDTO<List<Map<Long, RepBalanceSheetVO>>> selectBalanceResultBg(Long projectId, Long teamId, Long stageId) {

            List<Map<Long,RepBalanceSheetVO>> repBalanceSheetVOList = new ArrayList<Map<Long, RepBalanceSheetVO>>();

            Map<Long,RepBalanceSheetVO> map = new HashMap<>();

            RepBalanceSheetVO repBalanceSheetVO = null;

            repBalanceSheetVO = repBalanceSheetDao.query(projectId,teamId, stageId);

            map.put(stageId,repBalanceSheetVO);

            repBalanceSheetVOList.add(map);
            return ResponseDTO.succData(repBalanceSheetVOList);
    }
    /**
     * 查询利润结果表
     * @param projectId
     * @param teamId
     * @param stageId
     * @return
     * @author wz
     */
    public ResponseDTO<List<Map<Long, RepProfitVO>>> selectProfitBg(Long projectId, Long teamId, Long stageId) {
        List<Map<Long, RepProfitVO>> repProfitVOList = new ArrayList<>();
        Map<Long, RepProfitVO> map = new HashMap<>();
        RepProfitVO repProfitVO = null;
        repProfitVO =  repProfitDao.query(projectId,teamId,stageId);
        map.put(stageId, repProfitVO);
        repProfitVOList.add(map);
        return ResponseDTO.succData(repProfitVOList);
    }
    /**
     * 将流动性报表-现金返回到页面
     * @param projectId
     * @param teamId
     * @param stageId
     * @return
     */
    public ResponseDTO<RepLiquidityCashVO> selectLiquidityCashBg(Long projectId, Long teamId, Long stageId) {

        RepLiquidityCashVO repLiquidityCashVO = new RepLiquidityCashVO();
        repLiquidityCashVO = repLiquidityCashDao.query(projectId,teamId, stageId);
        return ResponseDTO.succData(repLiquidityCashVO);
    }
    public ResponseDTO<Map<String,Map<String, RepInvestbusOperatDataStockVO>>> selectInvestbusOperatDataStockBg(Long projectId, Long teamId, Long stageId) {

        Map<String,Map<String, RepInvestbusOperatDataStockVO>> map =new HashMap<>();
        List<RepInvestbusOperatDataStockVO>  repInvestbusOperatDataStockVOList = repInvestbusOperatDataStockDao.query(projectId,teamId,stageId,"短期");
        Map<String,RepInvestbusOperatDataStockVO> map1 =new HashMap<>();
        for (RepInvestbusOperatDataStockVO repInvestbusOperatDataStockVO:repInvestbusOperatDataStockVOList) {
            map1.put(repInvestbusOperatDataStockVO.getStockProject(),repInvestbusOperatDataStockVO);
            map.put(repInvestbusOperatDataStockVO.getStockType(),map1);
        }
        List<RepInvestbusOperatDataStockVO>  repInvestbusOperatDataStockVOList1 = repInvestbusOperatDataStockDao.query(projectId,teamId,stageId,"长期");
        Map<String,RepInvestbusOperatDataStockVO> map2 =new HashMap<>();
        for (RepInvestbusOperatDataStockVO repInvestbusOperatDataStockVO:repInvestbusOperatDataStockVOList1) {
            map2.put(repInvestbusOperatDataStockVO.getStockProject(),repInvestbusOperatDataStockVO);
            map.put(repInvestbusOperatDataStockVO.getStockType(),map2);
        }
        return ResponseDTO.succData(map);
    }
    public ResponseDTO<Map<String, Map<String, RepInvestbusOperatDataBondVO>>> selectInvestbusOperatDataBondBg(Long projectId, Long teamId, Long stageId) {
        Map<String,Map<String, RepInvestbusOperatDataBondVO>> map =new HashMap<>();
        List<RepInvestbusOperatDataBondVO> repInvestbusOperatDataBondVOList = repInvestbusOperatDataBondDao.query(projectId,teamId,stageId,"短期");
        Map<String,RepInvestbusOperatDataBondVO> map1 =new HashMap<>();
        for (RepInvestbusOperatDataBondVO repInvestbusOperatDataBondVO:repInvestbusOperatDataBondVOList) {
            map1.put(repInvestbusOperatDataBondVO.getBondKind(),repInvestbusOperatDataBondVO);
            map.put(repInvestbusOperatDataBondVO.getBondType(),map1);
        }
        Map<String,RepInvestbusOperatDataBondVO> map2 =new HashMap<>();
        List<RepInvestbusOperatDataBondVO> repInvestbusOperatDataBondVOList1 = repInvestbusOperatDataBondDao.query(projectId,teamId,stageId,"长期");
        for (RepInvestbusOperatDataBondVO repInvestbusOperatDataBondVO:repInvestbusOperatDataBondVOList1) {
            map2.put(repInvestbusOperatDataBondVO.getBondKind(),repInvestbusOperatDataBondVO);
            map.put(repInvestbusOperatDataBondVO.getBondType(),map2);
        }
        return ResponseDTO.succData(map);
    }
    /**
     *
     * @param projectId
     * @param teamId
     * @param stageId
     * @return
     */
    public ResponseDTO<List<RepAgencyBondVO>> selectAgencyBondAllBg(Long projectId, Long teamId, Long stageId) {
        List<RepAgencyBondVO> repAgencyBondVOS = new ArrayList<RepAgencyBondVO>();

        repAgencyBondVOS = repAgencyBondDao.query(projectId,null,stageId);

        return ResponseDTO.succData(repAgencyBondVOS);
    }
    /**
     * 查询人事后勤结果表和参数表
     * @param projectId
     * @param teamId
     * @param stageId
     * @return
     */
    public ResponseDTO<Map<String, RepPeopleLogisticsVO>> selectPeopleLogisticsBg(Long projectId, Long teamId, Long stageId) {
        Map<String, RepPeopleLogisticsVO> map = new HashMap<>();
        List<RepPeopleLogisticsVO> repPeopleLogisticsVOs = repPeopleLogisticsDao.query(projectId,teamId,stageId);
        for (RepPeopleLogisticsVO repPeopleLogisticsVO:repPeopleLogisticsVOs ) {
            map.put(repPeopleLogisticsVO.getBusinessType(), repPeopleLogisticsVO);
        }
        return ResponseDTO.succData(map);
    }
    public ResponseDTO<RepPersonnelLogisticsDataAutomationVO> selectPerLogDataAutomationBg(Long projectId, Long teamId, Long stageId) {
        RepPersonnelLogisticsDataAutomationVO repPerLogDataAutomationVO = null;
        repPerLogDataAutomationVO = repPersonnelLogisticsDataAutomationDao.query(projectId,teamId,stageId);
        System.out.println(repPerLogDataAutomationVO);
        return ResponseDTO.succData(repPerLogDataAutomationVO);
    }

}
