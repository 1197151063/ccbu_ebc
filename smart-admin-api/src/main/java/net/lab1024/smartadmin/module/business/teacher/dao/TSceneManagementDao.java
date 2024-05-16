package net.lab1024.smartadmin.module.business.teacher.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.teacher.domain.dto.TSceneManagementQueryDTO;
import net.lab1024.smartadmin.module.business.teacher.domain.entity.TSceneManagementEntity;
import net.lab1024.smartadmin.module.business.teacher.domain.vo.TSceneManagementVO;
import net.lab1024.smartadmin.module.business.teacher.domain.vo.TSceneManagementExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

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
@Mapper
@Component
public interface TSceneManagementDao extends BaseMapper<TSceneManagementEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return TSceneManagementVO
    */
    IPage<TSceneManagementVO> queryByPage(Page page, @Param("queryDTO") TSceneManagementQueryDTO queryDTO,@Param("releaseType") Integer releaseType);

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
    List<TSceneManagementExcelVO> queryAllExportData(@Param("queryDTO")TSceneManagementQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<TSceneManagementExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    List<TSceneManagementVO> queryDataIndex(@Param("releaseType")Integer releaseType);

    TSceneManagementEntity queryData(@Param("id")Long id);
}
