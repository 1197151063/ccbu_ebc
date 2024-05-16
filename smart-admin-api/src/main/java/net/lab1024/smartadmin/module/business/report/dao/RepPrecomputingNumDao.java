package net.lab1024.smartadmin.module.business.report.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepPrecomputingNumQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.entity.RepPrecomputingNumEntity;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepPrecomputingNumVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepPrecomputingNumExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 预计算次数表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-27 17:08:27
 * @since JDK1.8
 */
@Mapper
@Component
public interface RepPrecomputingNumDao extends BaseMapper<RepPrecomputingNumEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return RepPrecomputingNumVO
    */
    IPage<RepPrecomputingNumVO> queryByPage(Page page, @Param("queryDTO") RepPrecomputingNumQueryDTO queryDTO);

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
    List<RepPrecomputingNumExcelVO> queryAllExportData(@Param("queryDTO")RepPrecomputingNumQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<RepPrecomputingNumExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    Integer queryPreNum(@Param("currentProjectId")Long currentProjectId,@Param("currentTeamId") Long currentTeamId,@Param("currentStageId") Long currentStageId);

    void updateNum(@Param("repPrecomputingNumEntity")RepPrecomputingNumEntity repPrecomputingNumEntity);
}
