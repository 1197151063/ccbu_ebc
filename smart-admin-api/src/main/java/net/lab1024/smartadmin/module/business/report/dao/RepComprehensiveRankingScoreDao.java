package net.lab1024.smartadmin.module.business.report.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepComprehensiveRankingScoreQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.entity.RepComprehensiveRankingScoreEntity;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepComprehensiveRankingScoreVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepComprehensiveRankingScoreExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 综合排名分数表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-17 14:42:37
 * @since JDK1.8
 */
@Mapper
@Component
public interface RepComprehensiveRankingScoreDao extends BaseMapper<RepComprehensiveRankingScoreEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return RepComprehensiveRankingScoreVO
    */
    IPage<RepComprehensiveRankingScoreVO> queryByPage(Page page, @Param("queryDTO") RepComprehensiveRankingScoreQueryDTO queryDTO);

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
    List<RepComprehensiveRankingScoreExcelVO> queryAllExportData(@Param("queryDTO")RepComprehensiveRankingScoreQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<RepComprehensiveRankingScoreExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    List<RepComprehensiveRankingScoreVO> queryTeamRankingByStageId(@Param("proId")Long proId,@Param("stageId")Long stageId);
}
