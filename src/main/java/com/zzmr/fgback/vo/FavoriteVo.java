package com.zzmr.fgback.vo;

import com.zzmr.fgback.bean.Favorite;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author zzmr
 * @create 2024-02-01 16:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class FavoriteVo extends Favorite {

    private String coverImg;

}
