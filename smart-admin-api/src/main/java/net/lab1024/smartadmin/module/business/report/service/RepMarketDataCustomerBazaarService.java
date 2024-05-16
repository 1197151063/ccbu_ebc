package net.lab1024.smartadmin.module.business.report.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.report.dao.RepMarketDataCustomerBazaarDao;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepMarketDataCustomerBazaarAddDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepMarketDataCustomerBazaarQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepMarketDataCustomerBazaarUpdateDTO;
import net.lab1024.smartadmin.module.business.report.domain.entity.RepMarketDataCustomerBazaarEntity;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepMarketDataCustomerBazaarExcelVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepMarketDataCustomerBazaarVO;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import net.lab1024.smartadmin.util.SmartPageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * [ 市场营销数据(客户市场)单位：百万元 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 11:26:24
 * @since JDK1.8
 */
@Service
public class RepMarketDataCustomerBazaarService {

    @Autowired
    private RepMarketDataCustomerBazaarDao repMarketDataCustomerBazaarDao;

    /**
     * 根据id查询
     */
    public RepMarketDataCustomerBazaarEntity getById(Long id){
        return  repMarketDataCustomerBazaarDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-11-25 11:26:24
     */
    public ResponseDTO<PageResultDTO<RepMarketDataCustomerBazaarVO>> queryByPage(RepMarketDataCustomerBazaarQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<RepMarketDataCustomerBazaarVO> voList = repMarketDataCustomerBazaarDao.queryByPage(page, queryDTO);
        PageResultDTO<RepMarketDataCustomerBazaarVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-11-25 11:26:24
     */
    public ResponseDTO<String> add(RepMarketDataCustomerBazaarAddDTO addDTO) {
        RepMarketDataCustomerBazaarEntity entity = SmartBeanUtil.copy(addDTO, RepMarketDataCustomerBazaarEntity.class);
        repMarketDataCustomerBazaarDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-11-25 11:26:24
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(RepMarketDataCustomerBazaarUpdateDTO updateDTO) {
        RepMarketDataCustomerBazaarEntity entity = SmartBeanUtil.copy(updateDTO, RepMarketDataCustomerBazaarEntity.class);
        repMarketDataCustomerBazaarDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-11-25 11:26:24
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        repMarketDataCustomerBazaarDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-11-25 11:26:24
     */
    public List<RepMarketDataCustomerBazaarExcelVO> queryAllExportData(RepMarketDataCustomerBazaarQueryDTO queryDTO) {
        return repMarketDataCustomerBazaarDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-11-25 11:26:24
     */
    public List<RepMarketDataCustomerBazaarExcelVO> queryBatchExportData(List<Long> idList) {
        return repMarketDataCustomerBazaarDao.queryBatchExportData(idList);
    }

    public ResponseDTO<List<RepMarketDataCustomerBazaarVO>> selectMarDataCusBaz(Long currentTeamId, Long currentStageId) {
        List<RepMarketDataCustomerBazaarVO> repMarDatCusBazVOList = new ArrayList<>();
            if (currentStageId < 4){
                repMarDatCusBazVOList = repMarketDataCustomerBazaarDao.selectMarDataCusBazPar(currentStageId);
            }else{
            repMarDatCusBazVOList = repMarketDataCustomerBazaarDao.selectMarDataCusBazRep(currentTeamId,currentStageId);
            }
        return ResponseDTO.succData(repMarDatCusBazVOList);
    }

    public List<RepMarketDataCustomerBazaarExcelVO> queryExportData(Long proId, Long stageId) {
        return repMarketDataCustomerBazaarDao.queryExportData(proId,stageId);
    }
}
