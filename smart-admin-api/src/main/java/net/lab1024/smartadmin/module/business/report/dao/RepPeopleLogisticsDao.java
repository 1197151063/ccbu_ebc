package net.lab1024.smartadmin.module.business.report.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepPeopleLogisticsQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.entity.RepPeopleLogisticsEntity;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepPeopleLogisticsVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepPeopleLogisticsExcelVO;
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
 * @date 2021-12-07 17:25:20
 * @since JDK1.8
 */
@Mapper
@Component
public interface RepPeopleLogisticsDao extends BaseMapper<RepPeopleLogisticsEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return RepPeopleLogisticsVO
    */
    IPage<RepPeopleLogisticsVO> queryByPage(Page page, @Param("queryDTO") RepPeopleLogisticsQueryDTO queryDTO);

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
    List<RepPeopleLogisticsExcelVO> queryAllExportData(@Param("queryDTO")RepPeopleLogisticsQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<RepPeopleLogisticsExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    List<RepPeopleLogisticsVO> queryData(@Param("currentProjectId")Long currentProjectId,@Param("currentTeamId") Long currentTeamId, @Param("currentStageId")Long currentStageId);

    List<RepPeopleLogisticsVO> selectPeopleLogisticsDataPar(@Param("currentStageId") Long currentStageId);

    List<RepPeopleLogisticsVO> selectPeopleLogisticsDataRep(@Param("currentTeamId")Long currentTeamId,@Param("currentStageId") Long currentStageId);

    RepPeopleLogisticsVO queryBeforeData(@Param("currentProjectId")Long currentProjectId,@Param("currentTeamId")Long currentTeamId,@Param("currentStageId") Long currentStageId, @Param("businessType")String businessType);

    List<RepPeopleLogisticsVO> selectPeopleRep(@Param("currentTeamId") Long currentTeamId, @Param("currentStageId")Long currentStageId);

    List<RepPeopleLogisticsVO> selectPeoplePar(@Param("currentStageId")Long currentStageId);

    RepPeopleLogisticsVO selectPeopleByPar(@Param("currentStageId")Long currentStageId,@Param("businessType") String businessType);

    List<RepPeopleLogisticsVO> selectPeopleLogisticsRep(@Param("currentTeamId")Long currentTeamId, @Param("currentStageId")Long currentStageId);

    List<RepPeopleLogisticsVO> query(@Param("currentProjectId")Long currentProjectId,@Param("currentTeamId")Long currentTeamId, @Param("currentStageId")Long currentStageId);

    List<RepPeopleLogisticsExcelVO> queryExportData(@Param("currentProjectId")Long proId,@Param("currentStageId") Long stageId);
}
