package com.alibou.security.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCoverDTO {

    private Long totalNumberOfOrderTools;
    private List<OrderDTO> orderList;
}
