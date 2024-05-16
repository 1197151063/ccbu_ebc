package net.lab1024.smartadmin.module.business.param.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataInvestbusStockQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParInitialDataInvestbusStockEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataInvestbusStockVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataInvestbusStockExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 初始数据表-投资业务营业数据表(股票) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 14:04:09
 * @since JDK1.8
 */
@Mapper
@Component
public interface ParInitialDataInvestbusStockDao extends BaseMapper<ParInitialDataInvestbusStockEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return ParInitialDataInvestbusStockVO
    */
    IPage<ParInitialDataInvestbusStockVO> queryByPage(Page page, @Param("queryDTO") ParInitialDataInvestbusStockQueryDTO queryDTO);

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
    List<ParInitialDataInvestbusStockExcelVO> queryAllExportData(@Param("queryDTO")ParInitialDataInvestbusStockQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<ParInitialDataInvestbusStockExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    ParInitialDataInvestbusStockVO selectInvestbusOperatData(@Param("currentStageId")Long currentStageId, @Param("stockProject")String stockProject, @Param("stockType")String stockType);
}
