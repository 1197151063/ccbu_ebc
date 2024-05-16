package net.lab1024.smartadmin.module.business.basics.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysProjectAddDTO;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysProjectQueryDTO;
import net.lab1024.smartadmin.module.business.basics.domain.entity.SysProjectEntity;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysProjectExcelVO;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysProjectVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ ��Ŀ�� ]
 *
 * @author SMS
 * @version 1.0
 * @company 1024����ʵ����( www.1024lab.net )
 * @copyright (c)  1024����ʵ����( www.1024lab.net )Inc. All rights reserved.
 * @date 2021-11-21 09:08:37
 * @since JDK1.8
 */
@Mapper
@Component
public interface SysProjectDao extends BaseMapper<SysProjectEntity> {

    /**
     * ��ҳ��ѯ
     * @param queryDTO
     * @return SysProjectVO
    */
    IPage<SysProjectVO> queryByPage(Page page, @Param("queryDTO") SysProjectQueryDTO queryDTO);

    /**
     * ����idɾ��
     * @param id
     * @return
    */
    void deleteById(@Param("proId")Long id);

    /**
     * ����id����ɾ��
     * @param idList
     * @return
    */
    void deleteByIdList(@Param("idList") List<Long> idList);

        /**
     * ��ѯ���е�������
     * @param queryDTO
     * @return
     */
    List<SysProjectExcelVO> queryAllExportData(@Param("queryDTO")SysProjectQueryDTO queryDTO);

        /**
         * ��ѯ������������
         * @param idList
         * @return
         */
    List<SysProjectExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    Integer selectByStageId(@Param("proId")Long proId,@Param("status")Integer status);

    List<SysProjectVO> queryBy(@Param("queryDTO")SysProjectQueryDTO queryDTO);

    SysProjectVO queryData(@Param("projectId")Long projectId);

    void updateProjectByProId(@Param("sysProject") SysProjectAddDTO project);

    List<SysProjectVO> queryByAllProject();

//    @Options(useGeneratedKeys =true,keyProperty = "proId")
    void insertProject(@Param("entity")SysProjectEntity entity);

    void updateStatus(@Param("currentProjectId")Long currentProjectId,@Param("status") Integer status);
}
