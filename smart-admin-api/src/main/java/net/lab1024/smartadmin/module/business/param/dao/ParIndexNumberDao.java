package net.lab1024.smartadmin.module.business.param.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParIndexNumberQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParIndexNumberEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParIndexNumberVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParIndexNumberExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 自动化投资指数 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-09 18:42:39
 * @since JDK1.8
 */
@Mapper
@Component
public interface ParIndexNumberDao extends BaseMapper<ParIndexNumberEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return ParIndexNumberVO
    */
    IPage<ParIndexNumberVO> queryByPage(Page page, @Param("queryDTO") ParIndexNumberQueryDTO queryDTO);

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
    List<ParIndexNumberExcelVO> queryAllExportData(@Param("queryDTO")ParIndexNumberQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<ParIndexNumberExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    ParIndexNumberVO queryData(@Param("currentStageId")Long currentStageId);
}
