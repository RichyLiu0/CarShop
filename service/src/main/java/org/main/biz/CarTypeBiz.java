package org.main.biz;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.carshop.entities.PageRestResult;
import org.carshop.entities.RestResult;
import org.carshop.utilities.ExceptionUnits;
import org.main.dao.CarTypeAttrMapper;
import org.main.dao.CarTypeMapper;
import org.main.dao.CarTypeAttrMapper;
import org.main.dao.CarTypeMapper;
import org.main.enums.RestResultEnum;
import org.main.model.CarType;
import org.main.model.CarTypeAttr;
import org.main.model.CarType;
import org.main.model.CarTypeAttr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;



@Component
public class CarTypeBiz extends ServiceImpl<CarTypeMapper, CarType> {

    @Autowired
    private CarTypeMapper mapper;

    @Autowired
    private CarTypeAttrMapper attrMapper;

    public List<CarType> getLst() {
        QueryWrapper<CarType> wrapper = new QueryWrapper<>();
        return mapper.selectList(wrapper);
    }

    public RestResult add (CarType serial, List<CarTypeAttr> attrs)
    {
        RestResult rs =new RestResult(RestResultEnum.sucess.getValue(),"成功");

        try {
            mapper.insert(serial);
            if (CollectionUtils.isEmpty(attrs) != true) {
                for (CarTypeAttr attr : attrs) {
                    attrMapper.insert(attr);
                }
            }
        }
        catch (Exception ex)
        {
            rs.setResult(RestResultEnum.failth.getValue());
            rs.setMsg(ExceptionUnits.getMsg(ex));
        }
        return rs;
    }

    public RestResult update (CarType serial, List<CarTypeAttr> attrs)
    {
        RestResult rs =new RestResult(RestResultEnum.sucess.getValue(),"成功");

        try {
            mapper.updateById(serial);

            if (CollectionUtils.isEmpty(attrs) != true) {

                QueryWrapper<CarTypeAttr> wrapper=new QueryWrapper<>();
                wrapper.eq("car_type_id",serial.getId());
                List<CarTypeAttr> delAttrs= attrMapper.selectList(wrapper);

                //删除
                for( CarTypeAttr attr: delAttrs)
                {
                    attrMapper.deleteById(attr);
                }

                //添加
                for (CarTypeAttr attr : attrs) {
                    attrMapper.insert(attr);
                }
            }
        }
        catch (Exception ex)
        {
            rs.setResult(RestResultEnum.failth.getValue());
            rs.setMsg(ExceptionUnits.getMsg(ex));
        }
        return rs;
    }


    public PageRestResult<CarType> getPage(Page<CarType> page,String serialId , String name, String desc) {
        PageRestResult<CarType> rs=new PageRestResult();
        IPage<CarType> list = mapper.getPage(page, serialId , name,desc);
        if (list.getSize()==0) {
            rs.setResult(RestResultEnum.failth.getValue());
            rs.setMsg("获取失败");
        }else {
            rs.setResult(RestResultEnum.sucess.getValue());
            rs.setMsg("获取成功");
            rs.filPage(list); //数据赋值
        }
        return rs;
    }



}
