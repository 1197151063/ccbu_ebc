package net.lab1024.smartadmin.module.business.report.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.lab1024.smartadmin.module.business.report.domain.entity.RepPeerReportPurchaseEntity;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepPeerReportPurchaseVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface RepPeerReportPurchaseDao extends BaseMapper<RepPeerReportPurchaseEntity> {

    List<RepPeerReportPurchaseVO> queryRecord(@Param("currentProjectId")Long currentProjectId,@Param("currentTeamId") Long currentTeamId,@Param("currentStageId") Long currentStageId);
}
