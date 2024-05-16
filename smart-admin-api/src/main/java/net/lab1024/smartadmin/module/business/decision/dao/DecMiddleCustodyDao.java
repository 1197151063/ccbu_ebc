package net.lab1024.smartadmin.module.business.decision.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecMiddleCustodyQueryDTO;
import net.lab1024.smartadmin.module.business.decision.domain.entity.DecMiddleCustodyEntity;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecMiddleCustodyExcelVO;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecMiddleCustodyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 中间业务提交表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-23 15:28:40
 * @since JDK1.8
 */
@Mapper
@Component
public interface DecMiddleCustodyDao extends BaseMapper<DecMiddleCustodyEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return DecMiddleCustodyVO
    */
    IPage<DecMiddleCustodyVO> queryByPage(Page page, @Param("queryDTO") DecMiddleCustodyQueryDTO queryDTO);

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
    List<DecMiddleCustodyExcelVO> queryAllExportData(@Param("queryDTO")DecMiddleCustodyQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<DecMiddleCustodyExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    DecMiddleCustodyVO query(@Param("currentProjectId")Long currentProjectId,@Param("currentTeamId") Long currentTeamId, @Param("currentStageId")Long currentStageId);

    List<DecMiddleCustodyVO> queryData(@Param("currentProjectId")Long currentProjectId,@Param("currentStageId") Long currentStageId);

    Integer deleteData(@Param("currentProjectId")Long currentProjectId,@Param("currentTeamId") Long currentTeamId, @Param("currentStageId")Long currentStageId);

    DecMiddleCustodyVO queryDataAndStatus(@Param("currentProjectId")Long currentProjectId, @Param("currentTeamId")Long currentTeamId, @Param("currentStageId") Long currentStageId);
}
