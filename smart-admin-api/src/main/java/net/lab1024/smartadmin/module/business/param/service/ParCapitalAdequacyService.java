package net.lab1024.smartadmin.module.business.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.param.dao.ParCapitalAdequacyDao;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParCapitalAdequacyAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParCapitalAdequacyUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParCapitalAdequacyQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParCapitalAdequacyEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParCapitalAdequacyVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParCapitalAdequacyExcelVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [ 资本充足率-风险加权系数% ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-05 11:21:36
 * @since JDK1.8
 */
@Service
public class ParCapitalAdequacyService {

    @Autowired
    private ParCapitalAdequacyDao parCapitalAdequacyDao;

    /**
     * 根据id查询
     */
    public ParCapitalAdequacyEntity getById(Long id){
        return  parCapitalAdequacyDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-12-05 11:21:36
     */
    public ResponseDTO<PageResultDTO<ParCapitalAdequacyVO>> queryByPage(ParCapitalAdequacyQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ParCapitalAdequacyVO> voList = parCapitalAdequacyDao.queryByPage(page, queryDTO);
        PageResultDTO<ParCapitalAdequacyVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-12-05 11:21:36
     */
    public ResponseDTO<String> add(ParCapitalAdequacyAddDTO addDTO) {
        ParCapitalAdequacyEntity entity = SmartBeanUtil.copy(addDTO, ParCapitalAdequacyEntity.class);
        parCapitalAdequacyDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-12-05 11:21:36
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ParCapitalAdequacyUpdateDTO updateDTO) {
        ParCapitalAdequacyEntity entity = SmartBeanUtil.copy(updateDTO, ParCapitalAdequacyEntity.class);
        parCapitalAdequacyDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-12-05 11:21:36
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        parCapitalAdequacyDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-12-05 11:21:36
     */
    public List<ParCapitalAdequacyExcelVO> queryAllExportData(ParCapitalAdequacyQueryDTO queryDTO) {
        return parCapitalAdequacyDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-12-05 11:21:36
     */
    public List<ParCapitalAdequacyExcelVO> queryBatchExportData(List<Long> idList) {
        return parCapitalAdequacyDao.queryBatchExportData(idList);
    }
}
