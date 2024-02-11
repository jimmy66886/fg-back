package com.zzmr.fgback.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzmr.fgback.bean.Followers;
import com.zzmr.fgback.dto.AddFollowers;
import com.zzmr.fgback.mapper.FollowersMapper;
import com.zzmr.fgback.service.FollowersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzmr.fgback.util.ContextUtils;
import com.zzmr.fgback.vo.FollowersVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zzmr
 * @since 2024-02-06
 */
@Service
public class FollowersServiceImpl extends ServiceImpl<FollowersMapper, Followers> implements FollowersService {

    @Autowired
    private FollowersMapper followersMapper;


    /**
     * 添加关注
     *
     * @param addFollowers
     */
    @Override
    public void insert(AddFollowers addFollowers) {
        addFollowers.setFollowerId(ContextUtils.getCurrentId());
        Followers followers = new Followers();
        BeanUtils.copyProperties(addFollowers, followers);
        followersMapper.insert(followers);
    }

    /**
     * 取消关注
     *
     * @param followingId
     */
    @Override
    public void delete(Long followingId) {
        followersMapper.delete(new LambdaQueryWrapper<Followers>()
                .eq(Followers::getFollowerId, ContextUtils.getCurrentId())
                .eq(Followers::getFollowingId, followingId)
        );
    }

    /**
     * 查看关注列表
     *
     * @return
     */
    @Override
    public List<FollowersVo> getList() {

        // 先获取到当前用户id
        Long currentId = ContextUtils.getCurrentId();
        List<FollowersVo> followersVoList = followersMapper.getList(currentId);
        return followersVoList;
    }

    /**
     * 获取粉丝列表
     *
     * @return
     */
    @Override
    public List<FollowersVo> getFans() {
        Long currentId = ContextUtils.getCurrentId();
        List<FollowersVo> fans = followersMapper.getFans(currentId);
        return fans;
    }
}
