package net.lab1024.smartadmin.module.system.employee.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.system.employee.domain.dto.UserPhotoQueryDTO;
import net.lab1024.smartadmin.module.system.employee.domain.entity.UserPhotoEntity;
import net.lab1024.smartadmin.module.system.employee.domain.vo.UserPhotoVO;
import net.lab1024.smartadmin.module.system.employee.domain.vo.UserPhotoExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

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
@Mapper
@Component
public interface UserPhotoDao extends BaseMapper<UserPhotoEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return UserPhotoVO
    */
    IPage<UserPhotoVO> queryByPage(Page page, @Param("queryDTO") UserPhotoQueryDTO queryDTO);

    /**
     * 根据id删除
     * @param id
     * @return
    */
    void deleteById(@Param("id")Long id);

    /**
     * 根据id批量删除
     * @param idList
     * @return
    */
    void deleteByIdList(@Param("idList") List<Long> idList);

        /**
     * 查询所有导出数据
     * @param queryDTO
     * @return
     */
    List<UserPhotoExcelVO> queryAllExportData(@Param("queryDTO")UserPhotoQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<UserPhotoExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);


    UserPhotoVO queryById(@Param("id")Long id);

}
