package net.lab1024.smartadmin.module.business.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.param.dao.ParIndexNumberDao;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParIndexNumberAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParIndexNumberUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParIndexNumberQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParIndexNumberEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParIndexNumberVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParIndexNumberExcelVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [ 自动化投资指数 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-09 18:42:39
 * @since JDK1.8
 */
@Service
public class ParIndexNumberService {

    @Autowired
    private ParIndexNumberDao parIndexNumberDao;

    /**
     * 根据id查询
     */
    public ParIndexNumberEntity getById(Long id){
        return  parIndexNumberDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-12-09 18:42:39
     */
    public ResponseDTO<PageResultDTO<ParIndexNumberVO>> queryByPage(ParIndexNumberQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ParIndexNumberVO> voList = parIndexNumberDao.queryByPage(page, queryDTO);
        PageResultDTO<ParIndexNumberVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-12-09 18:42:39
     */
    public ResponseDTO<String> add(ParIndexNumberAddDTO addDTO) {
        ParIndexNumberEntity entity = SmartBeanUtil.copy(addDTO, ParIndexNumberEntity.class);
        parIndexNumberDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-12-09 18:42:39
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ParIndexNumberUpdateDTO updateDTO) {
        ParIndexNumberEntity entity = SmartBeanUtil.copy(updateDTO, ParIndexNumberEntity.class);
        parIndexNumberDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-12-09 18:42:39
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        parIndexNumberDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-12-09 18:42:39
     */
    public List<ParIndexNumberExcelVO> queryAllExportData(ParIndexNumberQueryDTO queryDTO) {
        return parIndexNumberDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-12-09 18:42:39
     */
    public List<ParIndexNumberExcelVO> queryBatchExportData(List<Long> idList) {
        return parIndexNumberDao.queryBatchExportData(idList);
    }
}
