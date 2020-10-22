package com.atguigu.gmall.pms.vo;

import com.atguigu.gmall.pms.vo.AttrValueVo;
import lombok.Data;

import java.util.List;

/**
 * @author wh
 * @user wh
 * @create 2020-10-12
 */
@Data
public class ItemGroupVo {
    private Long id;
    private String name;
    private List<AttrValueVo> attrs;
}
