package net.lab1024.smartadmin.module.business.param.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataLiquidityTotalityQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParInitialDataLiquidityTotalityEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataLiquidityTotalityVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataLiquidityTotalityExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 初始数据表-流动性报表-总体 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 13:45:23
 * @since JDK1.8
 */
@Mapper
@Component
public interface ParInitialDataLiquidityTotalityDao extends BaseMapper<ParInitialDataLiquidityTotalityEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return ParInitialDataLiquidityTotalityVO
    */
    IPage<ParInitialDataLiquidityTotalityVO> queryByPage(Page page, @Param("queryDTO") ParInitialDataLiquidityTotalityQueryDTO queryDTO);

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
    List<ParInitialDataLiquidityTotalityExcelVO> queryAllExportData(@Param("queryDTO")ParInitialDataLiquidityTotalityQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<ParInitialDataLiquidityTotalityExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);
}
