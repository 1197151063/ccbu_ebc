package net.lab1024.smartadmin.module.business.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.param.dao.ParMarketsharesTestDao;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParMarketsharesTestAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParMarketsharesTestUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParMarketsharesTestQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParMarketsharesTestEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParMarketsharesTestVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParMarketsharesTestExcelVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [ 各团队各阶段市场份额(开发阶段测试数据用:共6个团队，数据按六个团队计算) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 14:04:54
 * @since JDK1.8
 */
@Service
public class ParMarketsharesTestService {

    @Autowired
    private ParMarketsharesTestDao parMarketsharesTestDao;

    /**
     * 根据id查询
     */
    public ParMarketsharesTestEntity getById(Long id){
        return  parMarketsharesTestDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-11-25 14:04:54
     */
    public ResponseDTO<PageResultDTO<ParMarketsharesTestVO>> queryByPage(ParMarketsharesTestQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ParMarketsharesTestVO> voList = parMarketsharesTestDao.queryByPage(page, queryDTO);
        PageResultDTO<ParMarketsharesTestVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-11-25 14:04:54
     */
    public ResponseDTO<String> add(ParMarketsharesTestAddDTO addDTO) {
        ParMarketsharesTestEntity entity = SmartBeanUtil.copy(addDTO, ParMarketsharesTestEntity.class);
        parMarketsharesTestDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-11-25 14:04:54
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ParMarketsharesTestUpdateDTO updateDTO) {
        ParMarketsharesTestEntity entity = SmartBeanUtil.copy(updateDTO, ParMarketsharesTestEntity.class);
        parMarketsharesTestDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-11-25 14:04:54
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        parMarketsharesTestDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-11-25 14:04:54
     */
    public List<ParMarketsharesTestExcelVO> queryAllExportData(ParMarketsharesTestQueryDTO queryDTO) {
        return parMarketsharesTestDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-11-25 14:04:54
     */
    public List<ParMarketsharesTestExcelVO> queryBatchExportData(List<Long> idList) {
        return parMarketsharesTestDao.queryBatchExportData(idList);
    }
}
