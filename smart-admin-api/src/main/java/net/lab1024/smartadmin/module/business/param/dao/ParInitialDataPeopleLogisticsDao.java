package net.lab1024.smartadmin.module.business.param.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataPeopleLogisticsQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParInitialDataPeopleLogisticsEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataPeopleLogisticsVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataPeopleLogisticsExcelVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepPeopleLogisticsVO;
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
 * @date 2021-12-07 16:27:47
 * @since JDK1.8
 */
@Mapper
@Component
public interface ParInitialDataPeopleLogisticsDao extends BaseMapper<ParInitialDataPeopleLogisticsEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return ParInitialDataPeopleLogisticsVO
    */
    IPage<ParInitialDataPeopleLogisticsVO> queryByPage(Page page, @Param("queryDTO") ParInitialDataPeopleLogisticsQueryDTO queryDTO);

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
    List<ParInitialDataPeopleLogisticsExcelVO> queryAllExportData(@Param("queryDTO")ParInitialDataPeopleLogisticsQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<ParInitialDataPeopleLogisticsExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    List<ParInitialDataPeopleLogisticsVO> query();

    ParInitialDataPeopleLogisticsVO queryData(@Param("businessType") String businessType);

    List<RepPeopleLogisticsVO> selectPeopleLogisticsPar(@Param("currentStageId")Long currentStageId);
}
