package com.zzmr.fgback.bean;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 
 * </p>
 *
 * @author zzmr
 * @since 2024-01-30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class BasketMaterial implements Serializable {

    private Long basketId;

    private String materialName;

    private String materialAmount;


}
