package net.lab1024.smartadmin.module.business.basics.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysStageQueryDTO;
import net.lab1024.smartadmin.module.business.basics.domain.entity.SysStageEntity;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysStageVO;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysStageExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 阶段表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-22 15:55:16
 * @since JDK1.8
 */
@Mapper
@Component
public interface SysStageDao extends BaseMapper<SysStageEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return SysStageVO
    */
    IPage<SysStageVO> queryByPage(Page page, @Param("queryDTO") SysStageQueryDTO queryDTO);

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
    List<SysStageExcelVO> queryAllExportData(@Param("queryDTO")SysStageQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<SysStageExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    List<Integer> queryAllId();

    String queryData(@Param("stageId")Long stageId);
}
