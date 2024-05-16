package net.lab1024.smartadmin.module.business.teacher.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.teacher.dao.TMessageDao;
import net.lab1024.smartadmin.module.business.teacher.domain.dto.TMessageAddDTO;
import net.lab1024.smartadmin.module.business.teacher.domain.dto.TMessageQueryDTO;
import net.lab1024.smartadmin.module.business.teacher.domain.dto.TMessageUpdateDTO;
import net.lab1024.smartadmin.module.business.teacher.domain.entity.TMessageEntity;
import net.lab1024.smartadmin.module.business.teacher.domain.vo.TMessageExcelVO;
import net.lab1024.smartadmin.module.business.teacher.domain.vo.TMessageVO;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import net.lab1024.smartadmin.util.SmartPageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [ 消息管理表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2022-01-06 09:46:05
 * @since JDK1.8
 */
@Service
public class TMessageService {

    @Autowired
    private TMessageDao tMessageDao;

    /**
     * 根据id查询
     */
    public TMessageEntity getById(Long id){
        return  tMessageDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2022-01-06 09:46:05
     */
    public ResponseDTO<PageResultDTO<TMessageVO>> queryByPage(TMessageQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);

        IPage<TMessageVO> voList = null;
        if(queryDTO.getMessageType()==1){
            voList  = tMessageDao.queryByPage(page, queryDTO,null);
        }else {
            voList  = tMessageDao.queryByPage(page, queryDTO,queryDTO.getMessageType());
        }

        PageResultDTO<TMessageVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2022-01-06 09:46:05
     */
    public ResponseDTO<String> add(TMessageAddDTO addDTO) {
        TMessageEntity entity = SmartBeanUtil.copy(addDTO, TMessageEntity.class);
        if(addDTO.getId()!=null){
            tMessageDao.deleteById(addDTO.getId());
        }
        tMessageDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2022-01-06 09:46:05
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(TMessageUpdateDTO updateDTO) {
        TMessageEntity entity = SmartBeanUtil.copy(updateDTO, TMessageEntity.class);
        tMessageDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2022-01-06 09:46:05
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        tMessageDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2022-01-06 09:46:05
     */
    public List<TMessageExcelVO> queryAllExportData(TMessageQueryDTO queryDTO) {
        return tMessageDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2022-01-06 09:46:05
     */
    public List<TMessageExcelVO> queryBatchExportData(List<Long> idList) {
        return tMessageDao.queryBatchExportData(idList);
    }

    public ResponseDTO<String> deleteId(Long id) {
        tMessageDao.deleteById(id);
        return ResponseDTO.succ();
    }

    public ResponseDTO<TMessageEntity> queryData(Long id) {
        TMessageEntity tMessageEntity = tMessageDao.queryData(id);
        return ResponseDTO.succData(tMessageEntity);
    }

    public ResponseDTO<List<TMessageEntity>> queryMessageData() {
        List<TMessageEntity> tMessageEntityList = tMessageDao.queryMessageData();
        return ResponseDTO.succData(tMessageEntityList);
    }
}
