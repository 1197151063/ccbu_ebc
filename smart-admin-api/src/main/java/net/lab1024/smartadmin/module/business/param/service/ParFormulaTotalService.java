package net.lab1024.smartadmin.module.business.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.param.dao.ParFormulaTotalDao;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParFormulaTotalAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParFormulaTotalUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParFormulaTotalQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParFormulaTotalEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParFormulaTotalVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParFormulaTotalExcelVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [ 公式 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-17 17:01:27
 * @since JDK1.8
 */
@Service
public class ParFormulaTotalService {

    @Autowired
    private ParFormulaTotalDao parFormulaTotalDao;

    /**
     * 根据id查询
     */
    public ParFormulaTotalEntity getById(Long id){
        return  parFormulaTotalDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-12-17 17:01:27
     */
    public ResponseDTO<PageResultDTO<ParFormulaTotalVO>> queryByPage(ParFormulaTotalQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ParFormulaTotalVO> voList = parFormulaTotalDao.queryByPage(page, queryDTO);
        PageResultDTO<ParFormulaTotalVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-12-17 17:01:27
     */
    public ResponseDTO<String> add(ParFormulaTotalAddDTO addDTO) {
        ParFormulaTotalEntity entity = SmartBeanUtil.copy(addDTO, ParFormulaTotalEntity.class);
        parFormulaTotalDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-12-17 17:01:27
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ParFormulaTotalUpdateDTO updateDTO) {
        ParFormulaTotalEntity entity = SmartBeanUtil.copy(updateDTO, ParFormulaTotalEntity.class);
        parFormulaTotalDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-12-17 17:01:27
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        parFormulaTotalDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-12-17 17:01:27
     */
    public List<ParFormulaTotalExcelVO> queryAllExportData(ParFormulaTotalQueryDTO queryDTO) {
        return parFormulaTotalDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-12-17 17:01:27
     */
    public List<ParFormulaTotalExcelVO> queryBatchExportData(List<Long> idList) {
        return parFormulaTotalDao.queryBatchExportData(idList);
    }

    public List<ParFormulaTotalVO> queryAll(ParFormulaTotalEntity allFormulaEntity) {
        return parFormulaTotalDao.queryAll(allFormulaEntity);
    }
}
