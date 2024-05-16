package net.lab1024.smartadmin.module.business.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.param.dao.ParGencyBondDiscountDao;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParGencyBondDiscountAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParGencyBondDiscountUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParGencyBondDiscountQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParGencyBondDiscountEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParGencyBondDiscountVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParGencyBondDiscountExcelVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [ 代理债券折扣 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-09 14:44:56
 * @since JDK1.8
 */
@Service
public class ParGencyBondDiscountService {

    @Autowired
    private ParGencyBondDiscountDao parGencyBondDiscountDao;

    /**
     * 根据id查询
     */
    public ParGencyBondDiscountEntity getById(Long id){
        return  parGencyBondDiscountDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-12-09 14:44:56
     */
    public ResponseDTO<PageResultDTO<ParGencyBondDiscountVO>> queryByPage(ParGencyBondDiscountQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ParGencyBondDiscountVO> voList = parGencyBondDiscountDao.queryByPage(page, queryDTO);
        PageResultDTO<ParGencyBondDiscountVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-12-09 14:44:56
     */
    public ResponseDTO<String> add(ParGencyBondDiscountAddDTO addDTO) {
        ParGencyBondDiscountEntity entity = SmartBeanUtil.copy(addDTO, ParGencyBondDiscountEntity.class);
        parGencyBondDiscountDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-12-09 14:44:56
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ParGencyBondDiscountUpdateDTO updateDTO) {
        ParGencyBondDiscountEntity entity = SmartBeanUtil.copy(updateDTO, ParGencyBondDiscountEntity.class);
        parGencyBondDiscountDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-12-09 14:44:56
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        parGencyBondDiscountDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-12-09 14:44:56
     */
    public List<ParGencyBondDiscountExcelVO> queryAllExportData(ParGencyBondDiscountQueryDTO queryDTO) {
        return parGencyBondDiscountDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-12-09 14:44:56
     */
    public List<ParGencyBondDiscountExcelVO> queryBatchExportData(List<Long> idList) {
        return parGencyBondDiscountDao.queryBatchExportData(idList);
    }
}
