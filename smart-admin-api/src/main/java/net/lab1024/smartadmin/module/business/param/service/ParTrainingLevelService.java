package net.lab1024.smartadmin.module.business.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.param.dao.ParTrainingLevelDao;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParTrainingLevelAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParTrainingLevelUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParTrainingLevelQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParTrainingLevelEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParTrainingLevelVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParTrainingLevelExcelVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [ 培训水平 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-09 14:52:40
 * @since JDK1.8
 */
@Service
public class ParTrainingLevelService {

    @Autowired
    private ParTrainingLevelDao parTrainingLevelDao;

    /**
     * 根据id查询
     */
    public ParTrainingLevelEntity getById(Long id){
        return  parTrainingLevelDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-12-09 14:52:40
     */
    public ResponseDTO<PageResultDTO<ParTrainingLevelVO>> queryByPage(ParTrainingLevelQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ParTrainingLevelVO> voList = parTrainingLevelDao.queryByPage(page, queryDTO);
        PageResultDTO<ParTrainingLevelVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-12-09 14:52:40
     */
    public ResponseDTO<String> add(ParTrainingLevelAddDTO addDTO) {
        ParTrainingLevelEntity entity = SmartBeanUtil.copy(addDTO, ParTrainingLevelEntity.class);
        parTrainingLevelDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-12-09 14:52:40
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ParTrainingLevelUpdateDTO updateDTO) {
        ParTrainingLevelEntity entity = SmartBeanUtil.copy(updateDTO, ParTrainingLevelEntity.class);
        parTrainingLevelDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-12-09 14:52:40
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        parTrainingLevelDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-12-09 14:52:40
     */
    public List<ParTrainingLevelExcelVO> queryAllExportData(ParTrainingLevelQueryDTO queryDTO) {
        return parTrainingLevelDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-12-09 14:52:40
     */
    public List<ParTrainingLevelExcelVO> queryBatchExportData(List<Long> idList) {
        return parTrainingLevelDao.queryBatchExportData(idList);
    }
}
