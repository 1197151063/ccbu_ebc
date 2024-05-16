package net.lab1024.smartadmin.module.business.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.param.dao.ParPersonnelCostDao;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParPersonnelCostAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParPersonnelCostUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParPersonnelCostQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParPersonnelCostEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParPersonnelCostVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParPersonnelCostExcelVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [ 人员成本表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-02 14:36:42
 * @since JDK1.8
 */
@Service
public class ParPersonnelCostService {

    @Autowired
    private ParPersonnelCostDao parPersonnelCostDao;

    /**
     * 根据id查询
     */
    public ParPersonnelCostEntity getById(Long id){
        return  parPersonnelCostDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-12-02 14:36:42
     */
    public ResponseDTO<PageResultDTO<ParPersonnelCostVO>> queryByPage(ParPersonnelCostQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ParPersonnelCostVO> voList = parPersonnelCostDao.queryByPage(page, queryDTO);
        PageResultDTO<ParPersonnelCostVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-12-02 14:36:42
     */
    public ResponseDTO<String> add(ParPersonnelCostAddDTO addDTO) {
        ParPersonnelCostEntity entity = SmartBeanUtil.copy(addDTO, ParPersonnelCostEntity.class);
        parPersonnelCostDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-12-02 14:36:42
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ParPersonnelCostUpdateDTO updateDTO) {
        ParPersonnelCostEntity entity = SmartBeanUtil.copy(updateDTO, ParPersonnelCostEntity.class);
        parPersonnelCostDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-12-02 14:36:42
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        parPersonnelCostDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-12-02 14:36:42
     */
    public List<ParPersonnelCostExcelVO> queryAllExportData(ParPersonnelCostQueryDTO queryDTO) {
        return parPersonnelCostDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-12-02 14:36:42
     */
    public List<ParPersonnelCostExcelVO> queryBatchExportData(List<Long> idList) {
        return parPersonnelCostDao.queryBatchExportData(idList);
    }

    public ParPersonnelCostVO queryAdditional(ParPersonnelCostEntity parPersonnelCostEntity) {
        return parPersonnelCostDao.queryAdditional(parPersonnelCostEntity);
    }
}
