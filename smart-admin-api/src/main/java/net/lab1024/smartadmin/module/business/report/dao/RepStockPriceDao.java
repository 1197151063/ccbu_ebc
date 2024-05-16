package net.lab1024.smartadmin.module.business.report.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepStockPriceQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.entity.RepStockPriceEntity;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepStockPriceVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepStockPriceExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 股价表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-17 19:06:10
 * @since JDK1.8
 */
@Mapper
@Component
public interface RepStockPriceDao extends BaseMapper<RepStockPriceEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return RepStockPriceVO
    */
    IPage<RepStockPriceVO> queryByPage(Page page, @Param("queryDTO") RepStockPriceQueryDTO queryDTO);

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
    List<RepStockPriceExcelVO> queryAllExportData(@Param("queryDTO")RepStockPriceQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<RepStockPriceExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    List<RepStockPriceVO> queryStockPriceByProid(@Param("proId")Long proId);
}
