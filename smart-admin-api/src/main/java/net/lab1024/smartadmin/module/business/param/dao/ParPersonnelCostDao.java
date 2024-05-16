package net.lab1024.smartadmin.module.business.param.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParPersonnelCostQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParPersonnelCostEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParPersonnelCostVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParPersonnelCostExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 人员成本表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-02 14:36:42
 * @since JDK1.8
 */
@Mapper
@Component
public interface ParPersonnelCostDao extends BaseMapper<ParPersonnelCostEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return ParPersonnelCostVO
    */
    IPage<ParPersonnelCostVO> queryByPage(Page page, @Param("queryDTO") ParPersonnelCostQueryDTO queryDTO);

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
    List<ParPersonnelCostExcelVO> queryAllExportData(@Param("queryDTO")ParPersonnelCostQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<ParPersonnelCostExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    ParPersonnelCostVO queryWages(@Param("project")String project);

    ParPersonnelCostVO queryAdditional(ParPersonnelCostEntity parPersonnelCostEntity);
}
