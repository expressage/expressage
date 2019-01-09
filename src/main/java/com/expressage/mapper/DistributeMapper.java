package com.expressage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.expressage.pojo.Distribute;

public interface DistributeMapper {
    int deleteByPrimaryKey(Integer did);

    
    int insert(Distribute record);

    int insertSelective(Distribute record);

    Distribute selectByPrimaryKey(Integer did);

    int updateByPrimaryKeySelective(Distribute record);

    int updateByPrimaryKey(Distribute record);
    
    List<Distribute> pmlSelectDistribute();
    
    int pmlInsertDistribute(Distribute distribute);
    
    int pmlUpdateStatus(@Param("did") Integer did ,@Param("status") String status);
    
    Distribute pmlSelectKeyDid(@Param("did") Integer did);
    
}