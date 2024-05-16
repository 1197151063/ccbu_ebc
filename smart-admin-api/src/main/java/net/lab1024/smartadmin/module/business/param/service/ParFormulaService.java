package net.lab1024.smartadmin.module.business.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.param.dao.ParFormulaDao;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParFormulaAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParFormulaUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParFormulaQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParFormulaEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParFormulaVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParFormulaExcelVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [ 存贷款计算公式表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-07 14:42:04
 * @since JDK1.8
 */
@Service
public class ParFormulaService {

    @Autowired
    private ParFormulaDao parFormulaDao;

    /**
     * 根据id查询
     */
    public ParFormulaEntity getById(Long id){
        return  parFormulaDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-12-07 14:42:04
     */
    public ResponseDTO<PageResultDTO<ParFormulaVO>> queryByPage(ParFormulaQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ParFormulaVO> voList = parFormulaDao.queryByPage(page, queryDTO);
        PageResultDTO<ParFormulaVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-12-07 14:42:04
     */
    public ResponseDTO<String> add(ParFormulaAddDTO addDTO) {
        ParFormulaEntity entity = SmartBeanUtil.copy(addDTO, ParFormulaEntity.class);
        parFormulaDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-12-07 14:42:04
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ParFormulaUpdateDTO updateDTO) {
        ParFormulaEntity entity = SmartBeanUtil.copy(updateDTO, ParFormulaEntity.class);
        parFormulaDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-12-07 14:42:04
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        parFormulaDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-12-07 14:42:04
     */
    public List<ParFormulaExcelVO> queryAllExportData(ParFormulaQueryDTO queryDTO) {
        return parFormulaDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-12-07 14:42:04
     */
    public List<ParFormulaExcelVO> queryBatchExportData(List<Long> idList) {
        return parFormulaDao.queryBatchExportData(idList);
    }
}
