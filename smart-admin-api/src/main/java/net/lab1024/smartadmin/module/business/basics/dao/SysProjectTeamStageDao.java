package net.lab1024.smartadmin.module.business.basics.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysBeginStageDTO;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysProjectTeamStageQueryDTO;
import net.lab1024.smartadmin.module.business.basics.domain.entity.SysProjectTeamStageEntity;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysProjectTeamStageExcelVO;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysProjectTeamStageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 项目团队阶段业务关联表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-22 11:10:47
 * @since JDK1.8
 */
@Mapper
@Component
public interface SysProjectTeamStageDao extends BaseMapper<SysProjectTeamStageEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return SysProjectTeamStageVO
    */
    IPage<SysProjectTeamStageVO> queryByPage(Page page, @Param("queryDTO") SysProjectTeamStageQueryDTO queryDTO);

    /**
     * 根据id修改
     * @param
     * @return
     */
    void updateStatus(@Param("proId")Long proId,@Param("teamId")Long teamId,@Param("stageId")Long stageId,@Param("businessName")String businessName,@Param("status")Integer status);

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
    List<SysProjectTeamStageExcelVO> queryAllExportData(@Param("queryDTO")SysProjectTeamStageQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<SysProjectTeamStageExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    void insertProjectTeamStage(@Param("proId")Long proId,@Param("teamId") Long teamId,@Param("stageId") Long stageId, @Param("businessName")String businessName,@Param("status") int status);

    List<SysProjectTeamStageVO> selectByStatus(@Param("currentProjectId")Long currentProjectId,@Param("currentStageId") Long currentStageId);

    Integer queryStatus(@Param("proId")Long proId, @Param("teamId")Long teamId, @Param("stageId")Long stageId,@Param("businessName") String businessName);

    SysProjectTeamStageVO selectCurrentStageBusiness(@Param("proId")Long proId);

    /**
     * 根据项目ID查询所有阶段信息
     * @param proId
     * @return
     */
    List<SysProjectTeamStageVO> selectStageBusiness(@Param("proId")Long proId);

    void deleteProjectTeamStage(@Param("projectId")Long projectId, @Param("id")Long id);

    Integer querySubmitStatus(@Param("currentProjectId")Long currentProjectId, @Param("currentTeamId")Long currentTeamId,@Param("currentStageId") Long currentStageId,@Param("businessName")String businessName);

    List<SysProjectTeamStageVO> queryBeforeStage(@Param("sysBeginStageDTO")SysBeginStageDTO sysBeginStageDTO);
}
