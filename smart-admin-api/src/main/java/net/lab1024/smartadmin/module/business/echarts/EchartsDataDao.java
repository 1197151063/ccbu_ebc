package net.lab1024.smartadmin.module.business.echarts;

import net.lab1024.smartadmin.module.business.param.domain.vo.ParMarketShareVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface EchartsDataDao {
    List<ParMarketShareVO> queryMSByPidAndSid(@Param("proId")Long proId,@Param("stageId")Long stageId,@Param("type")String type);
    List<ParMarketShareVO> queryMarketShare(@Param("marketShare")ParMarketShareVO marketShareVO);
}
