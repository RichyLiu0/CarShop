package org.main.biz;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.carshop.entities.PageRestResult;
import org.carshop.entities.RestResult;
import org.carshop.utilities.ExceptionUnits;
import org.main.dao.CarSerialAttrMapper;
import org.main.dao.CarSerialMapper;
import org.main.enums.RestResultEnum;
import org.main.model.CarSerial;
import org.main.model.CarSerialAttr;
import org.main.model.CarTypeAttr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author Richy
 * @date 2020/08/16
 */
//@Service("zarSerialBiz")

@Component
public class CarSerialBiz extends ServiceImpl<CarSerialMapper, CarSerial> {

    @Autowired
    private CarSerialMapper mapper;

    @Autowired
    private CarSerialAttrMapper attrMapper;

    public List<CarSerial> getLst() {
        QueryWrapper<CarSerial> wrapper = new QueryWrapper<>();
        return mapper.selectList(wrapper);
    }

    public RestResult add (CarSerial serial, List<CarSerialAttr> attrs)
    {
        RestResult rs =new RestResult(RestResultEnum.sucess.getValue(),"成功");

        try {
            mapper.insert(serial);
            if (CollectionUtils.isEmpty(attrs) != true) {
                for (CarSerialAttr attr : attrs) {
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

    public RestResult update (CarSerial serial, List<CarSerialAttr> attrs)
    {
        RestResult rs =new RestResult(RestResultEnum.sucess.getValue(),"成功");

        try {
            mapper.updateById(serial);

            if (CollectionUtils.isEmpty(attrs) != true) {

                QueryWrapper<CarSerialAttr> wrapper=new QueryWrapper<>();
                wrapper.eq("serial_id",serial.getId());
                List<CarSerialAttr> delAttrs= attrMapper.selectList(wrapper);

                //删除
                for( CarSerialAttr attr: delAttrs)
                {
                    attrMapper.deleteById(attr);
                }

                //添加
                for (CarSerialAttr attr : attrs) {
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


    public PageRestResult<CarSerial> getPage(Page<CarSerial> page, String name, String desc) {
        PageRestResult<CarSerial> rs=new PageRestResult();
        IPage<CarSerial> list = mapper.getPage(page, name,desc);
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
