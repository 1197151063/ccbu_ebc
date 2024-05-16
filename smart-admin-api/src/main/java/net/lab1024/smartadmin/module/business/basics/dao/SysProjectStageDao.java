package net.lab1024.smartadmin.module.business.basics.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysProjectStageQueryDTO;
import net.lab1024.smartadmin.module.business.basics.domain.entity.SysProjectStageEntity;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysProjectStageVO;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysProjectStageExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 项目阶段关联表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-22 16:13:42
 * @since JDK1.8
 */
@Mapper
@Component
public interface SysProjectStageDao extends BaseMapper<SysProjectStageEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return SysProjectStageVO
    */
    IPage<SysProjectStageVO> queryByPage(Page page, @Param("queryDTO") SysProjectStageQueryDTO queryDTO);

    List<SysProjectStageVO> querySysProjectStage(@Param("sysProjectStageVO") SysProjectStageVO sysProjectStageVO);

    List<SysProjectStageVO> querySysProjectEndStage(@Param("sysProjectStageVO") SysProjectStageVO sysProjectStageVO);
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
    List<SysProjectStageExcelVO> queryAllExportData(@Param("queryDTO")SysProjectStageQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<SysProjectStageExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    void insertProjectStage(@Param("proId")Long proId, @Param("stageId")Integer stageId,@Param("status") int status);

    void beginStage(@Param("currentProjectId")Long currentProjectId, @Param("currentStageId")Long currentStageId, @Param("status")Integer status);
}
