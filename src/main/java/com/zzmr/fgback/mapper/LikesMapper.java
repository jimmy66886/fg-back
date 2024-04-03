package com.zzmr.fgback.mapper;

import com.zzmr.fgback.bean.Likes;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzmr.fgback.vo.LikeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zzmr
 * @since 2024-01-13
 */
@Mapper
public interface LikesMapper extends BaseMapper<Likes> {

    List<LikeVo> getRecipeLikes(@Param("userId") Long userId);

    List<LikeVo> getCommentLikes(@Param("userId") Long userId);
}
