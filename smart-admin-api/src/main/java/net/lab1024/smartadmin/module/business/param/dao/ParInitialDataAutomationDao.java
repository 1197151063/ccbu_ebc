package net.lab1024.smartadmin.module.business.param.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataAutomationQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParInitialDataAutomationEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataAutomationVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataAutomationExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 初始数据表-人事后勤数据表(自动化投资) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-06 14:22:45
 * @since JDK1.8
 */
@Mapper
@Component
public interface ParInitialDataAutomationDao extends BaseMapper<ParInitialDataAutomationEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return ParInitialDataAutomationVO
    */
    IPage<ParInitialDataAutomationVO> queryByPage(Page page, @Param("queryDTO") ParInitialDataAutomationQueryDTO queryDTO);

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
    List<ParInitialDataAutomationExcelVO> queryAllExportData(@Param("queryDTO")ParInitialDataAutomationQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<ParInitialDataAutomationExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    ParInitialDataAutomationVO selectInitialDataAutomationData(@Param("currentStageId")Long currentStageId, @Param("project")String project );

    List<ParInitialDataAutomationVO> getAllInitialDataAutomationData(@Param("currentStageId")Long currentStageId);
}
