package net.lab1024.smartadmin.module.business.decision.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecMarketStrategyQueryDTO;
import net.lab1024.smartadmin.module.business.decision.domain.entity.DecMarketStrategyEntity;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecMarketStrategyVO;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecMarketStrategyExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 市场营销策略 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-23 16:31:22
 * @since JDK1.8
 */
@Mapper
@Component
public interface DecMarketStrategyDao extends BaseMapper<DecMarketStrategyEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return DecMarketStrategyVO
    */
    IPage<DecMarketStrategyVO> queryByPage(Page page, @Param("queryDTO") DecMarketStrategyQueryDTO queryDTO);

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
    List<DecMarketStrategyExcelVO> queryAllExportData(@Param("queryDTO")DecMarketStrategyQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<DecMarketStrategyExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    DecMarketStrategyVO query(@Param("currentProjectId")Long currentProjectId,@Param("currentTeamId") Long currentTeamId,@Param("currentStageId") Long currentStageId);

    Integer deleteData(@Param("currentProjectId")Long currentProjectId, @Param("currentTeamId")Long currentTeamId, @Param("currentStageId")Long currentStageId);

    DecMarketStrategyVO queryDataAndStatus(@Param("currentProjectId")Long currentProjectId, @Param("currentTeamId")Long currentTeamId, @Param("currentStageId")Long currentStageId);

    DecMarketStrategyVO queryMarket(@Param("currentTeamId") Long currentTeamId,@Param("currentStageId") Long currentStageId);
}
