package org.main.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.main.model.Order;
import org.main.model.OrderConfig;


@Mapper
public interface OrderConfigMapper extends BaseMapper<OrderConfig> {


}
