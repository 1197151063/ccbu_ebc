package net.lab1024.smartadmin.module.business.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.param.dao.ParComprehensiveScoreDao;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParComprehensiveScoreAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParComprehensiveScoreUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParComprehensiveScoreQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParComprehensiveScoreEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParComprehensiveScoreVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParComprehensiveScoreExcelVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [ 综合评分指标 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-16 10:39:05
 * @since JDK1.8
 */
@Service
public class ParComprehensiveScoreService {

    @Autowired
    private ParComprehensiveScoreDao parComprehensiveScoreDao;

    /**
     * 根据id查询
     */
    public ParComprehensiveScoreEntity getById(Long id){
        return  parComprehensiveScoreDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-12-16 10:39:05
     */
    public ResponseDTO<PageResultDTO<ParComprehensiveScoreVO>> queryByPage(ParComprehensiveScoreQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ParComprehensiveScoreVO> voList = parComprehensiveScoreDao.queryByPage(page, queryDTO);
        PageResultDTO<ParComprehensiveScoreVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-12-16 10:39:05
     */
    public ResponseDTO<String> add(ParComprehensiveScoreAddDTO addDTO) {
        ParComprehensiveScoreEntity entity = SmartBeanUtil.copy(addDTO, ParComprehensiveScoreEntity.class);
        parComprehensiveScoreDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-12-16 10:39:05
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ParComprehensiveScoreUpdateDTO updateDTO) {
        ParComprehensiveScoreEntity entity = SmartBeanUtil.copy(updateDTO, ParComprehensiveScoreEntity.class);
        parComprehensiveScoreDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-12-16 10:39:05
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        parComprehensiveScoreDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-12-16 10:39:05
     */
    public List<ParComprehensiveScoreExcelVO> queryAllExportData(ParComprehensiveScoreQueryDTO queryDTO) {
        return parComprehensiveScoreDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-12-16 10:39:05
     */
    public List<ParComprehensiveScoreExcelVO> queryBatchExportData(List<Long> idList) {
        return parComprehensiveScoreDao.queryBatchExportData(idList);
    }
}
