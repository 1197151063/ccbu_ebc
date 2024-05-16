package net.lab1024.smartadmin.module.business.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.param.dao.ParWastageRateDao;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParWastageRateAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParWastageRateUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParWastageRateQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParWastageRateEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParWastageRateVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParWastageRateExcelVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [ 人事后勤信息录入 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-08 16:05:49
 * @since JDK1.8
 */
@Service
public class ParWastageRateService {

    @Autowired
    private ParWastageRateDao parWastageRateDao;

    /**
     * 根据id查询
     */
    public ParWastageRateEntity getById(Long id){
        return  parWastageRateDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-12-08 16:05:49
     */
    public ResponseDTO<PageResultDTO<ParWastageRateVO>> queryByPage(ParWastageRateQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ParWastageRateVO> voList = parWastageRateDao.queryByPage(page, queryDTO);
        PageResultDTO<ParWastageRateVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-12-08 16:05:49
     */
    public ResponseDTO<String> add(ParWastageRateAddDTO addDTO) {
        ParWastageRateEntity entity = SmartBeanUtil.copy(addDTO, ParWastageRateEntity.class);
        parWastageRateDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-12-08 16:05:49
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ParWastageRateUpdateDTO updateDTO) {
        ParWastageRateEntity entity = SmartBeanUtil.copy(updateDTO, ParWastageRateEntity.class);
        parWastageRateDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-12-08 16:05:49
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        parWastageRateDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-12-08 16:05:49
     */
    public List<ParWastageRateExcelVO> queryAllExportData(ParWastageRateQueryDTO queryDTO) {
        return parWastageRateDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-12-08 16:05:49
     */
    public List<ParWastageRateExcelVO> queryBatchExportData(List<Long> idList) {
        return parWastageRateDao.queryBatchExportData(idList);
    }
}
