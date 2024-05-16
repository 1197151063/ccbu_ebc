package net.lab1024.smartadmin.module.business.decision.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecInvestmentLongQueryDTO;
import net.lab1024.smartadmin.module.business.decision.domain.entity.DecInvestmentLongEntity;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecInvestmentLongExcelVO;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecInvestmentLongVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 投资业务(长期投资)提交表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-23 16:38:20
 * @since JDK1.8
 */
@Mapper
@Component
public interface DecInvestmentLongDao extends BaseMapper<DecInvestmentLongEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return DecInvestmentLongVO
    */
    IPage<DecInvestmentLongVO> queryByPage(Page page, @Param("queryDTO") DecInvestmentLongQueryDTO queryDTO);

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
    List<DecInvestmentLongExcelVO> queryAllExportData(@Param("queryDTO")DecInvestmentLongQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<DecInvestmentLongExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    DecInvestmentLongVO query(@Param("currentProjectId")Long currentProjectId,@Param("currentTeamId") Long currentTeamId,@Param("currentStageId") Long currentStageId,@Param("bondType")String bondType);


    void deleteInvesmentDataByType(@Param("currentProjectId")Long currentProjectId,@Param("currentTeamId") Long currentTeamId,@Param("currentStageId") Long currentStageId,@Param("bondType")String bondType);

    List<DecInvestmentLongVO> queryAllLongInvestment(@Param("currentProjectId")Long currentProjectId, @Param("currentTeamId")Long currentTeamId);

    void deleteData(@Param("currentProjectId")Long currentProjectId,@Param("currentTeamId") Long currentTeamId,@Param("currentStageId") Long currentStageId,@Param("bondType")String bondType);


    List<DecInvestmentLongVO> querybond(@Param("currentProjectId")Long currentProjectId,@Param("currentTeamId") Long currentTeamId,@Param("currentStageId") Long currentStageId);
}
