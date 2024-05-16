package net.lab1024.smartadmin.module.business.param.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParFormulaQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParFormulaEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParFormulaVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParFormulaExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 存贷款计算公式表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-07 14:42:04
 * @since JDK1.8
 */
@Mapper
@Component
public interface ParFormulaDao extends BaseMapper<ParFormulaEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return ParFormulaVO
    */
    IPage<ParFormulaVO> queryByPage(Page page, @Param("queryDTO") ParFormulaQueryDTO queryDTO);

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
    List<ParFormulaExcelVO> queryAllExportData(@Param("queryDTO")ParFormulaQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<ParFormulaExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    Integer queryParameter(@Param("businessName")String businessName);
}
