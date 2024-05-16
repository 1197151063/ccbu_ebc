package net.lab1024.smartadmin.module.business.param.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParTrainingLevelQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParTrainingLevelEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParTrainingLevelVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParTrainingLevelExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 培训水平 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-09 14:52:40
 * @since JDK1.8
 */
@Mapper
@Component
public interface ParTrainingLevelDao extends BaseMapper<ParTrainingLevelEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return ParTrainingLevelVO
    */
    IPage<ParTrainingLevelVO> queryByPage(Page page, @Param("queryDTO") ParTrainingLevelQueryDTO queryDTO);

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
    List<ParTrainingLevelExcelVO> queryAllExportData(@Param("queryDTO")ParTrainingLevelQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<ParTrainingLevelExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);
}
