package hello.itemservice.domain.item;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * FAST 빠른배송
 * SLOW
 * NOMAL 일반배송
 */
@Data
@AllArgsConstructor
public class DeliveryCode {
    
    private String code;
    
    private String displayName;
}
