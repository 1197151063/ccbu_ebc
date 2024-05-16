package net.lab1024.smartadmin.module.system.employee.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.system.employee.dao.UserPhotoDao;
import net.lab1024.smartadmin.module.system.employee.domain.dto.UserPhotoAddDTO;
import net.lab1024.smartadmin.module.system.employee.domain.dto.UserPhotoUpdateDTO;
import net.lab1024.smartadmin.module.system.employee.domain.dto.UserPhotoQueryDTO;
import net.lab1024.smartadmin.module.system.employee.domain.entity.UserPhotoEntity;
import net.lab1024.smartadmin.module.system.employee.domain.vo.UserPhotoVO;
import net.lab1024.smartadmin.module.system.employee.domain.vo.UserPhotoExcelVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [  ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-14 14:49:16
 * @since JDK1.8
 */
@Service
public class UserPhotoService {

    @Autowired
    private UserPhotoDao userPhotoDao;

    /**
     * 根据id查询
     */
    public UserPhotoEntity getById(Long id){
        return  userPhotoDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2021-12-14 14:49:16
     */
    public ResponseDTO<PageResultDTO<UserPhotoVO>> queryByPage(UserPhotoQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<UserPhotoVO> voList = userPhotoDao.queryByPage(page, queryDTO);
        PageResultDTO<UserPhotoVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2021-12-14 14:49:16
     */
    public ResponseDTO<UserPhotoEntity> add(UserPhotoVO addDTO) {
        UserPhotoEntity entity = SmartBeanUtil.copy(addDTO, UserPhotoEntity.class);
        if(entity.getId()!=null){
            userPhotoDao.updateById(entity);
        }else{
            userPhotoDao.insert(entity);
        }
        return ResponseDTO.succData(entity);
    }

    /**
     * 编辑
     * @author SMS
     * @date 2021-12-14 14:49:16
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(UserPhotoUpdateDTO updateDTO) {
        UserPhotoEntity entity = SmartBeanUtil.copy(updateDTO, UserPhotoEntity.class);
        userPhotoDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2021-12-14 14:49:16
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        userPhotoDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2021-12-14 14:49:16
     */
    public List<UserPhotoExcelVO> queryAllExportData(UserPhotoQueryDTO queryDTO) {
        return userPhotoDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2021-12-14 14:49:16
     */
    public List<UserPhotoExcelVO> queryBatchExportData(List<Long> idList) {
        return userPhotoDao.queryBatchExportData(idList);
    }

    public ResponseDTO<UserPhotoVO> queryById(Long id){
        return ResponseDTO.succData(userPhotoDao.queryById(id));
    }
}
