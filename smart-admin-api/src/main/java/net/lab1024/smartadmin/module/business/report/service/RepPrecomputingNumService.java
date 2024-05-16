package net.lab1024.smartadmin.module.business.report.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.report.dao.RepPrecomputingNumDao;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepPrecomputingNumAddDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepPrecomputingNumUpdateDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepPrecomputingNumQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.entity.RepPrecomputingNumEntity;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepPrecomputingNumVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepPrecomputingNumExcelVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [ 预计算次数表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-27 17:08:27
 * @since JDK1.8
 */
@Service
public class RepPrecomputingNumService {

    @Autowired
    private RepPrecomputingNumDao repPrecomputingNumDao;

    /**
     * 根据id查询
     */
    public RepPrecomputingNumEntity getById(Long id){
        return  repPrecomputingNumDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-12-27 17:08:27
     */
    public ResponseDTO<PageResultDTO<RepPrecomputingNumVO>> queryByPage(RepPrecomputingNumQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<RepPrecomputingNumVO> voList = repPrecomputingNumDao.queryByPage(page, queryDTO);
        PageResultDTO<RepPrecomputingNumVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-12-27 17:08:27
     */
    public ResponseDTO<String> add(RepPrecomputingNumAddDTO addDTO) {
        RepPrecomputingNumEntity entity = SmartBeanUtil.copy(addDTO, RepPrecomputingNumEntity.class);
        repPrecomputingNumDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-12-27 17:08:27
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(RepPrecomputingNumUpdateDTO updateDTO) {
        RepPrecomputingNumEntity entity = SmartBeanUtil.copy(updateDTO, RepPrecomputingNumEntity.class);
        repPrecomputingNumDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-12-27 17:08:27
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        repPrecomputingNumDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-12-27 17:08:27
     */
    public List<RepPrecomputingNumExcelVO> queryAllExportData(RepPrecomputingNumQueryDTO queryDTO) {
        return repPrecomputingNumDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-12-27 17:08:27
     */
    public List<RepPrecomputingNumExcelVO> queryBatchExportData(List<Long> idList) {
        return repPrecomputingNumDao.queryBatchExportData(idList);
    }
}
