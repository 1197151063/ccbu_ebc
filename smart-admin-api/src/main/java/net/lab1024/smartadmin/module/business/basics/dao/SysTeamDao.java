package net.lab1024.smartadmin.module.business.basics.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysTeamQueryDTO;
import net.lab1024.smartadmin.module.business.basics.domain.entity.SysDepartmentEmployeeEntity;
import net.lab1024.smartadmin.module.business.basics.domain.entity.SysTeamEntity;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysTeamVO;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysTeamExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 团队表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-21 10:13:24
 * @since JDK1.8
 */
@Mapper
@Component
public interface SysTeamDao extends BaseMapper<SysTeamEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return SysTeamVO
    */
    IPage<SysTeamVO> queryByPage(Page page, @Param("queryDTO") SysTeamQueryDTO queryDTO);

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
    List<SysTeamExcelVO> queryAllExportData(@Param("queryDTO")SysTeamQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<SysTeamExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    void insertTeam(@Param("entity")SysTeamEntity entity);

    List<SysDepartmentEmployeeEntity> selectDepartmentEmployee(Long id);
}
