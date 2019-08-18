//package com.cskaoyan.handler;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.ibatis.type.JdbcType;
//import org.apache.ibatis.type.MappedJdbcTypes;
//import org.apache.ibatis.type.MappedTypes;
//import org.apache.ibatis.type.TypeHandler;
//
//import java.io.IOException;
//import java.sql.CallableStatement;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//@MappedJdbcTypes(JdbcType.VARCHAR)
//@MappedTypes(String[].class)
//public class StringTypeHandler implements TypeHandler<String[]> {
//
//    @Override
//    public void setParameter(PreparedStatement preparedStatement, int index, String[] ints, JdbcType jdbcType) throws SQLException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        String value = null;
//        try {
//            //将object转换成json字符串
//            value = objectMapper.writeValueAsString(ints);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        preparedStatement.setString(index,value);
//    }
//
//    @Override
//    public String[] getResult(ResultSet resultSet, String s) throws SQLException {
//        String content = resultSet.getString(s);
//        return transfer(content);
//    }
//
//    @Override
//    public String[] getResult(ResultSet resultSet, int index) throws SQLException {
//        String content = resultSet.getString(index);
//        return transfer(content);
//    }
//
//    @Override
//    public String[] getResult(CallableStatement callableStatement, int i) throws SQLException {
//        String content = callableStatement.getString(i);
//        return transfer(content);
//    }
//    private String[] transfer(String content)  {
//        ObjectMapper objectMapper = new ObjectMapper();
//        String[] ints = new String[0];
//        try {
//            ints = objectMapper.readValue(content, String[].class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return ints;
//    }
//}
