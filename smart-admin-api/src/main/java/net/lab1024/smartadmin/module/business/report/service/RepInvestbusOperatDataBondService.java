package net.lab1024.smartadmin.module.business.report.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.report.dao.RepInvestbusOperatDataBondDao;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepInvestbusOperatDataBondAddDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepInvestbusOperatDataBondQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepInvestbusOperatDataBondUpdateDTO;
import net.lab1024.smartadmin.module.business.report.domain.entity.RepInvestbusOperatDataBondEntity;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepInvestbusOperatDataBondExcelVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepInvestbusOperatDataBondVO;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import net.lab1024.smartadmin.util.SmartPageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [ 投资业务营业数据表(债券) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-24 18:22:46
 * @since JDK1.8
 */
@Service
public class RepInvestbusOperatDataBondService {

    @Autowired
    private RepInvestbusOperatDataBondDao repInvestbusOperatDataBondDao;

    /**
     * 根据id查询
     */
    public RepInvestbusOperatDataBondEntity getById(Long id){
        return  repInvestbusOperatDataBondDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-11-24 18:22:46
     */
    public ResponseDTO<PageResultDTO<RepInvestbusOperatDataBondVO>> queryByPage(RepInvestbusOperatDataBondQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<RepInvestbusOperatDataBondVO> voList = repInvestbusOperatDataBondDao.queryByPage(page, queryDTO);
        PageResultDTO<RepInvestbusOperatDataBondVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-11-24 18:22:46
     */
    public ResponseDTO<String> add(RepInvestbusOperatDataBondAddDTO addDTO) {
        RepInvestbusOperatDataBondEntity entity = SmartBeanUtil.copy(addDTO, RepInvestbusOperatDataBondEntity.class);
        repInvestbusOperatDataBondDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-11-24 18:22:46
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(RepInvestbusOperatDataBondUpdateDTO updateDTO) {
        RepInvestbusOperatDataBondEntity entity = SmartBeanUtil.copy(updateDTO, RepInvestbusOperatDataBondEntity.class);
        repInvestbusOperatDataBondDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-11-24 18:22:46
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        repInvestbusOperatDataBondDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-11-24 18:22:46
     */
    public List<RepInvestbusOperatDataBondExcelVO> queryAllExportData(RepInvestbusOperatDataBondQueryDTO queryDTO) {
        return repInvestbusOperatDataBondDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-11-24 18:22:46
     */
    public List<RepInvestbusOperatDataBondExcelVO> queryBatchExportData(List<Long> idList) {
        return repInvestbusOperatDataBondDao.queryBatchExportData(idList);
    }

    public ResponseDTO<Map<String, Map<String, RepInvestbusOperatDataBondVO>>> selectInvestbusOperatDataBond(Long currentTeamId, Long currentStageId) {
        Map<String,Map<String, RepInvestbusOperatDataBondVO>> map =new HashMap<>();

        if (currentStageId <= 1 ){
            List<RepInvestbusOperatDataBondVO> repInvestbusOperatDataBondVOList = repInvestbusOperatDataBondDao.selectInvestbusOperatDataBondPar(1L,"短期");
            Map<String,RepInvestbusOperatDataBondVO> map1 =new HashMap<>();
            for (RepInvestbusOperatDataBondVO repInvestbusOperatDataBondVO:repInvestbusOperatDataBondVOList) {

                map1.put(repInvestbusOperatDataBondVO.getBondKind(),repInvestbusOperatDataBondVO);
                map.put(repInvestbusOperatDataBondVO.getBondType(),map1);
            }
            Map<String,RepInvestbusOperatDataBondVO> map2 =new HashMap<>();
            List<RepInvestbusOperatDataBondVO> repInvestbusOperatDataBondVOList1 = repInvestbusOperatDataBondDao.selectInvestbusOperatDataBondPar(1L,"长期");
            for (RepInvestbusOperatDataBondVO repInvestbusOperatDataBondVO:repInvestbusOperatDataBondVOList1) {
                map2.put(repInvestbusOperatDataBondVO.getBondKind(),repInvestbusOperatDataBondVO);
                map.put(repInvestbusOperatDataBondVO.getBondType(),map2);
            }
        } else {
            List<RepInvestbusOperatDataBondVO> repInvestbusOperatDataBondVOList = repInvestbusOperatDataBondDao.selectInvestbusOperatDataBondRep(currentTeamId,currentStageId,"短期");
            Map<String,RepInvestbusOperatDataBondVO> map1 =new HashMap<>();
            for (RepInvestbusOperatDataBondVO repInvestbusOperatDataBondVO:repInvestbusOperatDataBondVOList) {
                map1.put(repInvestbusOperatDataBondVO.getBondKind(),repInvestbusOperatDataBondVO);
                map.put(repInvestbusOperatDataBondVO.getBondType(),map1);
            }
            Map<String,RepInvestbusOperatDataBondVO> map2 =new HashMap<>();
            List<RepInvestbusOperatDataBondVO> repInvestbusOperatDataBondVOList1 = repInvestbusOperatDataBondDao.selectInvestbusOperatDataBondRep(currentTeamId,currentStageId,"长期");
            for (RepInvestbusOperatDataBondVO repInvestbusOperatDataBondVO:repInvestbusOperatDataBondVOList1) {
                map2.put(repInvestbusOperatDataBondVO.getBondKind(),repInvestbusOperatDataBondVO);
                map.put(repInvestbusOperatDataBondVO.getBondType(),map2);
            }
        }
        return ResponseDTO.succData(map);
    }

    public List<RepInvestbusOperatDataBondExcelVO> queryExportData(Long proId, Long stageId) {
        return repInvestbusOperatDataBondDao.queryExportData(proId,stageId);
    }
}
