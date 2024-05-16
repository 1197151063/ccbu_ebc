package net.lab1024.smartadmin.module.business.echarts;

import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParMarketShareVO;
import net.lab1024.smartadmin.module.business.report.dao.RepBalanceSheetDao;
import net.lab1024.smartadmin.module.business.report.dao.RepProfitDao;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepBalanceSheetVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepProfitVO;
import net.lab1024.smartadmin.module.system.department.domain.dto.DepartmentVO;
import net.lab1024.smartadmin.module.system.employee.EmployeeDao;
import net.lab1024.smartadmin.module.system.employee.domain.dto.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EchartsDatalService {
    @Autowired
    private  EchartsDataDao echartsDataDao;
    @Autowired
    private RepBalanceSheetDao repBalanceSheetDao;
    @Autowired
    private RepProfitDao repProfitDao;
    /**
     * 查询某项目某阶段下某个业务的各银行市场占比
     * @param proId
     * @param stageId
     * @param type
     * @return
     */
    public ResponseDTO<List<ParMarketShareVO>> queryMSByPidAndSid(Long proId, Long stageId, String type) {
        List<ParMarketShareVO> parMarketShareVOList = echartsDataDao.queryMSByPidAndSid(proId, stageId, type);
        return ResponseDTO.succData(parMarketShareVOList);
    }

    /**
     * 查询某个银行某阶段的市场占比
     * @param marketShareVO
     */
    public ResponseDTO<Map<String,ParMarketShareVO>> queryMarketShareByTeam(ParMarketShareVO marketShareVO){
        List<ParMarketShareVO> parMarketShareVOList = echartsDataDao.queryMarketShare(marketShareVO);
        Map<String,ParMarketShareVO> parMarketShareVOMap = new HashMap<>();
        if(parMarketShareVOList!=null && parMarketShareVOList.size()>0){
            for(ParMarketShareVO parMarketShareVO : parMarketShareVOList){
                parMarketShareVOMap.put(parMarketShareVO.getBusiness(),parMarketShareVO);
            }
        }
        return ResponseDTO.succData(parMarketShareVOMap);
    }

    public  List<RepBalanceSheetVO> queryAssestTotalByStageid(Long proId,Long teamId,Long stageId){
       List<RepBalanceSheetVO> list = repBalanceSheetDao.queryTotalAssets(proId, teamId, stageId);
       return list;
    }

    public  List<RepProfitVO> queryProfitByProId(Long proId){
        List<RepProfitVO> list = repProfitDao.selectProfitByProId(proId);
        return list;
    }
}
