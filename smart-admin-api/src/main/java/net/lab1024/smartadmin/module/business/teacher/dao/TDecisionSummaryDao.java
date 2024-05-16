package net.lab1024.smartadmin.module.business.teacher.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.teacher.domain.dto.TDecisionSummaryQueryDTO;
import net.lab1024.smartadmin.module.business.teacher.domain.entity.TDecisionSummaryEntity;
import net.lab1024.smartadmin.module.business.teacher.domain.vo.TDecisionSummaryExcelVO;
import net.lab1024.smartadmin.module.business.teacher.domain.vo.TDecisionSummaryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 决策总结表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2022-01-09 16:01:51
 * @since JDK1.8
 */
@Mapper
@Component
public interface TDecisionSummaryDao extends BaseMapper<TDecisionSummaryEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return TDecisionSummaryVO
    */
    IPage<TDecisionSummaryVO> queryByPage(Page page, @Param("queryDTO") TDecisionSummaryQueryDTO queryDTO);

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
    List<TDecisionSummaryExcelVO> queryAllExportData(@Param("queryDTO")TDecisionSummaryQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<TDecisionSummaryExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    ResponseDTO<String> updateStatus(@Param("status")Integer status,@Param("id")Long id);

    TDecisionSummaryEntity queryData(@Param("queryDTO")TDecisionSummaryQueryDTO queryDTO);

    List<TDecisionSummaryVO> queryDataIndex(@Param("proId") Long proId);
}
