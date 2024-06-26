package com.crio.starter.exchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class XmemeRequestDto {
    private String id;

    private String name;

    private String url;

    private String caption;

}
