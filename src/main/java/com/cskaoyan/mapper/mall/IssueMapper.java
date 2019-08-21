package com.cskaoyan.mapper.mall;

import com.cskaoyan.bean.admin.mall.issue.Issue;
import com.cskaoyan.bean.admin.mall.issue.IssueExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IssueMapper {
    long countByExample(IssueExample example);

    int deleteByExample(IssueExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Issue record);

    int insertSelective(Issue record);

    List<Issue> selectByExample(IssueExample example);

    Issue selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Issue record, @Param("example") IssueExample example);

    int updateByExample(@Param("record") Issue record, @Param("example") IssueExample example);

    int updateByPrimaryKeySelective(Issue record);

    int updateByPrimaryKey(Issue record);

    List<Issue> getIssueList();

    List<Issue> getIssueListByQuestion(@Param("question") String question);

    Issue selectByAnswer(@Param("answer")String answer);
}