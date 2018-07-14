/**
 * 项目名：	ssx-server
 * 文件名：	MyMapper.java
 * 模块说明：
 * 修改历史：
 * 2018/7/14 - seven - 创建。
 */
package com.seven.server.core.mapper;

import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * * 定制版MyBatis Mapper插件接口
 * 如需其他接口参考官方文档自行添加
 *
 * @author seven
 */
public interface MyMapper<T> extends
        BaseMapper<T>,
        ConditionMapper<T>,
        IdsMapper<T>,
        InsertListMapper<T> {

}
