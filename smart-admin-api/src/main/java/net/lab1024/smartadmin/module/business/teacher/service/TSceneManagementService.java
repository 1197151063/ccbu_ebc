package net.lab1024.smartadmin.module.business.teacher.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.teacher.dao.TSceneManagementDao;
import net.lab1024.smartadmin.module.business.teacher.domain.dto.TSceneManagementAddDTO;
import net.lab1024.smartadmin.module.business.teacher.domain.dto.TSceneManagementQueryDTO;
import net.lab1024.smartadmin.module.business.teacher.domain.dto.TSceneManagementUpdateDTO;
import net.lab1024.smartadmin.module.business.teacher.domain.entity.TSceneManagementEntity;
import net.lab1024.smartadmin.module.business.teacher.domain.vo.TSceneManagementExcelVO;
import net.lab1024.smartadmin.module.business.teacher.domain.vo.TSceneManagementVO;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import net.lab1024.smartadmin.util.SmartPageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * [ 场景管理表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2022-01-11 09:05:57
 * @since JDK1.8
 */
@Service
public class TSceneManagementService {

    @Autowired
    private TSceneManagementDao tSceneManagementDao;

    /**
     * 根据id查询
     */
    public TSceneManagementEntity getById(Long id){
        return  tSceneManagementDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2022-01-11 09:05:57
     */
    public ResponseDTO<PageResultDTO<TSceneManagementVO>> queryByPage(TSceneManagementQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<TSceneManagementVO> voList = tSceneManagementDao.queryByPage(page, queryDTO,queryDTO.getReleaseType());
        PageResultDTO<TSceneManagementVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2022-01-11 09:05:57
     */
    public ResponseDTO<String> add(TSceneManagementAddDTO addDTO) {
        TSceneManagementEntity entity = SmartBeanUtil.copy(addDTO, TSceneManagementEntity.class);
        if(addDTO.getId()!=null){
            tSceneManagementDao.deleteById(addDTO.getId());
        }
        tSceneManagementDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2022-01-11 09:05:57
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(TSceneManagementUpdateDTO updateDTO) {
        TSceneManagementEntity entity = SmartBeanUtil.copy(updateDTO, TSceneManagementEntity.class);
        tSceneManagementDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2022-01-11 09:05:57
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        tSceneManagementDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2022-01-11 09:05:57
     */
    public List<TSceneManagementExcelVO> queryAllExportData(TSceneManagementQueryDTO queryDTO) {
        return tSceneManagementDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2022-01-11 09:05:57
     */
    public List<TSceneManagementExcelVO> queryBatchExportData(List<Long> idList) {
        return tSceneManagementDao.queryBatchExportData(idList);
    }

    public ResponseDTO<Map<Integer, List<TSceneManagementVO>>> queryDataIndex() {

        Map<Integer, List<TSceneManagementVO>> map=new TreeMap<>();
        Integer releaseType[] ={1,2,3,4};
        for (int i = 0; i <releaseType.length; i++) {
            List<TSceneManagementVO> tSceneManagementVOS = tSceneManagementDao.queryDataIndex(releaseType[i]);
            map.put(i,tSceneManagementVOS);
        }
        return ResponseDTO.succData(map);
    }

    public ResponseDTO<TSceneManagementEntity> queryData(Long id) {
        TSceneManagementEntity tSceneManagementEntity = tSceneManagementDao.queryData(id);
        return ResponseDTO.succData(tSceneManagementEntity);

    }
}
