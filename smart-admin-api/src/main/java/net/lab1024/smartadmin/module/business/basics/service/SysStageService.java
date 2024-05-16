package net.lab1024.smartadmin.module.business.basics.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.basics.dao.SysStageDao;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysStageAddDTO;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysStageUpdateDTO;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysStageQueryDTO;
import net.lab1024.smartadmin.module.business.basics.domain.entity.SysStageEntity;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysStageVO;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysStageExcelVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [ 阶段表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-22 15:55:16
 * @since JDK1.8
 */
@Service
public class SysStageService {

    @Autowired
    private SysStageDao sysStageDao;

    /**
     * 根据id查询
     */
    public SysStageEntity getById(Long id){
        return  sysStageDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-11-22 15:55:16
     */
    public ResponseDTO<PageResultDTO<SysStageVO>> queryByPage(SysStageQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<SysStageVO> voList = sysStageDao.queryByPage(page, queryDTO);
        PageResultDTO<SysStageVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-11-22 15:55:16
     */
    public ResponseDTO<String> add(SysStageAddDTO addDTO) {
        SysStageEntity entity = SmartBeanUtil.copy(addDTO, SysStageEntity.class);
        sysStageDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-11-22 15:55:16
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(SysStageUpdateDTO updateDTO) {
        SysStageEntity entity = SmartBeanUtil.copy(updateDTO, SysStageEntity.class);
        sysStageDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-11-22 15:55:16
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        sysStageDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-11-22 15:55:16
     */
    public List<SysStageExcelVO> queryAllExportData(SysStageQueryDTO queryDTO) {
        return sysStageDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-11-22 15:55:16
     */
    public List<SysStageExcelVO> queryBatchExportData(List<Long> idList) {
        return sysStageDao.queryBatchExportData(idList);
    }
}
