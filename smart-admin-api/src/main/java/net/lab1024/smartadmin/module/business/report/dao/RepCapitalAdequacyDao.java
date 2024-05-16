package net.lab1024.smartadmin.module.business.report.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepCapitalAdequacyQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.entity.RepCapitalAdequacyEntity;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepCapitalAdequacyVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepCapitalAdequacyExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 资本充足率报告 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 11:17:26
 * @since JDK1.8
 */
@Mapper
@Component
public interface RepCapitalAdequacyDao extends BaseMapper<RepCapitalAdequacyEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return RepCapitalAdequacyVO
    */
    IPage<RepCapitalAdequacyVO> queryByPage(Page page, @Param("queryDTO") RepCapitalAdequacyQueryDTO queryDTO);

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
    List<RepCapitalAdequacyExcelVO> queryAllExportData(@Param("queryDTO")RepCapitalAdequacyQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<RepCapitalAdequacyExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);


    List<RepCapitalAdequacyVO> selectRepCapitalAdequacyPar(@Param("currentStageId")Long currentStageId);

    List<RepCapitalAdequacyVO> selectRepCapitalAdequacyRep(@Param("currentTeamId")Long currentTeamId, @Param("currentStageId")Long currentStageId);

    RepCapitalAdequacyVO query(@Param("currentProjectId")Long currentProjectId,@Param("currentTeamId")Long currentTeamId,@Param("currentStageId")Long currentStageId);

    RepCapitalAdequacyVO queryCapitalPar(@Param("beforeCurrentStageId")Long beforeCurrentStageId);

    List<RepCapitalAdequacyExcelVO> queryExportData(@Param("currentProjectId")Long proId,@Param("currentStageId") Long stageId);
}
