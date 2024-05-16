package net.lab1024.smartadmin.module.business.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.param.dao.ParBondParameterDao;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParBondParameterAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParBondParameterUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParBondParameterQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParBondParameterEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParBondParameterVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParBondParameterExcelVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [ 债券参数表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 13:42:33
 * @since JDK1.8
 */
@Service
public class ParBondParameterService {

    @Autowired
    private ParBondParameterDao parBondParameterDao;

    /**
     * 根据id查询
     */
    public ParBondParameterEntity getById(Long id){
        return  parBondParameterDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-11-25 13:42:33
     */
    public ResponseDTO<PageResultDTO<ParBondParameterVO>> queryByPage(ParBondParameterQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ParBondParameterVO> voList = parBondParameterDao.queryByPage(page, queryDTO);
        PageResultDTO<ParBondParameterVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-11-25 13:42:33
     */
    public ResponseDTO<String> add(ParBondParameterAddDTO addDTO) {
        ParBondParameterEntity entity = SmartBeanUtil.copy(addDTO, ParBondParameterEntity.class);
        parBondParameterDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-11-25 13:42:33
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ParBondParameterUpdateDTO updateDTO) {
        ParBondParameterEntity entity = SmartBeanUtil.copy(updateDTO, ParBondParameterEntity.class);
        parBondParameterDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-11-25 13:42:33
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        parBondParameterDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-11-25 13:42:33
     */
    public List<ParBondParameterExcelVO> queryAllExportData(ParBondParameterQueryDTO queryDTO) {
        return parBondParameterDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-11-25 13:42:33
     */
    public List<ParBondParameterExcelVO> queryBatchExportData(List<Long> idList) {
        return parBondParameterDao.queryBatchExportData(idList);
    }
}
