package com.cskaoyan.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(List.class)
public class String2StringListTypeHandler implements TypeHandler<List<String>> {


    @Override
    public void setParameter(PreparedStatement preparedStatement, int index, List<String> stringList, JdbcType jdbcType) throws SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        String value = null;
        try {
            //将object转换成json字符串
            value = objectMapper.writeValueAsString(stringList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        preparedStatement.setString(index,value);
    }

    @Override
    public List<String> getResult(ResultSet resultSet, String s) throws SQLException {
        String content = resultSet.getString(s);
        return transfer(content);
    }

    @Override
    public List<String> getResult(ResultSet resultSet, int index) throws SQLException {
        String content = resultSet.getString(index);
        return transfer(content);
    }

    @Override
    public List<String> getResult(CallableStatement callableStatement, int i) throws SQLException {
        String content = callableStatement.getString(i);
        return transfer(content);
    }
    private List<String> transfer(String content)  {
        ObjectMapper objectMapper = new ObjectMapper();
        String[] strings = new String[0];
        try {
            strings = objectMapper.readValue(content, String[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Arrays.asList(strings);
    }
}
