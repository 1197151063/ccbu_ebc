package net.lab1024.smartadmin.module.business.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.param.dao.ParAgencyBondIssueDao;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParAgencyBondIssueAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParAgencyBondIssueUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParAgencyBondIssueQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParAgencyBondIssueEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParAgencyBondIssueVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParAgencyBondIssueExcelVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [ 代理债券发行业务信息表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 13:37:59
 * @since JDK1.8
 */
@Service
public class ParAgencyBondIssueService {

    @Autowired
    private ParAgencyBondIssueDao parAgencyBondIssueDao;

    /**
     * 根据id查询
     */
    public ParAgencyBondIssueEntity getById(Long id){
        return  parAgencyBondIssueDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-11-25 13:37:59
     */
    public ResponseDTO<PageResultDTO<ParAgencyBondIssueVO>> queryByPage(ParAgencyBondIssueQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ParAgencyBondIssueVO> voList = parAgencyBondIssueDao.queryByPage(page, queryDTO);
        PageResultDTO<ParAgencyBondIssueVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-11-25 13:37:59
     */
    public ResponseDTO<String> add(ParAgencyBondIssueAddDTO addDTO) {
        ParAgencyBondIssueEntity entity = SmartBeanUtil.copy(addDTO, ParAgencyBondIssueEntity.class);
        parAgencyBondIssueDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-11-25 13:37:59
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ParAgencyBondIssueUpdateDTO updateDTO) {
        ParAgencyBondIssueEntity entity = SmartBeanUtil.copy(updateDTO, ParAgencyBondIssueEntity.class);
        parAgencyBondIssueDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-11-25 13:37:59
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        parAgencyBondIssueDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-11-25 13:37:59
     */
    public List<ParAgencyBondIssueExcelVO> queryAllExportData(ParAgencyBondIssueQueryDTO queryDTO) {
        return parAgencyBondIssueDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-11-25 13:37:59
     */
    public List<ParAgencyBondIssueExcelVO> queryBatchExportData(List<Long> idList) {
        return parAgencyBondIssueDao.queryBatchExportData(idList);
    }
}
