package com.zzmr.fgback.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 
 * </p>
 *
 * @author zzmr
 * @since 2024-01-24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class Favorites implements Serializable {

    @TableId(value = "favorites_id", type = IdType.AUTO)
    private Long favoritesId;

    private Long userId;

    private LocalDateTime createTime;

    /**
     * 收藏夹名称
     */
    private String name;


}
