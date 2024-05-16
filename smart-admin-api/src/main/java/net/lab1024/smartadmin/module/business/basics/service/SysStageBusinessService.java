package net.lab1024.smartadmin.module.business.basics.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.basics.dao.SysStageBusinessDao;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysStageBusinessAddDTO;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysStageBusinessUpdateDTO;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysStageBusinessQueryDTO;
import net.lab1024.smartadmin.module.business.basics.domain.entity.SysStageBusinessEntity;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysStageBusinessVO;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysStageBusinessExcelVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [ 阶段业务名称 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-30 09:36:32
 * @since JDK1.8
 */
@Service
public class SysStageBusinessService {

    @Autowired
    private SysStageBusinessDao sysStageBusinessDao;

    /**
     * 根据id查询
     */
    public SysStageBusinessEntity getById(Long id){
        return  sysStageBusinessDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-11-30 09:36:32
     */
    public ResponseDTO<PageResultDTO<SysStageBusinessVO>> queryByPage(SysStageBusinessQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<SysStageBusinessVO> voList = sysStageBusinessDao.queryByPage(page, queryDTO);
        PageResultDTO<SysStageBusinessVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-11-30 09:36:32
     */
    public ResponseDTO<String> add(SysStageBusinessAddDTO addDTO) {
        SysStageBusinessEntity entity = SmartBeanUtil.copy(addDTO, SysStageBusinessEntity.class);
        sysStageBusinessDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-11-30 09:36:32
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(SysStageBusinessUpdateDTO updateDTO) {
        SysStageBusinessEntity entity = SmartBeanUtil.copy(updateDTO, SysStageBusinessEntity.class);
        sysStageBusinessDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-11-30 09:36:32
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        sysStageBusinessDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-11-30 09:36:32
     */
    public List<SysStageBusinessExcelVO> queryAllExportData(SysStageBusinessQueryDTO queryDTO) {
        return sysStageBusinessDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-11-30 09:36:32
     */
    public List<SysStageBusinessExcelVO> queryBatchExportData(List<Long> idList) {
        return sysStageBusinessDao.queryBatchExportData(idList);
    }

    public ResponseDTO<SysStageBusinessVO> selectBusiness(Long stageId) {
            SysStageBusinessVO sysStageBusinessVO = sysStageBusinessDao.selectBusiness(stageId);
        return ResponseDTO.succData(sysStageBusinessVO);
    }
}
