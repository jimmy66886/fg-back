package com.zzmr.fgback.mapper;

import com.github.pagehelper.Page;
import com.zzmr.fgback.bean.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzmr.fgback.dto.CommentDto;
import com.zzmr.fgback.vo.CommentVo;
import com.zzmr.fgback.vo.RecentCommentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zzmr
 * @since 2024-01-13
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 获取菜谱对应的顶级评论
     *
     * @param commentDto
     * @return
     */
    Page<CommentVo> getTop(@Param("commentDto") CommentDto commentDto);

    Page<CommentVo> getByTopComment(@Param("commentDto") CommentDto commentDto);

    List<RecentCommentVo> getNewComment(@Param("userId") Long userId);
}
