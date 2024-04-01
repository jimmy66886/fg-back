package com.zzmr.fgback.service;

import com.zzmr.fgback.bean.Followers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzmr.fgback.dto.AddFollowers;
import com.zzmr.fgback.vo.FollowersVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zzmr
 * @since 2024-02-06
 */
public interface FollowersService extends IService<Followers> {

    /**
     * 添加关注
     *
     * @param addFollowers
     */
    void insert(AddFollowers addFollowers);

    /**
     * 取消关注
     *
     * @param followingId
     */
    void delete(Long followingId);

    /**
     * 查看关注列表
     *
     * @return
     */
    List<FollowersVo> getList(Long userId);

    /**
     * 获取粉丝列表
     *
     * @return
     */
    List<FollowersVo> getFans(Long userId);

    /**
     * 查询用户是否关注菜谱作者
     *
     * @param authorId
     * @return
     */
    Boolean getFollowed(Long authorId);

    /**
     * 获取新增粉丝
     *
     * @return
     */
    List<FollowersVo> getNewFans();
}
