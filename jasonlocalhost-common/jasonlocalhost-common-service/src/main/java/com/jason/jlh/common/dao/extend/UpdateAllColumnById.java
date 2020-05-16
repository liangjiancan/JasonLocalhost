package com.jason.jlh.common.dao.extend;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import java.util.stream.Collectors;

/**
 * @title: UpdateAllColumnById
 * @package: com.jason.jlh.common.dao.extend
 * @description: updateAllColumnById方法定义类
 * @author: huyongjun
 * @date: 2020/5/16
 * @version: v1.0
 */
public class UpdateAllColumnById extends AbstractMethod {

    /**
     * 方法名
     */
    private static final String METHOD_NAME = "updateAllColumnById";

    /**
     * sql格式: UPDATE %s SET %s WHERE %s=#{%s} %s
     */
    private static final String SQL_TEMPLATE = SqlMethod.UPDATE_ALL_COLUMN_BY_ID.getSql();

    /**
     * 注入映射语句
     *
     * @param: [mapperClass Mapper类对象, modelClass 实体类类对象, tableInfo 表信息]
     * @return: org.apache.ibatis.mapping.MappedStatement
     * @author: huyongjun
     * @date: 2020/5/16
     */
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        boolean logicDelete = tableInfo.isLogicDelete();
        String additional = this.optlockVersion() + tableInfo.getLogicDeleteSql(true, false);
        String sql = String.format(SQL_TEMPLATE,
                tableInfo.getTableName(),
                sqlSet(logicDelete, false, tableInfo, false, ENTITY_DOT, ENTITY_DOT),
                tableInfo.getKeyColumn(),
                ENTITY_DOT + tableInfo.getKeyProperty(),
                additional);
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return addUpdateMappedStatement(mapperClass, modelClass, METHOD_NAME, sqlSource);
    }

    /**
     * 重写sqlSet方法
     *
     * @param: [logic, ew, table, judgeAliasNull, alias, prefix]
     * @return: java.lang.String
     * @author: huyongjun
     * @date: 2020/5/16
     */
    @Override
    protected String sqlSet(boolean logic, boolean ew, TableInfo table, boolean judgeAliasNull, String alias, String prefix) {
        // 修改此处为调用自定义的getAllSqlSet方法
        String sqlScript = getAllSqlSet(table, logic, prefix);
        if (judgeAliasNull) {
            sqlScript = SqlScriptUtils.convertIf(sqlScript, String.format("%s != null", alias), true);
        }
        if (ew) {
            sqlScript = sqlScript + "\n";
            sqlScript = sqlScript + SqlScriptUtils.convertIf(SqlScriptUtils.unSafeParam("ew.sqlSet"), String.format("%s != null and %s != null", "ew", "ew.sqlSet"), false);
        }
        sqlScript = SqlScriptUtils.convertSet(sqlScript);
        return sqlScript;
    }

    /**
     * 获取所有的SET语句, 忽略if标签使得空值字段也进行设置处理
     *
     * @param: [table, ignoreLogicDelFiled, prefix]
     * @return: java.lang.String
     * @author: huyongjun
     * @date: 2020/5/16
     */
    protected String getAllSqlSet(TableInfo table, boolean ignoreLogicDelFiled, String prefix) {
        String newPrefix = prefix == null ? "" : prefix;
        return table.getFieldList()
                .stream()
                .filter((i) -> {
                    if (ignoreLogicDelFiled) {
                        return !table.isLogicDelete() || !i.isLogicDelete();
                    } else {
                        return true;
                    }
                }).map(i -> i.getSqlSet(true, newPrefix)) // 此处ignoreIf为true, 则忽略if标签
                .collect(Collectors.joining("\n"));
    }
}
