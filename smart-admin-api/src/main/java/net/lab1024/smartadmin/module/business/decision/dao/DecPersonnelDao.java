package net.lab1024.smartadmin.module.business.decision.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecPersonnelQueryDTO;
import net.lab1024.smartadmin.module.business.decision.domain.entity.DecPersonnelEntity;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecPersonnelVO;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecPersonnelExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 人事后勤信息录入 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-23 16:27:46
 * @since JDK1.8
 */
@Mapper
@Component
public interface DecPersonnelDao extends BaseMapper<DecPersonnelEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return DecPersonnelVO
    */
    IPage<DecPersonnelVO> queryByPage(Page page, @Param("queryDTO") DecPersonnelQueryDTO queryDTO);

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
    List<DecPersonnelExcelVO> queryAllExportData(@Param("queryDTO")DecPersonnelQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<DecPersonnelExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    DecPersonnelVO query(@Param("currentProjectId")Long currentProjectId, @Param("currentTeamId")Long currentTeamId,@Param("currentStageId") Long currentStageId);

    Integer deleteData(@Param("currentProjectId")Long currentProjectId, @Param("currentTeamId")Long currentTeamId, @Param("currentStageId")Long currentStageId);

    DecPersonnelVO queryPeople(@Param("currentProjectId")Long currentProjectId, @Param("currentTeamId")Long currentTeamId, @Param("currentStageId")Long currentStageId);

}
