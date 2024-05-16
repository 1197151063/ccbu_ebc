package net.lab1024.smartadmin.module.business.report.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepAgencyBondQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.entity.RepAgencyBondEntity;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepAgencyBondExcelVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepAgencyBondVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 代理债券发行业务数据表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-08 12:46:28
 * @since JDK1.8
 */
@Mapper
@Component
public interface RepAgencyBondDao extends BaseMapper<RepAgencyBondEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return RepAgencyBondVO
    */
    IPage<RepAgencyBondVO> queryByPage(Page page, @Param("queryDTO") RepAgencyBondQueryDTO queryDTO);

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
    List<RepAgencyBondExcelVO> queryAllExportData(@Param("queryDTO")RepAgencyBondQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<RepAgencyBondExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    List<RepAgencyBondVO> selectAgencyBondAll(@Param("currentProjectId")Long currentProjectId, @Param("currentStageId")Long currentStageId);

    List<RepAgencyBondVO> query(@Param("currentProjectId")Long projectId,@Param("currentTeamId")Long teamId, @Param("currentStageId")Long stageId);

    List<RepAgencyBondVO> selectAgencyBond( @Param("currentStageId")Long currentStageId);
}
