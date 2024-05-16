package net.lab1024.smartadmin.module.business.param.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParFormulaTotalQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParFormulaTotalEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParFormulaTotalVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParFormulaTotalExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 公式 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-17 17:01:27
 * @since JDK1.8
 */
@Mapper
@Component
public interface ParFormulaTotalDao extends BaseMapper<ParFormulaTotalEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return ParFormulaTotalVO
    */
    IPage<ParFormulaTotalVO> queryByPage(Page page, @Param("queryDTO") ParFormulaTotalQueryDTO queryDTO);

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
    List<ParFormulaTotalExcelVO> queryAllExportData(@Param("queryDTO")ParFormulaTotalQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<ParFormulaTotalExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    List<ParFormulaTotalVO> queryAll(@Param("allFormulaEntity") ParFormulaTotalEntity allFormulaEntity);
}
