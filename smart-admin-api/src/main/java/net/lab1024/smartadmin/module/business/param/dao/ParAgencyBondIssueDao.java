package net.lab1024.smartadmin.module.business.param.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParAgencyBondIssueQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParAgencyBondIssueEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParAgencyBondIssueVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParAgencyBondIssueExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 代理债券发行业务信息表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 13:37:59
 * @since JDK1.8
 */
@Mapper
@Component
public interface ParAgencyBondIssueDao extends BaseMapper<ParAgencyBondIssueEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return ParAgencyBondIssueVO
    */
    IPage<ParAgencyBondIssueVO> queryByPage(Page page, @Param("queryDTO") ParAgencyBondIssueQueryDTO queryDTO);

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
    List<ParAgencyBondIssueExcelVO> queryAllExportData(@Param("queryDTO")ParAgencyBondIssueQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<ParAgencyBondIssueExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    List<ParAgencyBondIssueVO> queryAgency(Long agencyCurrentStageId);
}
