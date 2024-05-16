package net.lab1024.smartadmin.module.business.param.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParGencyBondDiscountQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParGencyBondDiscountEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParGencyBondDiscountVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParGencyBondDiscountExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 代理债券折扣 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-09 14:44:56
 * @since JDK1.8
 */
@Mapper
@Component
public interface ParGencyBondDiscountDao extends BaseMapper<ParGencyBondDiscountEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return ParGencyBondDiscountVO
    */
    IPage<ParGencyBondDiscountVO> queryByPage(Page page, @Param("queryDTO") ParGencyBondDiscountQueryDTO queryDTO);

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
    List<ParGencyBondDiscountExcelVO> queryAllExportData(@Param("queryDTO")ParGencyBondDiscountQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<ParGencyBondDiscountExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    Integer queryDiscount(@Param("currentStageId")Long currentStageId,@Param("bondType")String bondType);
}
