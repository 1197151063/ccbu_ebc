package net.lab1024.smartadmin.module.business.report.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.report.dao.RepMarketDataCustomerAccountDao;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepMarketDataCustomerAccountAddDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepMarketDataCustomerAccountUpdateDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepMarketDataCustomerAccountQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.entity.RepMarketDataCustomerAccountEntity;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepMarketDataCustomerAccountVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepMarketDataCustomerAccountExcelVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * [ 市场营销数据(客户账户) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 11:25:07
 * @since JDK1.8
 */
@Service
public class RepMarketDataCustomerAccountService {

    @Autowired
    private RepMarketDataCustomerAccountDao repMarketDataCustomerAccountDao;

    /**
     * 根据id查询
     */
    public RepMarketDataCustomerAccountEntity getById(Long id){
        return  repMarketDataCustomerAccountDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-11-25 11:25:07
     */
    public ResponseDTO<PageResultDTO<RepMarketDataCustomerAccountVO>> queryByPage(RepMarketDataCustomerAccountQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<RepMarketDataCustomerAccountVO> voList = repMarketDataCustomerAccountDao.queryByPage(page, queryDTO);
        PageResultDTO<RepMarketDataCustomerAccountVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-11-25 11:25:07
     */
    public ResponseDTO<String> add(RepMarketDataCustomerAccountAddDTO addDTO) {
        RepMarketDataCustomerAccountEntity entity = SmartBeanUtil.copy(addDTO, RepMarketDataCustomerAccountEntity.class);
        repMarketDataCustomerAccountDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-11-25 11:25:07
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(RepMarketDataCustomerAccountUpdateDTO updateDTO) {
        RepMarketDataCustomerAccountEntity entity = SmartBeanUtil.copy(updateDTO, RepMarketDataCustomerAccountEntity.class);
        repMarketDataCustomerAccountDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-11-25 11:25:07
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        repMarketDataCustomerAccountDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-11-25 11:25:07
     */
    public List<RepMarketDataCustomerAccountExcelVO> queryAllExportData(RepMarketDataCustomerAccountQueryDTO queryDTO) {
        return repMarketDataCustomerAccountDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-11-25 11:25:07
     */
    public List<RepMarketDataCustomerAccountExcelVO> queryBatchExportData(List<Long> idList) {
        return repMarketDataCustomerAccountDao.queryBatchExportData(idList);
    }

    public ResponseDTO<List<RepMarketDataCustomerAccountVO>> selectMarDatCusAccount(Long currentTeamId, Long currentStageId) {
        List<RepMarketDataCustomerAccountVO> repMarDatCusAccVOList = new ArrayList<>();
            if (currentStageId < 4){
                repMarDatCusAccVOList = repMarketDataCustomerAccountDao.selectMarDatCusAccPar(currentStageId);
            }else{
                repMarDatCusAccVOList = repMarketDataCustomerAccountDao.selectMarDatCusAccRep(currentTeamId,currentStageId);
            }
        return ResponseDTO.succData(repMarDatCusAccVOList);
    }

    public List<RepMarketDataCustomerAccountExcelVO> queryExportData(Long proId, Long stageId) {
        return repMarketDataCustomerAccountDao.queryExportData(proId,stageId);
    }
}
