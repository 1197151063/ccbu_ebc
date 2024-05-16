package net.lab1024.smartadmin.module.business.param.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataLiquidityCashQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParInitialDataLiquidityCashEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataLiquidityCashVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataLiquidityCashExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 初始数据表-流动性报表-现金 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 13:44:40
 * @since JDK1.8
 */
@Mapper
@Component
public interface ParInitialDataLiquidityCashDao extends BaseMapper<ParInitialDataLiquidityCashEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return ParInitialDataLiquidityCashVO
    */
    IPage<ParInitialDataLiquidityCashVO> queryByPage(Page page, @Param("queryDTO") ParInitialDataLiquidityCashQueryDTO queryDTO);

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
    List<ParInitialDataLiquidityCashExcelVO> queryAllExportData(@Param("queryDTO")ParInitialDataLiquidityCashQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<ParInitialDataLiquidityCashExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);
}
